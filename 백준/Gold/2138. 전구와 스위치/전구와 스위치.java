import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
i번 전구를 누르면 i-1, i+1번 전구 상태가 바뀜
N개의 전구들이 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때
그상태를 만들고자 스위치를 "최소" 몇번 눌러야 하는지
----------------------
N (2<=N<=100,000)
N개의 전구 상태
N개의 목표 상태
불가능한 경우도 있다!
----------------------
N이 10만까지니까 DP인가?
일단 완탐은 모든 스위치를 키거나 끄는거니까 2^10만이므로 불가능
잘 생각해보면 i번째 스위치는 i-1번스위치와 i+1번 스위치의 영향을 받고 나머지는 상관이 없다.
즉, 순서대로 생각해봐도 된다는 뜻이고 그 상태에서 상태를 결정짓지 못한다면 그것은 못만든다는 의미

 */
public class Main {
    static char[] arr;
    static char[] target;
    static int ans = 100_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        target = br.readLine().toCharArray();
        solution(true);
        solution(false);
        System.out.println(ans==100_001?-1:ans);
    }

    public static void solution(boolean flag){
        char[] tmpArr = new char[arr.length];
        System.arraycopy(arr, 0 , tmpArr, 0, arr.length );
        int count = 0;
        if(flag){
            for(int i=1; i<tmpArr.length-1; i++){
                if(tmpArr[i-1] != target[i-1]){
                    count++;
                    onOff(tmpArr, i);
                }
            }
            if(tmpArr[tmpArr.length-2] != target[tmpArr.length-2]){
                tmpArr[tmpArr.length-2] = tmpArr[tmpArr.length-2]=='0'? '1' : '0';
                tmpArr[tmpArr.length-1] = tmpArr[tmpArr.length-1]=='0'? '1' : '0';
                count++;
            }
            answerCheck(tmpArr, count);
        }
        else{
            tmpArr[0] = tmpArr[0]=='0'? '1' : '0';
            tmpArr[1] = tmpArr[1]=='0'? '1' : '0';
            count++;
            for(int i=1; i<tmpArr.length-1; i++){
                if(tmpArr[i-1] != target[i-1]){
                    count++;
                    onOff(tmpArr, i);
                }
            }
            if(tmpArr[tmpArr.length-1] != target[tmpArr.length-1]){
                tmpArr[tmpArr.length-2] = tmpArr[tmpArr.length-2]=='0'? '1' : '0';
                tmpArr[tmpArr.length-1] = tmpArr[tmpArr.length-1]=='0'? '1' : '0';
                count++;
            }
            answerCheck(tmpArr, count);

        }
    }

    public static void onOff(char[] arr, int idx){
        arr[idx-1] = arr[idx-1] == '0'? '1' : '0';
        arr[idx] = arr[idx] == '0'? '1' : '0';
        arr[idx+1] = arr[idx+1] == '0'? '1' : '0';
    }

    public static void answerCheck(char[] arr, int count){
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=target[i]){
                return;
            }
        }
        ans = Math.min(ans, count);
    }

}
