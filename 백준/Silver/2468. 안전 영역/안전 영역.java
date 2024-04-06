import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 그래프가 주어짐
	 * 최저높이와 최고높이를 받아놓음
	 * 최저높이부터 최고높이까지 물에 잠긴다고 가정함
	 * 그때 높이를 BFS의 매개변수로 넣어서 탐색함
	 * 매개변수보다 높은 지점을 발견하면 탐색
	 * 탐색할때마다 cnt+1
	 * 탐색 완료시 정답값 갱신
	 */

	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int ans;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		int maxV = 0;
		int minV = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				maxV = Math.max(maxV, graph[i][j]);
				minV = Math.min(minV, graph[i][j]);
			}
		}
		
		for(int i=0; i<maxV; i++) {
			search(i);
		}
		
		System.out.println(ans);

	}
	
	public static void search(int h) {
		visited = new boolean[N][N];
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph[i][j]>h&&!visited[i][j]) {
					BFS(i, j, h);
					temp++;
				}
			}
		}
		ans = Math.max(temp, ans);
		
	}

	private static void BFS(int i, int j, int h) {
		Queue<int[]>q = new ArrayDeque<>();
		visited[i][j] = true;
		q.offer(new int[] {i,j});
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for(int d=0; d<4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				if(!isInRange(ny, nx)) continue;
				if(visited[ny][nx]) continue;
				if(graph[ny][nx]<=h) continue;
				q.offer(new int[] {ny, nx});
				visited[ny][nx] = true;
			}
		}
		
		
	}
	
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}