import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] location;
	static boolean[] isSelected;
	static int[][] picked;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			isSelected = new boolean[N+2];
			picked = new int[N][2];
			location = new int[N+2][2]; //x좌표, y좌표, 0은 회사,1은 집
			for(int i=0; i<N+2; i++) {
				location[i][0] = Integer.parseInt(st.nextToken());
				location[i][1] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.deepToString(location));
			permu(0);
			System.out.println("#"+tc+" "+ans);
			
		}

	}
	public static void permu(int depth) {
		if(depth==N) {
			int temp = 0;
			temp += range(location[0],picked[0]);
			for(int i=0; i<N; i++) {
				if(i==N-1) {
					temp += range(picked[i], location[1]);
					break;
				}				
				temp += range(picked[i], picked[i+1]);						
			}
			ans = Math.min(ans, temp);
			return;
		}	
		
		for(int i=2; i<N+2; i++) {
			if(isSelected[i] ==true) continue;
			isSelected[i] = true;
			picked[depth][0] = location[i][0];
			picked[depth][1] = location[i][1];
			permu(depth+1);
			isSelected[i] = false;
			
			
		}
		
	}
	
	public static int range(int[] a, int[]b) {
		int y = Math.abs(a[0]-b[0]);
		int x = Math.abs(a[1]-b[1]);
		return y+x;
	}

}