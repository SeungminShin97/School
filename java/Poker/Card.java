import java.util.ArrayList;

public class Card {
    private ArrayList<Integer> deallist;

    public Card() {
        deallist = new ArrayList<>();
    }
    // 랜덤으로 뽑은 숫자 기록
    public void pushDeallist(int n) {
        deallist.add(n);
    }
    // deallist 길이 반환
    public int getDeallistLength() {
        return deallist.size();
    }
    //deallist n번째 배열의 숫자 반환
    public int getDeallistNum(int n) { return deallist.get(n); }
}
/*
카드 순서
♣♤◇♠
*/
