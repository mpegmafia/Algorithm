import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 순열로 일단 순서부터 정하기
	 * 정해진 순서 기반으로 부분집합만들어서 왼쪽 오른쪽 나누기
	 * 왼쪽무게, 오른쪽무게 나눠서 만약 오른쪽무게가 더 커지면 리턴
	 */

	static int N;
	static int[] nums;
	static boolean[] isSelected;
	static int[] pick;
	static int ans;
	static int[] powerSet;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			isSelected = new boolean[N];
			pick = new int[N];
			
			permu(0);
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb);

	}
	
	public static void permu(int depth) {
		if(depth==N) {
			powerSet(0,0,0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			pick[depth] = nums[i];
			isSelected[i] = true;
			permu(depth+1);
			isSelected[i] = false;
		}
		
	}
	
	public static void powerSet(int depth,int left, int right) {
		if(left<right) {
			return;
		}
		if(depth==N) {
			ans++;
			return;
		}
		powerSet(depth+1, left+pick[depth], right);
		powerSet(depth+1, left, right + pick[depth]);

		
	}

}