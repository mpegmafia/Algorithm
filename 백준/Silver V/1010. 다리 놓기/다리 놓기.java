import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // MCN 조합문제 
	static int ans;
	static int[][] dp = new int[30][30];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = combi(M,N);
			
			System.out.println(ans);
			
		}

	}
	public static int combi(int n, int r) {
		if (dp[n][r] >0) return dp[n][r];
		
		if (n==r||r==0) {
			return dp[n][r] = 1;
		}
		
		return dp[n][r] = combi(n-1,r-1)+combi(n-1,r);
		
		
	}

}