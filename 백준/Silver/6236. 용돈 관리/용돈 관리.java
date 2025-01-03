import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    N일동안 M번의 인출을 함
    인출할 때 K원을 인출하고 그 돈으로 하루 생활이 가능하다면 그대로 사용, 불가능하면 남은 금액을
        통장에 다시 넣고 다시 K원을 인출
    다만 현우는 M이라는 숫자를 좋아하기 때문에 정확히 M번을 맞추기 위해 돈이 지출금액보다 많더라도 그냥 다시 집어
        넣고 빼는 행위를 할 수 있다.
    이때 현우는 돈을 아끼기 위해 금액 K를 최소화하기로 했다. 현우가 필요한 최소 금액 K를 계산하는 프로그램 만들자
    ============================
    입력:
        N, M이 공백으로 주어짐 (1<=N<=100,000 , 1<=M<=N)
        2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다 (1<=금액<=10000)
    ============================
    다시집어넣고 빼는 행위가 가능하다는 것은 만약 K원을 사용했을 때 M번보다 적은 숫자가 나온다면 된다는 뜻
    브루트포스로 돌리기에는 100,000*10000이니까 안되고 이분탐색 알고리즘을 사용하면 될 것 같다.
    log10000 * 100,000 = 1,300,000 정도니까 가능

    먼저 금액의 최댓값 구한 후 그 값에서 반을 나누기
    그 나눈 값을 K로 넣은 후 비용들을 순회하며 M번 인출보다 적은지 큰지 확인
    M번보다 적게 나왔다면 정답으로 넣은 후 그거 보다 더 적은 값으로 돌아보기
    M번보다 크다면 그것도 더 큰 값으로 돌아봐야함

    종료조건 : 최소값이 최대값보다 클때
    1,3 일 때 2가 중간값 -> 1,1 -> 1이 중간값 -> 0 1=> 같아지면 종료
    -> 더 작게 내려가면 그냥 중간값을 최대값으로
    1,5 일 때 3이 중간값 -> 4,5 -> 4가 중간값 -> 5,5=> 5,5 => 5가 중간값,
    여기서 같아짐 처리하면 5는 계산이 안된다.
    -> while min<=max 로 처리하고
    max = temp -1
    min = temp +1 을 해주는게 맞음

    그냥 max값을 10억으로 놔두는건..?
     */
    static int ans = 10000;
    public static void main(String[] args) throws IOException {
        int N;
        int M;
        int[] expenses;
        int max = 1000000001; //10억 1
        int min = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        expenses = new int[N];
        for(int i=0; i<N; i++){
            expenses[i] = Integer.parseInt(br.readLine());
            //max = Math.max(max, expenses[i]);
        }

        while(min<=max){
            int temp = (min+max)/2;
            //만약 M번 안에 순회 가능하다면 K값 더 작게
            if(chk(expenses, temp, M)){
                max = temp-1;
                //정답 교체
                ans= temp;
            }
            //아니라면 K값 더 크게
            else{
                min = temp+1;
            }

        }

        System.out.println(ans);

    }
    public static boolean chk(int[] exp, int temp, int M){
        int cnt = 0;
        int balance = 0;
        for (int i=0; i<exp.length; i++){
            if(exp[i]>balance){
                cnt++;
                balance = temp;
                if(exp[i]>balance) return false;
                balance -= exp[i];
            }
            else balance -= exp[i];
        }
        return cnt <= M;
    }
}