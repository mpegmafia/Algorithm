import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int K;
	static int N;
	static int M;
	static boolean[][] graph;
	static boolean[][][] visited;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static int[][] horse = {
			{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}
	};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N][M];
		visited = new boolean[K+1][N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = (Integer.parseInt(st.nextToken())==1)? true : false;
			}
		}
		
		for(int i=K; i>=0; i--) {
			BFS(i);
			
		}
		System.out.println(ans==Integer.MAX_VALUE? -1:ans);
		
		
	}
	static void BFS(int curK) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0,curK,0});
		
		visited[curK][0][0]= true;
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			int restK = q.peek()[2];
			int cnt = q.peek()[3];
			q.poll();
			
			if(y==N-1&&x==M-1) {
//				System.out.println("y : "+y+" x : "+x+" : restK : "+restK+ " cnt : "+cnt);
				ans = Math.min(ans, cnt);
				break;
			}
			
			for(int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(!isInRange(ny, nx)) continue;
				if(visited[restK][ny][nx]) continue;
				if(graph[ny][nx]) { // 벽일때
				}
				else{
					visited[restK][ny][nx] = true;
					q.offer(new int[] {ny, nx, restK, cnt+1});
				}
			}
			if(restK>0) { // horse점프가 남아있을 때 
				for(int j=0; j<8; j++) {
					int hy = y+horse[j][0];
					int hx = x+horse[j][1];
//							System.out.println("hy : "+hy+" hx : "+hx);
					if(!isInRange(hy, hx)) continue;
					if(visited[restK-1][hy][hx]) continue;
					if(graph[hy][hx]) continue;
					visited[restK-1][hy][hx] = true;
					q.offer(new int[] {hy, hx, restK-1, cnt+1});
				}
			}
				
				
			
			
		}
		
		
		
		
	}
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}

}