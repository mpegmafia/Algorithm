import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		dp[0] = 0;
		dp[1] = 0;
		if(N==1) {
			System.out.println(dp[N]);
			return;
		}
		dp[2] = 1;
		if(N==2) {
			System.out.println(dp[N]);
			return;
		}
		dp[3] = 1;
		if(N==3) {
			System.out.println(dp[N]);
			return;
		}
		for(int i=4; i<=N; i++) {
			if(i%6==0) {
				dp[i] = Math.min(Math.min(dp[i/2], dp[i/3]),dp[i-1])+1;
			}
			
			else if(i%3==0) {
				dp[i] = Math.min(dp[i-1], dp[i/3])+1;
			}
			else if(i%2==0) {
				dp[i] = Math.min(dp[i-1], dp[i/2])+1;
			}
			else {
				dp[i] = dp[i-1]+1; 
			}
		}
		System.out.println(dp[N]);
	}

}