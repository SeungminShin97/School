import java.io.*;
import java.util.*;

public class Main {
    public static File file = new File(".\\money.txt");
    public static int betmoney = 0;
    public static void load(){
        try{
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while((line = buf.readLine()) !=null) {
                StringTokenizer tokenizer = new StringTokenizer(line,",");
                String num = tokenizer.nextToken();
                betmoney = Integer.parseInt(num);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File open error : " + file + "을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void save(){
        try{
            FileWriter writer = new FileWriter(file);
            BufferedWriter buf = new BufferedWriter(writer);
            buf.write(Integer.toString(betmoney));
            buf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        Poker poker = new Poker();


        load();
        System.out.println("*************************************************************************");
        System.out.println("포커를 플레이 하실려면 D2Coding 폰트를 기본 폰트로 설정하셔야 됩니다!!!!!");
        System.out.println("다른 폰트로 진행하면 오류가 생길 수도 있습니다!!!!!!");
        System.out.println("꼭 설치하세요!!!!");
        System.out.println("*************************************************************************");

        while(true){
            System.out.print("설치하셨나요(y/n) : ");
            String answer = sc.nextLine();
            if(answer.equals("y")){
                System.out.println("감사합니다!!!");
                break;
            }
            else{
                System.out.println("설치하셔야됩니다!!!!!!!!");
            }
        }
        ClearScreen.mainclear();
        bank.getMoney();
        while(true){
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│ 카 드 게 임                              │");
            System.out.println("│ 1. 포커                                  │");
            System.out.println("│ 2. 은행                                  │");
            System.out.println("│ 0. 종료                                  │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.println("│");
            System.out.println("│ 보유금액 : " + betmoney);
            System.out.println("└───────────────────────────────────────────");
            System.out.print("메뉴선택 : ");
            int answer = sc.nextInt();
            if(answer == 1){
                if(betmoney == 0){
                    System.out.println("돈이 없습니다!!! 대출해오세요!!");
                }
                else {
                    poker.pokerMain(betmoney);
                    betmoney = poker.getBetmoney();
                }
            }
            else if(answer == 2){
                bank.BankMain(betmoney);
                betmoney  = bank.getMoney();
            }
            else if(answer == 0){
                save();
                break;
            }
            else {
                System.out.println("잘못된 값입니다!");
            }
        }

    }




}






