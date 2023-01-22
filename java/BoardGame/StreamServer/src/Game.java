import java.util.Random;

public class Game {
    private int entern = 0;
    /**
     * 0 : 이름//
     * 1 : 현재 위치//
     * 2 : 스킵 해야되면 1, 아니면 0
     */
    private String[][] narr;
    private int[] map;

    public Game(int n) {
        narr = new String[n][3];
    }

    /**
     * 1 : Jump (앞으로 2칸)
     * 2 : Back (뒤로 3칸)
     * 3 : Skip (한 턴 쉬기)
     * @param n = 맵의 크기
     */
    public void set(int n) {
        map = new int[n];
        Random random = new Random();
        for(int i = 0; i < (map.length / 3); i++) {
            int rand = random.nextInt(map.length - 1) + 1;
            while(map[rand] != 0) {
                rand = random.nextInt(map.length);
            }
            map[rand] = random.nextInt(3) + 1;
        }
    }

    public void enter(String s) {
        narr[entern][0] = s;
        narr[entern][1] = "0";
        narr[entern][2] = "0";
        entern++;
    }

    public void dice(int i, int n) {
        narr[i][1] = String.valueOf(Integer.parseInt(narr[i][1]) + n);
        if(Integer.parseInt(narr[i][1]) < map.length - 1) {
            while(true) {
                if(map[Integer.parseInt(narr[i][1])] == 1) {
                    if(Integer.parseInt(narr[i][1]) + 2 >= map.length - 1) {
                        narr[i][1] = Integer.toString(map.length - 1);
                        break;
                    }
                    else
                        narr[i][1] = Integer.toString(Integer.parseInt(narr[i][1]) + 2);
                }
                else if(map[Integer.parseInt(narr[i][1])] == 2) {
                    if(Integer.parseInt(narr[i][1]) < 3)
                        narr[i][1] = "0";
                    else
                        narr[i][1] = Integer.toString(Integer.parseInt(narr[i][1]) - 3);
                }
                else if(map[Integer.parseInt(narr[i][1])] == 3) {
                    narr[i][2] = "1";
                    break;
                }
                else
                    break;
            }
        }
    }

    public void setSkip(int i) { narr[i][2] = "0"; }

    public String getMap(int l) {
        StringBuilder sb = new StringBuilder();
        sb.append("J = Jump 2   B = Back 3  S = Skip next turn\n");
        for(int i = 0; i < map.length; i++) {
            if(map[i] == 0)
                sb.append("[0] " );
            else if(map[i] == 1)
                sb.append("[J] " );
            else if(map[i] == 2)
                sb.append("[B] " );
            else
                sb.append("[S] " );
        }
        sb.append("\n");
        for(int i = 0; i < getlocation(l); i++)
            sb.append("    ");
        sb.append(" ↑\n");
        for(int i = 0; i < getlocation(l); i++)
            sb.append("    ");
        sb.append(narr[l][0]);
        return sb.toString();
    }

    public int getSkip(int i) { return Integer.parseInt(narr[i][2]); }
    public int getlocation(int i) { return Integer.parseInt(narr[i][1]); }
    public int getEntern() { return entern;}
}