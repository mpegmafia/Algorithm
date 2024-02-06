import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static int[] picked;
	static int ans;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		picked = new int[N];
		ans = 0;
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		comb(0,0);
		System.out.println(ans);
		
		
	}
	
	static void comb(int depth, int start) {
		if(depth==3) {
			int temp = 0;
			for(int i=0; i<N; i++) {
				temp+= picked[i];
			}
			if(temp<=M) {
				ans = Math.max(ans, temp);
			}
			return;
		}
		
		
		for(int i=start; i<N; i++) {
			picked[depth] = nums[i];
			comb(depth+1, i+1);
		}
		
	}

}