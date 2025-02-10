import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;

/*
한번에 최대한 많은 상자를 안에 담기(마트료시카)
===========================
입력:
    n (1<=n<=1000)
    각 상자의 크기 (1000이하 자연수)
===========================
한 상자를 선택했을때 넣거나 안넣거나 할 수 있음
완탐(백트래킹 or 부분집합)을 했을때 시간복잡도는 2^1000
그리디 or DP?
1 5 2 3 7
최장증가수열이라는 dp 유형
현재 선택한 수를 마지막으로 갖는 수열이 가질수있는 최장수를 dp배열에 넣으면 된다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i]>arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int ans = 0;
        for(int i=0; i<n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}