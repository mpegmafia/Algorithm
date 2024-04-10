import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 순차적인 조합 생성? 
	 */

	static int N;
	static int B;
	static int[] heights;
	static boolean flag;
	static int[] selected;
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			flag = false;
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE;
			
			for(int i=1; i<=N; i++) {
				selected = new int[i];
				comb(0, 0, i);
				if(flag) break;
				
			}
			ans = ans-B;
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}
	
	static void comb(int depth, int start, int max) {
		if(depth==max) {
			int temp = 0;
			for(int i=0; i<max; i++) {
				temp += heights[selected[i]];
			}
			if(temp>=B) {
				ans = Math.min(ans, temp);
			}
			return;
		}
		
		for(int i = start; i<N; i++) {
			selected[depth] = i;
			comb(depth+1, i+1, max);
			
		}
		
		
		
	}

}