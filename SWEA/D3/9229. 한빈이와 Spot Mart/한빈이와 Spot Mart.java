import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int ans;
	static int[] snack;
	static int[] picked;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			snack = new int[N];
			picked = new int[2];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			combi(0, 0);
            if(ans==0) System.out.println("#"+tc+" "+"-1");
			else System.out.println("#"+tc+" "+ans);
			
		}

	}
	private static void combi(int cnt, int start) {
		if(cnt==2) {
			int temp = 0;
			for(int i=0; i<2; i++) {
				temp += picked[i];
			}
			if (temp <=M) {
				ans = Math.max(ans, temp);
			}
			return;
		}
		for(int i=start; i<N; i++) {
			picked[cnt] = snack[i];
			combi(cnt+1, i+1);
		}
		
		
	}

}