import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 각 칸을 지날때 최소로 지나서 N-1, N-1까지 가기
	 * visited를 Integer.maxValue로 초기화한 후 그 값보다 낮으면 들어가기?
	 * 
	 * 
	 */

	static int N;
	static int[][] graph;
	static int[][] visited;
	static StringBuilder sb = new StringBuilder();
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	public static void main(String[] args)  throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0)break;
			graph = new int[N][N];
			visited = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			
			BFS();
			sb.append("Problem "+tc+": "+visited[N-1][N-1]+"\n");
			tc++;
		}
		System.out.println(sb);
	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {0, 0, graph[0][0]});
		visited[0][0] = graph[0][0];
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			int rupee = q.peek()[2];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(!isInRange(ny, nx))continue;
				if(rupee+graph[ny][nx]>=visited[ny][nx]) continue;
				visited[ny][nx] = rupee+graph[ny][nx];
				q.offer(new int[] {ny, nx, rupee+graph[ny][nx]});
				
			}
			
		}
		
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}