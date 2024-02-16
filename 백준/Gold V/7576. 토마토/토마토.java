import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * M, N(가로 세로칸의 수)
	 * 1은 익토
	 * 0은 썩토
	 * -1은 노토
	 * 만약 다 돌았는데 0이 하나라도 있다면 -1 출력
	 * 아니라면 최소날짜출력
	 */
	static int N;
	static int M;
	static int[][] graph;
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static int ans = 0;
	
	static Queue<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (graph[i][j]==1) {
					q.offer(new int[] {i, j, 0});
				}
			}
		}
		
		BFS();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (graph[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(ans);
		
		
		

	}
	public static void BFS() {
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			int day = q.peek()[2];
			ans = Math.max(day, ans);
			q.poll();
			for(int i=0; i<4; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(isInRange(ny, nx) && graph[ny][nx] == 0) {
					q.offer(new int[] {ny, nx, day+1});
					graph[ny][nx] = 1;					
				}				
			}
			
		}
		
		
		
	}
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}
	
}