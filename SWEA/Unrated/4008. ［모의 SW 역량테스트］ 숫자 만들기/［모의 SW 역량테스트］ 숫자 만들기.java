import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 모든 경우의 수 다찾아야함
	 * 연산자의 개수가 남아있다면 들어가기
	 * 들어갔다가 나온 후에 빼주는것도 잊지말기
	 * 연산자 순서는 + - *  /
	 */

	static int N;
	static int[] oper;
	static int[] nums;
	static int maxV;
	static int minV;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			oper = new int[4];
			nums = new int[N];
			
			maxV = Integer.MIN_VALUE;
			minV = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			DFS(1,nums[0]);
			int ans = maxV-minV;
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}
	
	static void DFS(int depth, int val) {
		if(depth==N) {
			maxV = Math.max(maxV, val);
			minV = Math.min(minV, val);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i]==0) continue;
			
			oper[i]--;
			if(i==0) {
				DFS(depth+1, val+nums[depth]);
			}
			else if(i==1) {
				DFS(depth+1, val-nums[depth]);
			}
			else if(i==2) {
				DFS(depth+1, val*nums[depth]);
			}
			else if(i==3) {
				DFS(depth+1, val/nums[depth]);
			}
			oper[i]++;
		}
		
		
		
		
	}
	
	

}