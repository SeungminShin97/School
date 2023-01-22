import java.util.ArrayList;
import java.util.Random;

public class Ascending {
    public static void main(String args[]){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        int rnd = 0;


      for(int i = 0; i < 10; i++){
            if(list.size() == 0) {
                rnd = random.nextInt(41) + 10;
                list.add(rnd);
                System.out.println(rnd + " : " + list.toString());
            }
            else{
                int a = 1;
                while(a != 0){
                    //임의의 수 생성
                    rnd = random.nextInt(41) + 10;
                    //중복검사
                    for(int j = 0; j < list.size(); j++){
                        if(list.get(j) == rnd) {
                            a++;
                        }
                    }
                    a = 1;
                    if(a == 1){
                        a = 0;
                        System.out.print(rnd + " : ");
                    }

                }
                boolean bool = true;
                int cnt = 0;
                while(bool == true){
                    if(cnt < list.size()){
                        if(rnd > list.get(cnt))
                            cnt++;
                        else{
                            list.add(cnt, rnd);
                            System.out.println(list.toString());
                            bool = false;
                        }
                    }
                    else{
                        list.add(rnd);
                        System.out.println(list.toString());
                        bool = false;
                    }
                }
            }
        }
    }
}
