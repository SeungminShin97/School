import java.util.*;

public class CardHashMap {
    private HashMap<Integer, Integer[]> list = new HashMap<>(); // <사람번호, 받은카드번호>
    private Card card = new Card();
    private Scanner sc = new Scanner(System.in);

    public CardHashMap() {}


    //인원수 설정, 덱설정?
    public void setNumCard(int n) {
        for(int i = 0; i < n; i++){
            list.put(i,null);
        }
    }
    //인원수에 맞게 랜덤으로 카드 한장씩 돌리기
    public void dealCard(String[] saveList){
        Random rnd = new Random();
        //인원수 만큼 반복
        for(int i = 0; i < saveList.length; i++){
            //랜덤으로 숫자 주기
            if(saveList[i].equals("save") || saveList[i].equals("all")) {
                while (true) {
                    int rndnum = rnd.nextInt(52);
                    int cnt = 0;
                    for (int j = 0; j < card.getDeallistLength(); j++) {
                        if (card.getDeallistNum(j) == rndnum) {
                            cnt++;
                        }
                    }
                    if (cnt == 0) {
                        card.pushDeallist(rndnum);
                        //기존에 있던 숫자리스트 가져오기, 새로운 리스트 넣기
                        if (list.get(i) == null) {
                            Integer[] asdf = new Integer[1];
                            asdf[0] = rndnum;
                            list.put(i, asdf);
                        } else {
                            int len = list.get(i).length;
                            Integer[] asdf = new Integer[len + 1];
                            for (int j = 0; j < len; j++) {
                                asdf[j] = list.get(i)[j];
                            }
                            asdf[len] = rndnum;
                            list.put(i, asdf);
                        }
                        break;
                    }
                }
            }
        }
    }
    //처음 카드 3장을 받았을때 공개할 카드 정하기, 공개할 카드는 3번째로 보내기
    //컴퓨터는 그냥 3번째 받은거 오픈
    public void shuffleCard() {
        while(true) {
            System.out.print("공개할 카드를 선택하세요(1~3) : ");
            int num = sc.nextInt();
            if (num == 1 || num == 2) {
                Integer[] nlist = list.get(0);
                int asdf = nlist[num - 1];
                nlist[num - 1] = nlist[2];
                nlist[2] = asdf;
                list.put(0, nlist);
                break;
            } else if (num == 3) {
                break;
            } else {
                System.out.println("잘못된 값입니다.");
            }
        }
    }

    //카드상황 출력, 입력값 : 인원수
    //카드출력 12시방향, 가로, 컴퓨터, 모자이크 ㅇㅇ
    public void showCardRowMosaic(int n) {
        System.out.println("         ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐");
        //컴퓨터 2번째 줄
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(list.get(n).length > i){
                if(i == 0 || i == 1 || i == 6){
                    System.out.print("│■■■■│  ");
                }else{
                    System.out.print("│ " + getCardNum(list.get(n)[i]) + " │  ");
                }
            }else {
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        //컴퓨터 3번째 줄
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(list.get(n).length > i){
                if(i == 0 || i == 1 || i == 6){
                    System.out.print("│■■■■│  ");
                }else{
                    System.out.print("│  " + getCardPattern(list.get(n)[i]) + " │  ");
                }
            }else {
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        //컴퓨터 마지막줄
        System.out.println("         └────┘  └────┘  └────┘  └────┘  └────┘  └────┘  └────┘");
    }
    //카드출력 12시방향, 가로, 컴퓨터, 모자이크 xx
    public void showCardRow(int n ){
        System.out.println("         ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐");
        //컴퓨터 2번째 줄
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(list.get(n).length > i){
                    System.out.print("│ " + getCardNum(list.get(n)[i]) + " │  ");
            }else {
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        //컴퓨터 3번째 줄
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(list.get(n).length > i) {
                System.out.print("│  " + getCardPattern(list.get(n)[i]) + " │  ");
            }
            else{
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        //컴퓨터 마지막줄
        System.out.println("         └────┘  └────┘  └────┘  └────┘  └────┘  └────┘  └────┘");
    }
    //카드출력 3,9시방향, 세로, 컴퓨터, 모아지크 ㅇㅇ
    public void showCardColMosaic(int a, int b) {
        // 3번째 컴퓨터 출력 , 반복문 중간에 조건문 넣어서 선택적 4번 출력
        if(a > 0){
            // 3,4번 컴퓨터 1,2번 카드(안보이는거) 출력력
            for(int i = 0; i < 7; i++){
                //3-1
                System.out.print("┌──────┐");
                //4-1
                if(b > 0){
                    System.out.println("                                                        ┌──────┐");
                }else{
                    System.out.println(" ");
                }
                //3-2
                if(list.get(a).length > i){
                    if(i == 0 || i == 1 || i == 6){
                        System.out.print("│■■■■■■│");
                    } else {
                        System.out.print("│" + getCardNum(list.get(a)[i]) + "  " + getCardPattern(list.get(a)[i]) + " │");
                    }
                } else {
                    System.out.print("│      │");
                }
                //4-2
                if(b > 0) {
                    if (list.get(b).length > i) {
                        if (i == 0 || i == 1 || i == 6) {
                            System.out.print("                                                        │■■■■■■│");
                        } else {
                            System.out.print("                                                        │" + getCardNum(list.get(b)[i]) + "  " + getCardPattern(list.get(b)[i]) + " │");
                        }
                    } else {
                        System.out.print("                                                        │      │");
                    }
                    System.out.println(" ");
                } else {
                    System.out.println(" ");
                }

                //3-3
                System.out.print("└──────┘");
                if(b > 0){
                    System.out.println("                                                        └──────┘");
                } else {
                    System.out.println(" ");
                }

            }
        } else {
            for (int i = 0; i < 10; i++){
                System.out.println(" ");
            }
        }
    }
    //카드출력 3,9시방향, 세로, 컴퓨터, 모아지크 xx
    public void showCardCol(int a, int b){
        // 3번째 컴퓨터 출력 , 반복문 중간에 조건문 넣어서 선택적 4번 출력
        if(a > 0){
            // 3,4번 컴퓨터 1,2번 카드(안보이는거) 출력력
            for(int i = 0; i < 7; i++){
                //3-1
                System.out.print("┌──────┐");
                //4-1
                if(b > 0){
                    System.out.println("                                                        ┌──────┐");
                }else{
                    System.out.println(" ");
                }
                //3-2
                if(list.get(a).length > i){
                        System.out.print("│" + getCardNum(list.get(a)[i]) + "  " + getCardPattern(list.get(a)[i]) + " │");
                } else {
                    System.out.print("│      │");
                }
                //4-2
                if(b > 0) {
                    if (list.get(b).length > i) {
                            System.out.print("                                                        │" + getCardNum(list.get(b)[i]) + "  " + getCardPattern(list.get(b)[i]) + " │");
                    } else {
                        System.out.print("                                                        │      │");
                    }
                    System.out.println(" ");
                } else {
                    System.out.println(" ");
                }

                //3-3
                System.out.print("└──────┘");
                if(b > 0){
                    System.out.println("                                                        └──────┘");
                } else {
                    System.out.println(" ");
                }

            }
        } else {
            for (int i = 0; i < 10; i++){
                System.out.println(" ");
            }
        }
    }
    //카드출력 사용자
    public void showCardUser() {
        if(list.get(0).length != 3) {
            System.out.println("          xxxx    xxxx                                    xxxx ");
        }
        System.out.println("         ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐");
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(i < list.get(0).length) {
                System.out.print("│ " + getCardNum(list.get(0)[i]) + " │  ");
            } else {
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        System.out.print("         ");
        for(int i = 0; i < 7; i++){
            if(i < list.get(0).length) {
                System.out.print("│  " + getCardPattern(list.get(0)[i]) + " │  ");
            } else {
                System.out.print("│    │  ");
            }
        }
        System.out.println(" ");
        System.out.println("         └────┘  └────┘  └────┘  └────┘  └────┘  └────┘  └────┘");
    }

    // n번의 카드 배열 반환
    public int[] getCardlist(int n){
        int result[] = Arrays.stream(list.get(n)).mapToInt(Integer::intValue).toArray();
        return result;
    }

    //카드 숫자 가져오기  카드번호 0~ 51
    public String getCardNum(int asdf) {
        asdf /= 4;
        if (asdf == 0) {
            return " A";
        } else if (asdf == 10) {
            return " J";
        } else if (asdf == 11) {
            return " Q";
        } else if (asdf == 12) {
            return " K";
        } else if (asdf == 9) {
            return "10";
        } else if(asdf == 15) {
            return " A";
        } else {
            return " " + Integer.toString(asdf + 1);
        }
    }
    //카드 숫자 가져오기 0~12
    public String getCardNumForRanking(int asdf) {
        if (asdf == 0) {
            return "A";
        } else if (asdf == 10) {
            return "J";
        } else if (asdf == 11) {
            return "Q";
        } else if (asdf == 12) {
            return "K";
        } else if (asdf == 9) {
            return "10";
        } else if(asdf == 15) {
            return "A";
        }else {
            return " " + Integer.toString(asdf + 1);
        }
    }
    //카드 문양 가져오기  카드번호 0~ 51
    public String getCardPattern(int asdf){
        if(asdf % 4 == 1){
            return "♡";
        }else if (asdf % 4 == 2){
            return "◇";
        }else if (asdf % 4 == 3){
            return "♠";
        }else{
            return "♣";
        }
    }

    //점수 계산, 카드배열을 넘김
    public int cardScore(int[] list) {
        // 가장 높은 족보부터 순서대로
        //1. 로열 스트레이트 플러쉬 ,배열 오름차순 정렬///////////////////////////////////////////////////////////////
        Arrays.sort(list);
        int score = 0;
        int cnt = 0;
        int cntlist[] = {0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i = 0 ; i < list.length; i++){
            if(list[i] == 36 || list[i] == 40 || list[i] == 44 || list[i] == 48 || list[i] == 0){
                cntlist[0] += 1;
            } else if(list[i] == 37 || list[i] == 41 || list[i] == 45 || list[i] == 49 || list[i] == 1){
                cntlist[1] += 1;
            } else if(list[i] == 38 || list[i] == 42 || list[i] == 46 || list[i] == 50 || list[i] == 2){
                cntlist[2] += 1;
            } else if(list[i] == 39 || list[i] == 43 || list[i] == 47 || list[i] == 51 || list[i] == 3){
                cntlist[3] += 1;
            }
        }
        for(int j = 0; j < 4; j++){
            if(cntlist[j] == 5){
                return 1000 + cntlist[j];
            }
        }
        //2. 스트레이트 플러쉬////////////////////////////////////////////////////////////////////////////////////////
        cnt = 0;
        //Arrays.fill(cntlist,0); // 배열 0으로 초기화
        ArrayList<Integer> remainderlist[] = new ArrayList[13];
        for(int i = 0; i < 13; i++){
            remainderlist[i] = new ArrayList<>();
        }
        for(int i = 0; i < list.length; i++){
            if(list[i] % 4 == 0){ //클로버
                remainderlist[0].add(list[i]);
            } else if(list[i] % 4 == 1){ // 하트
                remainderlist[1].add(list[i]);
            } else if(list[i] % 4 == 2){ //다이아
                remainderlist[2].add(list[i]);
            } else {
                remainderlist[3].add(list[i]);
            }
        }
        //배열 길이가 5개인것 = 같은 문양 5개
        for(int i = 0; i < 4; i++){
            if(remainderlist[i].size() >= 5){
                // 연속된 5개의 숫자인지 판별
                for(int j = 0; j < (remainderlist[i].size() - 1); j++){
                    if(remainderlist[i].get(j) == remainderlist[i].get(j + 1) -4){
                        cnt++;
                    }
                }
                if(cnt == 4){ //스트레이트 플러쉬 완성
                    // 점수 반환, 무늬 * 10, 숫자 * 1, 기본점수 1000
                    // 무늬점수 = i * 10
                    // 숫자점수 =
                    return (900 + (i * 10 ) + (remainderlist[i].get(remainderlist[i].size() - 1) / 4));
                }
            }
        }
        //3. 포카드////////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < 13; i++){
            remainderlist[i].clear();
        }
        for(int i = 0; i < list.length; i++){
            int n = list[i] / 4;
            remainderlist[n].add(list[i]);
        }
        for(int i = 12; i >= 0; i--){
            if(remainderlist[i].size() == 4){// 카드 4장 같을 때, 포카드
                return (800 + i); //포카드 점수 반환//////////////////////////////////////////////
            }
        }
        //4. 풀하우스//////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 12; i >=0; i--){
            if (remainderlist[i].size() == 3){// 카드 3장 같을 때, 풀하우스 or 트리플
                for(int j = 12; j >= 0; j--){
                    //페어 찾기, 있으면 풀하우스 없으면 트리플
                    if(j != i){ // 트리플 찾은 곳 제외
                        if(remainderlist[j].size() == 2){//페어찾음 == 풀하우스800
                            // 풀하우스 점수 = 가장 높은 트리플 숫자가 높은 점수
                            return (700 + i);
                        }
                    }
                }
            }
        }
        //5. 플러쉬700/////////////////////////////////////////////////////////////////////////////////////////////////
        // 같은 랭킹일 경우 가장 높은 숫자를 보유한 플레이어가 승리
        for(int i = 0; i < 13; i++){
            remainderlist[i].clear();
        }
        for(int i = 0; i < list.length; i++){
            if(list[i] % 4 == 0){ //클로버
                remainderlist[0].add(list[i]);
            } else if(list[i] % 4 == 1){ // 하트
                remainderlist[1].add(list[i]);
            } else if(list[i] % 4 == 2){ //다이아
                remainderlist[2].add(list[i]);
            } else { // 스페이드
                remainderlist[3].add(list[i]);
            }
        }
        for(int i = 0; i < 4; i++){
            if(remainderlist[i].size() >=5){
                return (600 + remainderlist[i].get(remainderlist[i].size() - 1));
            }
        }
        //6. 마운틴600/////////////////////////////////////////////////////////////////////////////////////////////////
        //7. 스트레이트400/////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < 13; i++){
            remainderlist[i].clear();
        }
        for(int i = 0; i < list.length; i++){
            int n = list[i] / 4;
            remainderlist[n].add(list[i]);
        }
        if((remainderlist[0].size() >= 1) && (remainderlist[12].size() >= 1)
                && (remainderlist[11].size() >= 1) && (remainderlist[10].size() >= 1) && (remainderlist[9].size() >= 1)){
            //마운틴
            return (500 + remainderlist[0].get(remainderlist[0].size() - 1));
        } else { // 스트레이트 구하기
            for(int i = 0; i < list.length - 4; i++){
                if((remainderlist[i].size() >= 1) && (remainderlist[i + 1].size() >= 1)
                        && (remainderlist[i + 2].size() >= 1) && (remainderlist[i + 3].size() >= 1)
                        && (remainderlist[i + 4].size() >= 1)){
                    return (400 + remainderlist[i + 4].get(remainderlist[i + 4].size() - 1));
                }
            }
        }
        //8. 트리플300/////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < 13; i++){
            remainderlist[i].clear();
        }
        for(int i = 0; i < list.length; i++){
            int n = list[i] / 4;
            remainderlist[n].add(list[i]);
        }
        for(int i = 12; i >= 0; i--){
            if(remainderlist[i].size() == 3){// 카드 3장 같을 때
                return (300 + i);
            }
        }
        //9. 투페어200 ////////////////////////////////////////////////////////////////////////////////////////////////
        cnt = 0;
        for(int i = 12; i >= 0; i--){
            if(remainderlist[i].size() == 2){// 페어 검사
                cnt++;
            }
        }
        for(int i = 12; i >= 0; i--){
            if(remainderlist[i].size() == 2){// 페어 검사
                if(cnt >= 2) {
                    return (200 + remainderlist[i].get(remainderlist[i].size() - 1));
                } else {
        //10. 원페어///////////////////////////////////////////////////////////////////////////////////////////////////
                    return (100 + remainderlist[i].get(remainderlist[i].size() - 1));
                }
            }
        }
        //11. 하이카드////////////////////////////////////////////////////////////////////////////////////////////////
        // a가 k 보다 높게 나와야됨
        for(int i = 0; i < 13; i++){
            remainderlist[i].clear();
        }
        for(int i = 0; i < list.length; i++){
            int n = list[i] / 4;
            remainderlist[n].add(list[i]);
        }
        if(remainderlist[0].size() >= 1){
            for(int i = 0; i < list.length; i++){
                if(list[i] < 4){
                    list[i] += 60;
                }
            }
        }
        Arrays.sort(list);
        return (list[list.length - 1]);
    }
    //족보 점수를 족보 이름으로 바꿈
    public String cardRanking(int score){
        if(score >= 1100){
            return "로열 스트레이트 플러쉬";
        }else if(score < 1000 && score >= 900){
            return "스트레이트 플러쉬";
        }else if(score < 900 && score >= 800){
            score -= 800;
            String result = getCardNumForRanking(score) + " 포카드";
            return result;
        }else if(score < 800 && score >= 700){
            score -= 700;
            String result = getCardNumForRanking(score) + " 풀하우스";
            return result;
        }else if(score < 700 && score >= 600){
            score -= 600;
            String result = getCardNum(score) + "탑 " + getCardPattern(score) + " 플러쉬";
            return result;
        }else if(score < 600 && score >= 500){
            return "마운틴";
        }else if(score < 500 && score >= 400){
            return "스트레이트";
        }else if(score < 400 && score >= 300){
            score -= 300;
            String result = getCardNumForRanking(score) + "트리플";
            return result;
        }else if(score < 300 && score >= 200){
            score -= 200;
            String result = getCardNum(score) + "투페어";
            return result;
        }else if(score < 200 && score >= 100){
            score -= 100;
            String result = getCardNum(score) + "원페어";
            return result;
        }else {
            String result = getCardNum(score) + " 탑";
            return result;
        }
    }



}
//showcard
/*
            2
           3 4
            1
플레이어는 1번, 나머지는 234순
◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇
■■  ■■  ■■  ■■  ■■  ■■  ■■  ■■
♣♣ ♡◇ ♠
11111111ㅤㅁㄴㅇ■■■■■■
┴ ┬ ├ ┤
─ │ ┌ ┐ └ ┘
▩
│ 8  ♤│
┌──────┐
│■■■■■■│
└──────┘
┌────┐
│■■■■│
│■■■■│
└────┘


        tab*2 + 1
         ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐
         │  1 │
         │  ♠ │
         └────┘  └────┘  └────┘  └────┘  └────┘  └────┘  └────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
┌──────┐                                                        ┌──────┐
│ 1  ♠ │                                                        │ 1  ♠ │
└──────┘                                                        └──────┘
         ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐  ┌────┐
         │  1 │
         │  ♠ │
         └────┘  └────┘  └────┘  └────┘  └────┘  └────┘  └────┘

카드는 12시부터 반시계방향으로 돌림
FLOP = die 다음턴부터 카드안줌



 */
// 족보
/*
점수 계산 :
♠ : 4
◆ : 3
♥ : 2
♣ : 1

1. 로열 스트레이트 플러쉬 (=로티플) ---> 1100
    : 5장의 카드가 모두 같은 무늬이면서 10, J, Q, K, A 연달아 있는 경우
    = 39, 43, 47, 51, 3 .....
2. 스트레이트 플러쉬 (=스티플)
    : 5장의 카드가 모두 같은 무늬이면서 연속된 숫자로 되어 있는 경우
    -> 동일한경우 가장 높은 숫자비교 -> 모양비교
    = (n%4 == 0 || 1 || 2 ||3) && 반복문((n+1) - n = 4)
3. 포카드 (=포카)
    : 4장의 카드가 같은 숫자로 되어 있을 경우 { ex. 0,1,2,3  4,5,6,7}
    = n/4가 같은 값 4개
4. 풀하우스 (=집)
    : 같은 숫자 3장과 같은 숫자 2장으로 되어 있는 경우
    = n/4가 값은 값 2개 + n/4이 같은 값 3개
5. 플러쉬
    : 5장 카드 모두 무늬가 같은 경우
    = n( c%4 == 0 || 1 || 2 || 3) == 5
6. 마운틴
    : 5장의 카드의 숫자가 무늬에 상관없이 10, J, Q, K, A로 연속 될 경우, 스트레이트 중 가장 높은 족보
    = 33~36 1개, 37~40 1개, 41~44 1개, 45~48 1개, 49~52 1개
7. 스트레이트 (=줄)
    : 5장 카드의 숫자가 무늬에 상관없이 연속 될 경우
    =
8. 트리플 (=봉)
    : 3장 카드의 숫자가 같은 경우 (무늬는 상관없음)
    = n/4가 같은 값 3개
9. 투페어
    : 2장 카드 숫자가 같은 카드가 두 쌍 있을 경우
    = n/4가 같은 값 2개 * 2
10. 원페어
    : 2장 카드의 숫자가 같은 카드가 한 쌍 있을 경우
    = n/4가 같은 값 2개
뉴포커에서는 7포커의 룰과 같이 7장의 카드 중 가장 높은 5장의 카드로 족보를 만듭니다. 이는 뉴포커의 기본 룰입니다.

뉴포커에서는 동일 족보의 경우 가장 높은 5장의 카드 중 족보를 뺀 나머지 카드의 숫자로 승부를 결정합니다.

예를 들어 "4, 3투페어" 동률일 경우 해당 패를 제외한 가장 높은 카드의 숫자를 비교해서 승부를 결정합니다.

만약, 나머지 한장의 카드 숫자까지 동률일 경우 4의 무늬 중 높은 무늬로 승부를 결정합니다.

높은 무늬의 순서는 "♠(스페이드)>◆(다이아)>♥(하트)>♣ (클로버)" 순입니다.

백스트레이트는 제외했습니다다

*출처 뉴커*
*/