import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 1,2,3을 이용해 정수n만들기
	 * 처음에 1이나 2나 3으로 시작
	 * 들어가서 1이나 2나 3을 더하기
	 * 만약 더한 값이n이면 cnt++;
	 * n보다 크면 리턴
	 * --------------
	 * dp로 할때는 dp[n+1] 까지 만들고 dp[3]까지 초기화
	 * 
	 */
	
	static StringBuilder sb = new StringBuilder();	
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int[]dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int tc=1; tc<=T; tc++) {
			int n = Integer.parseInt(br.readLine());
//			ans = 0;
//			
//			DFS(1, n);
//			DFS(2, n);
//			DFS(3, n);
			
			for(int i=4; i<=n; i++) {
				dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
			}
			
			sb.append(dp[n]+"\n");
		}
		System.out.println(sb);

	}
//	public static void DFS(int value, int target) {
//		if(value==target) {
//			ans++;
//			return;
//		}
//		if(value>target) return;
//		DFS(value+1, target);
//		DFS(value+2, target);
//		DFS(value+3, target);
//	}
//	

}