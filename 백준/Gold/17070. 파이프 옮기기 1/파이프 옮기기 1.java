/*
 * 오른쪽
 * 오른쪽아래
 * 아래
 * 회전은 45도만 시킬 수 있다.
 * 오른쪽일시 오른쪽, 오른쪽아래만
 * 오른쪽아래일시 오른쪽, 아래, 오른쪽아래 셋다 가능
 * 아래일시 아래, 오른쪽아래만
 * 1,2 에서 N,N까지 가는 방법의 개수 구하기
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] graph;
	static int ans;
	
	static int[][] delta = { {0,1},
			{1,1},
			{1,0}		
	};
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0,1,0);
		System.out.println(ans);
		
	}
	private static void DFS(int y, int x, int dir) {
		if(y==N-1&&x==N-1) {
			ans++;
			return;
		}
		
		if(dir==0) {
			for(int i=0; i<2; i++) {
				int ny = y+delta[i][0];
				int nx = x+delta[i][1];
				if(!isInRange(ny, nx)) continue;
				if(chk(ny,nx,i)) continue;
				DFS(ny, nx, i);				
			}
			
		} else if(dir==1) {
			for(int i=0; i<3; i++) {
				int ny = y+delta[i][0];
				int nx = x+delta[i][1];
				if(!isInRange(ny, nx)) continue;
				if(chk(ny,nx,i)) continue;
				DFS(ny, nx, i);				
			}
			
		} else {
			for(int i=1; i<3; i++) {
				int ny = y+delta[i][0];
				int nx = x+delta[i][1];
				if(!isInRange(ny, nx)) continue;
				if(chk(ny,nx,i)) continue;
				DFS(ny, nx, i);				
			}
			
		}
		
		
		
		
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}
	
	public static boolean chk(int y, int x, int dir) {
		if(dir==1) {
			if(graph[y][x]==0&&graph[y-1][x]==0&&graph[y][x-1]==0) {
				return false;
			} else return true;
			
		} else {
			if(graph[y][x]==0) return false;
			else return true;
		}
		
	}

}