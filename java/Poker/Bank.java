import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bank implements ClearScreen{
    private int money = 0;
    private int loan = 0;
    private ArrayList<String> moneyList = new ArrayList<>();
    private File file = new File(".\\moneyList.txt");

    public Bank(){
        load();
    }

    class Date{
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        String date = year + "/" + month + "/" + day + "/" + hour + ":" + min ;
        public String getDate(){
            return date;
        }
    }

    public void BankMain(int n){
        money = n;

        clear();
        Scanner sc = new Scanner(System.in);
        Date date = new Date();

        while(true){
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│ 은       행                              │");
            System.out.println("│ 1. 대출                                  │");
            System.out.println("│ 2. 상환                                  │");
            System.out.println("│ 3. 내역                                  │");
            System.out.println("│ 0. 종료                                  │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("메뉴선택 : ");
            int answer = sc.nextInt();
            if(answer == 1){
                System.out.print("대출하실 금액을 입력하세요(한도 천만원) : ");
                int asdf = sc.nextInt();
                if(asdf >= 10000000){
                    System.out.println("한도초과!!!!");
                }
                else{
                    money = asdf;
                    loan = asdf;
                    String result = date.getDate() + " + " + asdf;
                    moneyList.add(result);
                    System.out.println("대출 완료!!");
                }
            }
            else if(answer == 2){
                System.out.println("상환하실 금액을 입력해주세요");
                System.out.println(loan + "원");
                System.out.print("입력 : ");
                int asdf = sc.nextInt();
                if(asdf > money){
                    System.out.println("돈이 부족합니다!!");
                }
                else{
                    money -= asdf;
                    loan -= asdf;
                    String result = date.getDate() + " - " + asdf;
                    moneyList.add(result);
                    System.out.println(asdf + "원 상환 완료!!");
                }
            }
            else if(answer == 3){
                int total = 0;
                System.out.println("------날짜------------종류------금액----------");
                for(int i = 0; i < moneyList.size(); i++){
                    String[] array = moneyList.toArray(new String[3]);
                    String[] moneyDetail = array[i].split(" ");
                    if(moneyDetail[1].equals("+")) {
                        System.out.println(moneyDetail[0] + "      대출      " + moneyDetail[2]);
                        total += Integer.parseInt(moneyDetail[2]);
                    }
                    else{
                        System.out.println(moneyDetail[0] + "      상환      " + moneyDetail[2]);
                        total -= Integer.parseInt(moneyDetail[2]);
                    }
                    System.out.println(" ");
                }
                System.out.println("남은 금액 : " + total + "원");
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
    public int getMoney(){ return money; }

    public void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < moneyList.size(); i++) {
                bw.write(moneyList.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load()  {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                moneyList.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}

























