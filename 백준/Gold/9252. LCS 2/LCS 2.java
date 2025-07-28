import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/*
두 문자열이 주어진다.
두 문자열의 LCS, 최장 공통 부분 수열을 찾아라.
---------------------------
문자열은 알파벳 대문자로만 이루어져있고 최대 1000글자이다.
---------------------------
음... 시간제한이 0.1초이니까 이중포문으로 돌아도 안전? 1000 x 1000 = 100만
이 이중포문안에 모든걸 다 비교하려면 역시 dp밖에 없다.

STR1과 STR2를 비교할때 str1을 하나씩 늘려가며 str2의 고정된 문자열과 계속 비교한다.
이때 같은 문자열이 나오면 +1해주고 아니라면 패스.
이 과정에서 지금까지의 최장 LCS값을 계속보관하는 것이 포인트인데, 바로 행-1값 혹은 열-1값의 최고값을 가져오는 것이다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] b = br.readLine().toCharArray();
        char[] a = br.readLine().toCharArray();
        int[][] dp = new int[a.length + 1][b.length + 1];

        for(int i=1; i<=a.length; i++){
            for(int j=1; j<=b.length; j++){
                if(a[i-1] == b[j-1]){
                    dp[i][j] = dp[i-1][j-1] +1;
                } else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i = a.length, j = b.length;
        int ans = dp[i][j];

//        for(int k = 0; k<dp.length; k++){
//            System.out.println(Arrays.toString(dp[k]));
//        }

        Stack<Character> stack = new Stack<>();
        while(i>0 && j>0){
            if(dp[i][j] == dp[i][j-1]){
                j--;
            }
            else if(dp[i][j] == dp[i-1][j]){
                i--;
            }
            else if(dp[i][j]-1 == dp[i-1][j-1]){
                stack.push(a[i-1]);
                i--;
                j--;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(ans);
        System.out.println(sb.toString());

    }
}
