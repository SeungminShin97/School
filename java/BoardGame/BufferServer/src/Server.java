import java.util.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    static Vector<User> clients = new Vector<>();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("*******************************");
        System.out.print("게임 인원 수 : ");
        int n = sc.nextInt();
        Game game = new Game(n);
        System.out.print("맵의 크기 : ");
        int map = sc.nextInt();
        game.set(map);
        ServerSocketChannel sschannel = ServerSocketChannel.open();
        sschannel.socket().bind(new InetSocketAddress(3005));
        ByteBuffer buf = ByteBuffer.allocate(512);
        int i = 0, gamecnt = 0;
        System.out.println("게임이 생성되었습니다.");
        while(true) {
            if(i < n) {
                System.out.println("플레이어를 기다리는 중........(" + i++ + "/" + n + ")");
                SocketChannel client = sschannel.accept();
                String name = HelperMethods.receiveMessage(client);
                informNew(name + " come!");
                User user = new User(client, name);
                game.enter(name);
                clients.add(user);
                System.out.println(name + " 사용자 등록 완료");
                if(i == n) {
                    System.out.println("플레이어를 기다리는 중........(" + i + "/" + n + ")");
                    System.out.println("게임을 시작합니다...");
                    informNew("Game start...");
                }
            } else {
                if(gamecnt >= n)
                    gamecnt = 0;
                if(game.getSkip(gamecnt) == 0) {
                    HelperMethods.sendMessage(clients.get(gamecnt).sc, game.getMap(gamecnt) + "\nYour turn, If you want roll the dice, press any key..");
                    String msg = HelperMethods.receiveMessage(clients.get(gamecnt).sc);
                    System.out.println(clients.get(gamecnt).name + " : " + msg);
                    Random random = new Random();
                    int rnd = random.nextInt(6) + 1;
                    game.dice(gamecnt, rnd);
                    HelperMethods.sendMessage(clients.get(gamecnt).sc, "Your dice : " + rnd + "\nYour locate is " + game.getlocation(gamecnt) + ".\n\npress any key...\n");
                    msg = HelperMethods.receiveMessage(clients.get(gamecnt).sc);
                    System.out.println(clients.get(gamecnt).name + " : " + msg);
                    if(game.getlocation(gamecnt) >= map - 1) {
                        informNew("#Congratulation!! " + clients.get(gamecnt).name + " is winner!");

                        break;
                    }
                    gamecnt++;
                }
                else {
                    HelperMethods.sendMessage(clients.get(gamecnt).sc, "Wait for next turn\n press any key..");
                    String msg = HelperMethods.receiveMessage(clients.get(gamecnt).sc);
                    System.out.println(clients.get(gamecnt).name + " : skip : " + msg);
                    game.setSkip(gamecnt);
                    gamecnt++;
                }
            }
        }
    }

    private static void informNew(String message) throws IOException {
        for(User user : clients)
            HelperMethods.sendMessage(user.sc, message);
    }
}

class User {
    SocketChannel sc;
    String name;
    public User(SocketChannel sc, String name) {
        this.sc = sc;
        this.name = name;
    }
}
