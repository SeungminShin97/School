import java.util.*;

public class Poker implements ClearScreen{
    private CardHashMap cardhash;
    private Betting betting = new Betting();
    private int headcnt = 0;
    private int betmoney = 0;
    private String[] saveList;   // 죽으면 die 살면 save 올인 all
    private  int[] moneyArr;
    private Scanner sc = new Scanner(System.in);

    public int getBetmoney() {
        return betmoney;
    }

    public void pokerMain(int money) {
        betmoney = money;
        while(true) {
            clear();
            System.out.println("포커게임");
            System.out.println("보유금액 : " + betmoney);
            System.out.println("1. 시작하기");
            System.out.println("2. 도움말");
            System.out.println("0. 종료");
            System.out.println("입력 : ");
            int menu = sc.nextInt();
            if (menu == 1) {
                System.out.print("인원 수(2~5) : ");
                int num = sc.nextInt();
                if(num >= 2 || num <= 5) {
                    pokerStart(num);
                } else {
                    System.out.println("잘못된 값입니다.");
                }
            } else if (menu == 2) {
                help();
            } else if (menu == 0) {
                break;
            } else {
                System.out.println("잘못된 값입니다.");
            }
        }
    }
    public void help(){
        System.out.println("포커의 기본 : ");
        System.out.println("포커는 2~10, J,Q,K,A의 숫자를 가진 각각 4종류 무늬의 카드 52장으로 즐기는 카드 게임입니다.\n" +
                "룰에 따라 받은 총 7장 카드의 족보를 비교해서 가장 높은 족보의 유저가 베팅금을 모두 획득합니다.\n" +
                "포커는 2명이상 5명까지 함께 게임에 참여할 수 있습니다.");
    }

    public void pokerStart(int num){
        System.out.print("판돈 설정 : ");
        int stake = sc.nextInt();
        int cnt = 0;
        betting.setStake(stake);
        betting.setBetMoney(betmoney);
        betting.ante(num);

        cardhash = new CardHashMap();
        cardhash.setNumCard(num);
        headcnt = num;
        saveList = new String[num];
        for(int i = 0; i < num; i++){
            saveList[i] = "save";
        } // savelist에 save라고 저장
        betting.setSaveList(saveList);  // betting 클래스에 savelist 저장
        //게임 시작
        for(int i = 0; i < 3; i++){
            cardhash.dealCard(saveList);
            cnt++;
        } // 3장 뿌리기
        cardhash.showCardUser(); // 카드 보여주기
        cardhash.shuffleCard(); // 3장중 뒤집을 카드 선택
        sc.nextLine();
        for(int i = 0; i < 4; i++) {
            if(saveList[0].equals("save") || saveList[0].equals("all")){
                clear();          // 화면 지우기
                cardhash.dealCard(saveList); // 카드 나눠주기
                cnt++;
                showCardMosaic(num);
                System.out.println("         " + cardhash.cardRanking(cardhash.cardScore(cardhash.getCardlist(0))));
                betting.betting(highScore(num, cnt)); // 베팅하기
                saveList = betting.getSaveList();    // 생존자 업데이트
                System.out.print("턴이 끝났습니다. 아무 키나 입력해주세요 ");
                String a = sc.nextLine();
            }
        }
        if(saveList[0].equals("die")){
            System.out.println("패배");
        }
        else {
            cnt++;
            showCard(num);
            System.out.println(" ");
            for (int i = 1; i < num; i++) {
                if(saveList[i].equals("die")){
                    System.out.println(" com " + i + " : die");
                }
                else {
                    System.out.println(" com " + i + " : " + cardhash.cardRanking(cardhash.cardScore(cardhash.getCardlist(i))));
                }
            }
            System.out.println("사용자 : " + cardhash.cardRanking(cardhash.cardScore(cardhash.getCardlist(0))));
            int getbank = betting.getBank(); // 베팅한돈 가져오기
            if (highScore(num, cnt) == 0) {
                System.out.println("축하합니다 우승하셨습니다.!!!");
                betmoney += getbank;
            } else {
                System.out.println("패배");
            }
        }
        System.out.print("턴이 끝났습니다. 아무 키나 입력해주세요 ");
        String a = sc.nextLine();

    }
    //가장 높은 사람의 번호 반환
    public int highScore(int num, int cnt){
        int[] basescore = new int[num];
        int [] comparescore = new int[num];
        if(cnt == 7){
            for(int i = 0; i < num; i++){
                if(saveList[i].equals("die")){
                    basescore[i] = 0;
                }
                else {
                    int[] asdf = new int[cnt - 3];
                    for (int j = 0; j < cnt - 3; j++) {
                        asdf[j] = cardhash.getCardlist(i)[j + 2];
                    }
                    basescore[i] = cardhash.cardScore((asdf));
                }
                comparescore[i] = basescore[i];
            }
        }
        else if(cnt == 8){
            for(int i = 0; i < num; i++){
                if(saveList[i].equals("die")){
                    basescore[i] = 0;
                }
                else {
                    basescore[i] = cardhash.cardScore(cardhash.getCardlist(i));
                    comparescore[i] = cardhash.cardScore(cardhash.getCardlist(i));
                }
            }
        }
        else {
            for(int i = 0; i < num; i++){
                if(saveList[i].equals("die")){
                    basescore[i] = 0;
                }
                else {
                    int[] asdf = new int[cnt - 2];
                    for (int j = 0; j < cnt - 2; j++) {
                        asdf[j] = cardhash.getCardlist(i)[j + 2];
                    }
                    basescore[i] = cardhash.cardScore((asdf));
                }
                comparescore[i] = basescore[i];
            }
        }
        Arrays.sort(comparescore); // 오름차순 정렬
        for(int i = 0; i < num; i++){
            if(basescore[i] == comparescore[comparescore.length-1]){
                return i;
            }
        }
        return 0; // 이거 반환되면 망함
    }
    //카드 보여주기 n명 , 모자이크 00
    public void showCardMosaic(int num){
        if(num == 2){
            cardhash.showCardRowMosaic(1);
        }
        else if(num == 3){
            cardhash.showCardRowMosaic(1);
            cardhash.showCardColMosaic(2,0);
        }
        else if(num == 4){
            cardhash.showCardRowMosaic(2);
            cardhash.showCardColMosaic(3,1);
        }
        else{
            for(int i = num - 1; i > 0; i--){
                cardhash.showCardRowMosaic(i);
                System.out.println(" ");
            }
        }
        cardhash.showCardUser();
    }
    //모자이크 xx
    public void showCard(int num){
        if(num == 2){
            cardhash.showCardRow(1);
        }
        else if(num == 3){
            cardhash.showCardRow(1);
            cardhash.showCardCol(2,0);
        }
        else if(num == 4){
            cardhash.showCardRow(2);
            cardhash.showCardCol(3,1);
        }
        else{
            for(int i = num - 1; i > 0; i--){
                cardhash.showCardRow(i);
                System.out.println(" ");
            }
        }
        cardhash.showCardUser();
    }


}
