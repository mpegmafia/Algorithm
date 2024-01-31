import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 수열의 길이 N, 명령문의 갯수 M
	 * 길이 N의 수열
	 * M개의 줄동안 i번째수부터 j번째 수 까지의 합
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N+1];
		int[] sum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1]+nums[i];
			
		}
		
//		sum[0] = nums[0];
//		
//		for (int i=1; i<N; i++) {
//			sum[i] = sum[i-1]+nums[i];
//		}
		
		
		
		for (int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			sb.append(sum[j]-sum[i-1]+"\n");
			
		}
		System.out.println(sb.toString());
		
		
		

	}

}