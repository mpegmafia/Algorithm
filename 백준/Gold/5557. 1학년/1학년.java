import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 마지막엔 무조건 등호
	 * 중간에 넘겨지는 수가 0이상 20이하여야함
	 * 숫자의 개수 N이 주어짐
	 * 출력은 long 타입
	 * 오버플로우 안터짐?
	 * 일단 ㄱ
	 * 0부터 들어감, N-1이라면 등호로 같은지 비교 후 리턴
	 * 만약 중간 숫자가 음수거나 20 초과면 리턴
	 * -------------------------
	 * 당연히 시간초과 뜸
	 * DP로 어떻게 ?
	 * 한테이블은 +
	 * 한테이블은 -
	 * 처음 숫자와 마지막 숫자는 고정
	 * 0이 나오면 지금까지의 경우의수에서 2배가 됨
	 * ------------------
	 * 행은 0에서 20까지 열은 0에서 N까지 DP테이블 만들기 
	 * 
	 */
	static int N;
	static int[] nums;
	static long ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		long[][] dp = new long[21][N-1]; // N-1일때 멈출꺼니까 N-1개만 있어도 됨
		dp[Integer.parseInt(st.nextToken())][0] = 1; // 첨 숫자 초기화
		for(int i=1; i<N-1; i++) {
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<=20; j++) {
				if(dp[j][i-1]==0) continue;
				if(j+num>=0&&j+num<=20) {
					dp[j+num][i] += dp[j][i-1]; // 다른 수가 이미 이 자리수에 경우의수를 더했을 때도 고려해야함
				}
				if(j-num>=0&&j-num<=20) {
					dp[j-num][i] += dp[j][i-1];
				}
			}
			
		}
		System.out.println(dp[Integer.parseInt(st.nextToken())][N-2]);
		

	}
	

}