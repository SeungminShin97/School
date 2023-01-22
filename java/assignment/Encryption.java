import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//random.nextInt(숫자)
public class Encryption {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int[] rndcnt;
        int r;
        String msg, enc = null, dec = null;

        System.out.print("Message : ");
        msg = sc.nextLine();

        rndcnt = new int[msg.length()];

        for(int i = 0 ; i < msg.length(); i++){
            boolean bool = false;
            for(char a : alpha){
                if(a == msg.charAt(i)){
                    //중첩 안되게 만들기
                    while(true){
                        int cnt = 0;
                        r = random.nextInt(25);
                        for(int asdf : rndcnt){
                            if(r == asdf) cnt++;
                        }
                        if(cnt == 0) break;
                    }
                    rndcnt[i] = r;           //랜덤값을 랜덤배열에 저장
                    if(enc == null) enc = Character.toString(alpha[r]); // r번째 알파벳을 enc에 저장
                    else enc += Character.toString(alpha[r]);
                    bool = true;
                }
            }
            //알파벳이 아닐경우 enc에 그대로 저장
            if(bool == false){
                if(enc == null) enc = Character.toString(msg.charAt(i));
                else enc += Character.toString(msg.charAt(i));
                rndcnt[i] = msg.charAt(i);
            }
        }
        System.out.println("Encoded : " + enc);

        char answer;
        System.out.print("Decoded(y/n)? : ");
        answer = sc.next().charAt(0);

        if(answer == 'y'){
            for(int i = 0; i < msg.length(); i++){
                if(msg.charAt(i) > 25){   //알파벳이 아닐때
                    char a = (char)(msg.charAt(i));
                    if(dec == null){
                        dec = Character.toString(a);
                    }
                    else{
                        dec += Character.toString(a);
                    }
                }
                else{
                    for(char a : alpha){
                        if(msg.charAt(i) == a){
                            if(dec == null){
                                dec = Character.toString(a);
                            }
                            else{
                                dec += Character.toString(a);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Decoded : " + dec);
        if(msg.equals(dec)){
            System.out.println("Decoding is successful.");
        }
    }
}
