import java.util.*;

public class Betting {
    private int betMoney; // 보유한 돈 , 컴퓨터는 무제한
    private int basestake, stake; // 판돈
    private int bank;  // 베팅한 금액들 모아놓은거
    private String[] saveList;
    private HashMap<Integer, Integer> betDetail;
    private Scanner sc = new Scanner(System.in);
    private Random rnd = new Random();

    public int getBetMoney() {
        return betMoney;
    }
    public int getBank() { return  bank; }

    public Betting() {
        betDetail = new HashMap<>();
        for(int i = 0; i < 3; i++){
            betDetail.put(i,0);
        }
    }
    /*
    0 = 베팅 횟수
    1 = 체크
    2 = 베트 , 콜, 레이즈, 폴드, 올인
     */
    // 보유한돈 입력
    public void setBetMoney(int a){
        betMoney = a;
    }
    public void setStake(int a){
        stake = a;
        basestake = a;
    }
    public void setSaveList(String[] sl){
        saveList = new String[sl.length];
        saveList = sl;
    }

    public String[] getSaveList(){
        return saveList;
    }

    //ante 시작할때 참가비 같은거?? 판돈 금액 지불
    public void ante(int headcnt) {
        bank += (stake * headcnt);
        betMoney -= stake;
        System.out.println(" ante : " + stake + "원");
        System.out.println("잔고 : " + betMoney + "원");
    }
    //베팅, asdf = 가장 높은 카드조합을 가지고 있는 사람의 번호
    public void betting (int asdf) {
        int n = asdf;
        int[] betconfirmlist = new int[saveList.length]; // 서로의 베팅금액이 맞나 확인하는 용
        Arrays.fill(betconfirmlist,0); // 0으로 초기화
         // 베팅 순서는 무조건 n에서 오름차순
        while(true){
            if(n == 0){ // 사용자일때
                if(saveList[n].equals("save")) {
                    while (true) {
                        System.out.println(" ");
                        System.out.println("보유금액 : " + betMoney);
                        if (confirmBetDetailCheck() == true) {
                            System.out.println("1. CHECK , 2. BET");
                            int num = sc.nextInt();
                            if (num == 1) { // 기록 리스트에 입력, 체크는 판돈에 영향 x
                                System.out.println("CHECK!!");
                                betconfirmlist[n] = 0;
                                betDetail.put(1,betDetail.get(1) + 1);
                                break;
                            } // 여태까지 check 만 했을 때
                            else if (num == 2) {
                                if(betMoney < stake){
                                    System.out.println("돈이 부족합니다 자동으로 all in 됩니다.");
                                    betDetail.put(2,betDetail.get(2) + 1);
                                    saveList[n] = "all";
                                    betconfirmlist[n] = betMoney;
                                    stake = betMoney;
                                    bank += betMoney;
                                    betMoney = 0;
                                    break;
                                }
                                else {
                                    System.out.println("BET!!");
                                    bank += stake;
                                    betMoney -= stake;
                                    betconfirmlist[n] = stake;
                                    betDetail.put(2, betDetail.get(2) + 1);
                                    break;
                                }
                            } // 기록 리스트에 입력, 베트 계산
                            else {
                                System.out.println("잘못된 값입니다.");
                            }
                        }
                        else {
                            if(betMoney >= stake) {
                                System.out.println("1. CALL , 2. RAISE , 3. FOLD , 4. ALL IN");
                                int num = sc.nextInt();
                                if (num == 1) {
                                    if (betconfirmlist[n] == 0) {
                                        bank += stake;
                                        betMoney -= stake;
                                    } else {
                                        int asfd = stake - betconfirmlist[n];
                                        bank += asdf;
                                        betMoney -= asdf;
                                    }
                                    betconfirmlist[n] = stake;
                                    betDetail.put(2, betDetail.get(2) + 1);
                                    System.out.println("CALL!!");
                                    break;
                                } else if (num == 2) {
                                    System.out.println("올릴 금액 입력 : ");
                                    int raisenum = sc.nextInt();
                                    stake += raisenum; // 판돈 올리기
                                    bank += stake;
                                    betMoney -= stake;
                                    betconfirmlist[n] = stake;
                                    betDetail.put(2, betDetail.get(2) + 1);
                                    System.out.println("RAISE!!");
                                    break;
                                } else if (num == 3) {
                                    System.out.println("FOLD!!");
                                    saveList[n] = "die";
                                    betDetail.put(2, betDetail.get(2) + 1);
                                    break;
                                } else if (num == 4) {
                                    System.out.println("ALL IN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    betDetail.put(2, betDetail.get(2) + 1);
                                    saveList[n] = "all";
                                    betconfirmlist[n] = betMoney;
                                    stake = betMoney;
                                    bank += betMoney;
                                    betMoney = 0;
                                    break;
                                } else {
                                    System.out.println("잘못된 값입니다.");
                                }
                            }
                            else {
                                while(true) {
                                    System.out.println("보유금액 부족");
                                    System.out.println("1. FOLD , 2. ALL IN ");
                                    int answer = sc.nextInt();
                                    if(answer == 1) {
                                        System.out.println("FOLD!!");
                                        saveList[n] = "die";
                                        betDetail.put(2, betDetail.get(2) + 1);
                                        break;
                                    }
                                    else if(answer ==2 ) {
                                        System.out.println("ALL IN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                        betDetail.put(2, betDetail.get(2) + 1);
                                        saveList[n] = "all";
                                        betconfirmlist[n] = betMoney;
                                        stake = betMoney;
                                        bank += betMoney;
                                        betMoney = 0;
                                        break;
                                    }
                                    else {
                                        System.out.println("잘못된 값입니다.");
                                    }
                                }
                            }
                        }
                    }
                } // 살아 있을 때
                else {
                    betconfirmlist[n] = stake;
                }
                n++;
                betDetail.put(0,betDetail.get(0) + 1);
            }
            else if(n >= saveList.length){
                n = 0;
            } // n이 끝까지 마지막사람갔을 때, 초기화
            else {
                if(saveList[n].equals("save")) {
                    if (confirmBetDetailCheck() == true) {
                        int rndnum = rnd.nextInt(6);
                        if (rndnum == 0) {
                            System.out.println("com" + n + " : CHECK!!");
                            betDetail.put(1,betDetail.get(1) + 1);
                            betconfirmlist[n] = 0;
                        }
                        else {
                            System.out.println("com" + n + " : BET!!");
                            bank += stake;
                            betDetail.put(2,betDetail.get(2) + 1);
                            betconfirmlist[n] = stake;
                        }
                    }//여태까지 check만 했을경우
                    else {
                        // 누가 이미 bet을 했을 때, 컴퓨터는 올인 없음
                        if(saveList[0].equals("all")) {
                            int rndnum = rnd.nextInt(5);
                            if(rndnum <= 3){
                                if (betconfirmlist[n] == 0) {
                                    bank += stake;
                                }
                                else {
                                    bank += stake - betconfirmlist[n];
                                }
                                betDetail.put(2, betDetail.get(2) + 1);
                                System.out.println("com" + n + " : CALL!!     + " + stake);
                                betconfirmlist[n] = stake;
                            } // call
                            else {
                                saveList[n] = "die";
                                betDetail.put(2, betDetail.get(2) + 1);
                                System.out.println("com" + n + " : Fold!!");
                            }
                        }
                        else {
                            // call raise fold  : 65 20 5
                            int rndnum = rnd.nextInt(90);
                            if (rndnum >= 0 && rndnum < 65) {
                                if (betconfirmlist[n] == 0) {
                                    bank += stake;
                                } else {
                                    bank += stake - betconfirmlist[n];
                                }
                                betDetail.put(2, betDetail.get(2) + 1);
                                System.out.println("com" + n + " : CALL!!     + " + stake);
                                betconfirmlist[n] = stake;
                            } // call
                            else if (rndnum >= 65 && rndnum < 85) {
                                int rndnum2 = rnd.nextInt(15);
                                if (rndnum2 >= 0 || rndnum2 < 13) {
                                    stake *= 2;
                                    bank += stake;
                                } else {
                                    stake *= 3;
                                    bank += stake;
                                }// raise,  9 : 1 비율로 2배 3배
                                betDetail.put(2, betDetail.get(2) + 1);
                                System.out.println("com" + n + " : RAISE!!    + " + stake);
                            } // raise
                            else {
                                saveList[n] = "die";
                                betDetail.put(2, betDetail.get(2) + 1);
                                betconfirmlist[n] = stake;
                                System.out.println("com" + n + " : Fold!!");
                            }// fold
                        }
                    }// 누가 이미 bet을 했을 때, 컴퓨터는 올인 없음
                }
                else if(saveList[n].equals("die")){
                    betconfirmlist[n] = stake;
                }
                n++;
                betDetail.put(0,betDetail.get(0) + 1);
            }
            int cnt = 0;
            for(int i = 0; i < saveList.length; i++){
                if(saveList[i].equals("die")){
                    betconfirmlist[i] = stake;
                }
            }
            if(betDetail.get(0) >= saveList.length) {
                for (int i = 0; i < betconfirmlist.length - 1; i++) {
                    if (betconfirmlist[i] != betconfirmlist[i + 1]) {
                        cnt++;
                    }
                } // 각자 베티한 가격 계산
                if(cnt == 0) {
                    break;
                } // 각자 베팅한 금액이 같으면?

            }

        }
        System.out.println("베팅 끝!");
        stake = basestake;
        for(int i = 0; i < 3; i++){
            betDetail.put(i,0);
        }
    }
    //check만 했을 경우 = true, 아니면 false
    private boolean confirmBetDetailCheck() {
        if(betDetail.get(2) == 0){
            return true;
        }
        else {
            return false;
        }
    }
}
/*

0 = 체크
1 = 베트
2 = 콜
3 = 레이즈
4 = 폴드
5 = 올인

게임시작할 때 카드 3장 받고 그중 한장 오픈
오픈한 카드중 가장 높은 카드를 가진 사람부터 시계방향으로 카드를 1장씩 뿌림
베팅
3,4번째 카드 조합중 가장 높은 사람부터 시계방향으로 카드를 1장씩 뿌림
베팅
~
히든카드 받고 마지막 베팅
체크(Check): 판돈을 추가하지 않고 차례를 넘기겠다는 신호. 카드가 돌아가고 처음으로 베팅하는 사람만 쓸 수 있다.
     단, 이후 다른 사람이 체크를 받아들이지 않고 판돈을 올렸다면 체크를 한 사람도 콜/레이즈를 하거나 판을
     포기해야 한다.[8]
베트(Bet): 한 베팅 라운드에서 최초로 판돈을 올리겠다는 신호. 만약 아무도 베트를 선언하지 않으면[9]
     플레이어 전원이 이번 라운드에선 모두 판돈을 올릴 의사가 없다고 간주되어 추가베팅 없이 다음 단계로
     넘어가게 된다. 베트를 선언한 사람은 그만큼의 베팅액을 올려야 하며, 다른 플레이어들은 콜 혹은 레이즈를
     선언해야만 계속해서 게임을 진행할 수 있다.
콜(Call): 앞의 플레이어가 판돈을 올린 것을 받아들인다는 의미. 하나의 베트·레이즈에 모든 플레이어가 콜하면,
    베팅 라운드는 다음 과정으로 넘어간다.
레이즈(Raise)[10]: 앞의 플레이어가 판돈을 올린 것을 받아들이고, 또한 거기서 추가로 더 베팅하는 것.
    한 베팅에 최대 2~3번까지 가능한 것이 일반적이지만, 레이즈 한도를 없앤 노리밋(No Limit) 룰도 있다.
    이 때 올인이라는 단어를 매우 쉽게 볼 수 있다.[11]
폴드(Fold): 경기를 포기하는 것. 포기하기 전까지 베팅한 금액은 잃게 된다. 한국에서는 명칭이 '다이(Die)'로 바뀌었다.
올인(All in): 콜 금액이 부족해 콜이 불가능할 경우, 자신이 지금 보유한 전 재산을 걸어 콜을 받는 행위.
    다만 올인을 하고 게임에서 이길 경우에는 각각의 플레이어들에게서 자신이 베팅한 금액을 초과하는
    금액은 받을 수 없다.[12] 언제 선언하든 올인한 사람은 카드 오픈까지 추가베팅 없이 게임할 수 있다.
    보통 올인을 할 경우 어차피 돈을 전부 걸었기 때문에 폴드를 하는 경우는 사실상 없다.[13]
//베팅
public void betting(){
    //베팅 가능한 사람의 수 = 생존자
    int cnt = 0;
    for(int i = 0; i < saveList.length; i++){
        if(saveList[i].equals("save")){
            cnt++;
        }
    }
    // 생존자 수로 베팅 배열 만들기
    int[] betlist = new int[cnt];
}
*/
