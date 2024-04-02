import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * dp[3][N+1][N+1] 로 선언
	 * 0은 가로
	 * 1은 대각
	 * 2는 세로 방향
	 * 가로방향은 가로방향의 x-1이나 대각선방향의 x-1을 더하면됨
	 * 세로방향은 세로방향의 y-1이나 대각선방향의 y-1을 더하면됨
	 * 대각선 방향은 가로방향의 y-1,x-1이나 세로방향의 x-1, y-1, 대각선방향의 y-1, x-1을 더하면됨
	 * 
	 * 
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		int[][][] dp = new int[3][N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1; i<N; i++) {
			if(graph[0][i]==1) break;
			dp[0][0][i] = 1;
			
		}
		
		
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(graph[i][j]==0) {
					dp[0][i][j] =  dp[0][i][j-1] + dp[1][i][j-1];
					dp[2][i][j] =  dp[2][i-1][j]+ dp[1][i-1][j];
				}
				if(graph[i][j]==0&&graph[i-1][j]==0&&graph[i][j-1]==0) {
					dp[1][i][j] =  dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
				}
			}
		}
		
		
		
		
		
		int ans = dp[0][N-1][N-1]+dp[1][N-1][N-1]+dp[2][N-1][N-1];
		System.out.println(ans);
	}

}