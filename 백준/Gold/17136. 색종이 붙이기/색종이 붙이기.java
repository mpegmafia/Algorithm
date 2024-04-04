import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 1x1, 2x2, 3x3, 4x4, 5x5 색종이가 5개씩 있음
	 * 0에는 색종이를 붙이면 안됨
	 * 1에다가 다 색종이를 붙여야함
	 * 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 갯수
	 * -------
	 * 그래프를 1만날때까지 순회함
	 * 1을 만나면 몇까지 붙일 수 있는지 체크해봄
	 * 체크후에 붙일 수 있는 모든 색종이들을 붙임
	 * 	이 과정에서 1을 붙였다면 1을 붙인상태에서 끝까지 가고 그다음 1을 다시 뗀다음 2를 붙여보고 하는식으로 가야함
	 * 다 돌았다면 다 0인지 체크 후 정답을 갱신해줌
	 * 
	 * 
	 */

	static int[][] graph;
	static int ans = Integer.MAX_VALUE;
	static int[] nums = {5,5,5,5,5};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		graph = new int[10][10];
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0,0);
		
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);

	}
	
	static void DFS(int idx, int cnt) {
		if (cnt>=ans) return;
		
		if( idx==100) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		int r = idx/10;
		int c = idx%10;
		
		if (graph[r][c]==0) {
			DFS(idx+1,cnt);
		}
		
		else {
			for(int range=0; range<5; range++) {
				if(nums[range]==0) continue;
				if(r+range>=10||c+range>=10) continue;
				if(!rangeChk(r, c, range)) continue;
				nums[range]--;
				put(r, c, range);
				DFS(idx+1, cnt+1);
				unPut(r, c, range);
				nums[range]++;
			}
			
		}
		
	}
	
	
	static boolean rangeChk(int r, int c, int range) {
		
		for(int i=r; i<=r+range; i++) {
			for(int j=c; j<=c+range; j++) {
				if(graph[i][j]==0) return false;
			}
		}
		return true;
	}
	
	static void put(int r, int c, int range) {
		
		for(int i=r; i<=r+range; i++) {
			for(int j=c; j<=c+range; j++) {
				graph[i][j] = 0;
			}
		}
		
		
	}
	
	static void unPut(int r, int c, int range) {
		for(int i=r; i<=r+range&&i<10; i++) {
			for(int j=c; j<=c+range&&j<10; j++) {
				graph[i][j] = 1;
			}
		}
		
	}

}