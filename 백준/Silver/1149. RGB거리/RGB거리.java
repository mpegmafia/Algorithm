import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] RGBs = new int[3][N+1];
		int[][] dp = new int[3][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			RGBs[0][i] = a; //
			RGBs[1][i] = b;
			RGBs[2][i] = c; //
		}
		
		dp[0][1] = RGBs[0][1];
		dp[1][1] = RGBs[1][1];
		dp[2][1] = RGBs[2][1];
		
		for(int i=2; i<=N; i++) {
			dp[0][i] = RGBs[0][i]+ Math.min(dp[1][i-1], dp[2][i-1]);
			dp[1][i] = RGBs[1][i]+ Math.min(dp[0][i-1], dp[2][i-1]);
			dp[2][i] = RGBs[2][i]+ Math.min(dp[1][i-1], dp[0][i-1]);
		}
		System.out.println(Math.min(Math.min(dp[0][N],dp[1][N]),dp[2][N]));

	}

}