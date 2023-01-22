import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


public class Client {
    final static int ServerPort = 3005;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String msg;
        SocketAddress address = new InetSocketAddress("127.0.0.1", 3005);
        SocketChannel socketChannel = SocketChannel.open(address);
        System.out.println("게임 서버에 연결되었습니다.");
        System.out.print("이름 : ");
        String name = sc.next();
        HelperMethods.sendMessage(socketChannel, name);
        msg = HelperMethods.receiveMessage(socketChannel);
        System.out.println(msg);

        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String msg = sc.next();
                    HelperMethods.sendMessage(socketChannel, msg);
                }
            }
        });
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String msg = HelperMethods.receiveMessage(socketChannel);
                    System.out.println(msg);
                    if(msg.charAt(0) == '#')
                        System.exit(0);
                }

            }
        });
        readMessage.start();
        sendMessage.start();

    }
}
