import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	//nCm 조합 가지수 구하기
	/*
	 * N,M 은 30까지, 30C15 = 1억 5천
	 * 테케까지 있으므로 dp로 해줘야함
	 */
	static int N;
	static int M;
	static int[][] dp;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		dp = new int[31][31];
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			sb.append(combi(M, N)+"\n");
			
			
			
			
			
		}
		System.out.println(sb);

	}
	
	public static int combi(int c, int r) {
		if(dp[c][r]!=0) return dp[c][r];		
		if (c==r)		return dp[c][r] = 1;
		if (r==0) return dp[c][r] = 1;
		return dp[c][r] = combi(c-1,r-1) + combi(c-1,r);
		
		
		
	}

}