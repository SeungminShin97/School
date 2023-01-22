import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Handler {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    String name;

    public Handler(Socket s, DataInputStream dis, DataOutputStream dos, String name) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.name = name;
    }

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
        }
        String msg, recievename;
        while(true) {
            try {
                msg = dis.readUTF();
                System.out.printf(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
