import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * N개의 식재료(짝수)
	 * N/2 개로 나누어 A와 B음식을 만든다
	 * 식재료 i와 j가 있을때 둘의 시너지를 나타내는 그래프 S가 있다
	 * S의 i행 j열이 두 음식의 시너지다
	 * ij와 ji를 더한게 음식의 맛이 된다
	 * 이렇게 나오는 두 음식의 맛의 차이를 최소로 구하는게 목표다
	 * N은 4이상 16이하
	 * N개중에서 N/2개를 조합해가지고 뽑는문제
	 * 자연스럽게 안뽑힌애들이 나머지팀이 된다
	 * 맛의 시너지를 구하는 법도 조합으로 다 구하면된다.
	 */
	static int[][] graph;
	static int[] picked;
	static int[] unpicked;
	static int N;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N]; //시너지
			picked = new int[N/2];
			unpicked = new int[N/2];
			ans = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combi(0,0);
			
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}
	public static void combi(int depth, int start) {
		if(depth==N/2) {
			int idx = 0;
		A:	for(int i=0; i<N; i++) {
				for(int j=0; j<N/2; j++) {
					if(i==picked[j]) continue A;
				}
				unpicked[idx++] = i;
			}
			int diff = Math.abs(sum(picked) - sum(unpicked));
			ans = Math.min(ans, diff);
			return;		
			
		}
		
		
		for(int i=start; i<N; i++) {
			picked[depth] = i;
			combi(depth+1, i+1);
		}
	}
	
	
	public static int sum(int[] ingredient) {
		int temp = 0;
		for(int i=0; i<ingredient.length-1; i++) {
			for(int j=i+1; j<ingredient.length; j++) {
				temp += graph[ingredient[i]][ingredient[j]];
				temp += graph[ingredient[j]][ingredient[i]];
			}
		
		}
		return temp;
	}

}