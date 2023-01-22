import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    static Vector<Handler> clients = new Vector<>();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("*******************************");
        System.out.print("게임 인원 수 : ");
        int n = sc.nextInt();
        Game game = new Game(n);
        System.out.print("맵의 크기 : ");
        int map = sc.nextInt();
        game.set(map);
        ServerSocket ss = new ServerSocket(3005);
        Socket s;
        int i = 0, gamecnt = 0;
        while(true) {
            if(i < n) {
                System.out.println("플레이어를 기다리는 중........(" + i++ + "/" + n + ")");
                s = ss.accept();
                System.out.println("사용자가 들어왔습니다.   : " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                String name = dis.readUTF();
                game.enter(name);
                Handler handler = new Handler(s, dis, dos, name);
                informNew(name + " 님이 들어왔습니다.");
                clients.add(handler);
                System.out.println(name + " 사용자 등록 완료");
                if(i == n) {
                    System.out.println("플레이어를 기다리는 중........(" + i + "/" + n + ")");
                    System.out.println("게임을 시작합니다...");
                    informNew("게임을 시작합니다..");
                }
            } else {
                if(gamecnt >= n)
                    gamecnt = 0;
                if(game.getSkip(gamecnt) == 0) {
                    clients.get(gamecnt).dos.writeUTF(game.getMap(gamecnt));
                    clients.get(gamecnt).dos.writeUTF("당신 차례입니다. \n주사위를 굴리시려면 아무키나 눌러주세요..");
                    String msg = clients.get(gamecnt).dis.readUTF();
                    System.out.println(clients.get(gamecnt).name + " : " + msg);
                    Random random = new Random();
                    int rnd = random.nextInt(6) + 1;
                    clients.get(gamecnt).dos.writeUTF("주사위 " + rnd + "이 나왔습니다!");
                    game.dice(gamecnt, rnd);
                    clients.get(gamecnt).dos.writeUTF("현재 위치는 " + game.getlocation(gamecnt) + "입니다.\n\n");
                    if(game.getlocation(gamecnt) >= map - 1) {
                        informNew("축하합니다!! " + clients.get(gamecnt).name + "님이 우승했습니다!!!!!!!!");
                        break;
                    }
                    gamecnt++;
                }
                else {
                    clients.get(gamecnt).dos.writeUTF("이번 턴은 쉬는 턴입니다. 다음 턴을 기다려 주세요");
                    game.setSkip(gamecnt);
                    gamecnt++;
                }
            }
        }
        sc.close();
        ss.close();
    }
    private static void informNew(String msg) throws IOException {
        for(Handler handler : clients)
            handler.dos.writeUTF(msg);
    }

}