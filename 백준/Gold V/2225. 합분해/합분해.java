import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] dp = new long[N+1];
		for(int i=0; i<N+1; i++) {
			dp[i] = 1;
		}
		for(int i=1; i<K; i++) {
			for(int j=1; j<N+1; j++) {
				dp[j] = (dp[j-1]+dp[j])%1000_000_000;
			}
		}
		System.out.println(dp[N]%1000_000_000);


	}

}