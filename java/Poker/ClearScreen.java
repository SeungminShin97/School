public interface ClearScreen {
    //화면 지우기
    default void clear() {
        for(int i = 0; i < 50; i++){
            System.out.println(" ");
        }
    }
    static void mainclear() {
        for(int i = 0; i < 50; i++){
            System.out.println(" ");
        }
    }
}
