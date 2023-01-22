import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    final static int ServerPort = 3005;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        Socket s = new Socket(ip, ServerPort);
        System.out.println("게임 서버에 연결되었습니다.");
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        System.out.print("이름 : ");
        String name = sc.next();
        dos.writeUTF(name);
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String msg = sc.next();
                    try {
                        dos.writeUTF(name + "#" + msg);
                        if(msg.equals("logout")) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    dis.close();
                    dos.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread readMessage = new Thread(new Runnable()
        {

            @Override
            public void run() {
                while(true) {
                    try {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch(IOException e) {
                        try {
                            s.close();
                            dis.close();
                            dos.close();
                            break;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }

            }
        });
        sendMessage.start();
        readMessage.start();
    }
}
