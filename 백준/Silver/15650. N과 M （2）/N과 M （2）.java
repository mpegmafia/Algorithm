import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int[] nums;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		comb (0, 1);
		System.out.println(sb.toString());
		

	}
	
	private static void comb(int cnt, int start) {
		if (cnt==M) {
			for(int i=0; i<nums.length; i++) sb.append(nums[i]+" ");
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N; i++) {
			nums[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}

}