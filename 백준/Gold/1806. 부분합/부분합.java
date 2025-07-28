import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
10000이하의 자연수로 이루어진 길이 N짜리 수열.
이 수열에서 연속된 수들의 부분합중 그 합이 S 이상이 되는 것 중.
가장 짧은 것의 길이를 구하라
-----------------------
N S (10<=N<=100,000), (0<S<=100,000,000)
수열
-----------------------
투포인터?
수열의 길이는 무조건 10이상이므로..
i와 j가 있을 때 둘 다 처음에 0부터시작.
그 후 S 이상인지 체크
    S이상이라면 i를 앞으로(만약 j보다 뒤로 갈시 길이는 1고정이니까 break
        현재 i값을 빼고 i앞으로
    S미만이라면 j를 앞으로(j가 끝에 도달했을시 break)
        다음 j값을 더해주기

 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        int sum = arr[0];
        int left = 0;
        int right = 0;
        while(left<=right && right<N){
            if(sum>=S){
                ans = Math.min(ans, right-left+1);
                sum -= arr[left];
                left++;
            }
            else {
                right++;
                if(right==N) break;
                sum += arr[right];
            }
        }

        System.out.println(ans==Integer.MAX_VALUE? 0 : ans);
    }
}
