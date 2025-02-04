import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
1~N까지의 사과나무
초기 높이는 모두 0
물뿌리개 2개를 준비, 하나는 1, 하나는 2, 둘이합쳐서 3도 가능
나무가 없는 토양에는 사용 불가능, 두개 동시에 사용해야 함
특정 사과나무 높이 배치가 가능한지 여부를 출력하자
========================
입력:
    N (1<=N<+100,000)
    N개의 정수
=======================
그리디인가?
3으로 나누어지던가 아니면 1,2를 여러군데 뿌려서 나누어지던가
10만개를 전부 3 나머지 연산으로 함
그 후 1,2갯수가 똑같으면 yes, 똑같지 않으면 no?
=>음...아닌거같다... 4 4 4 라고 가정했을 때 3으로 나누면 1이 3개가 나오지만
    사실 가능한 경우이기 때문
그러면 역순으로 정렬해서 3으로 나누고 1이면 뒤숫자에 2를빼고
2면 1를빼는 느낌으로?
-------------------------------
답보니까 그냥 2를 몇번줄수있느냐가 핵심인 문제였따
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            cnt += num/2;
        }

        if(sum%3==0 && sum/3<=cnt) System.out.println("YES");
        else System.out.println("NO");





    }
}