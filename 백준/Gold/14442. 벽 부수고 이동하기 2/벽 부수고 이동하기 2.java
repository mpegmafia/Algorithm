import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	
	static int N;
	static int M;
	static int K;
	static boolean[][] graph;
	static boolean[][][] visited;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		K = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N][M];
		visited = new boolean[K+1][N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				graph[i][j] = s.charAt(j)=='0'? true : false; //갈 수 있으면 true 벽이면 false;
			}
		}
		
		BFS();
		System.out.println(-1);
		

	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0,0,1}); // y, x, flag, cnt값 순서
		
		for(int i=0; i<K; i++) {
			visited[i][0][0] = true;
		}
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			int flag = q.peek()[2];
			int cnt = q.peek()[3];
			q.poll();
			
			if(y == N-1 && x == M-1) {
				System.out.println(cnt);
				System.exit(0);
			}
			
			for(int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(!isInRange(ny,nx)) continue; //범위 밖이면 스킵
				
				if(!graph[ny][nx]) {//만약 벽이라면
					if (flag==K) continue; //이미 K번 다부쉈다면 스킵
					if(visited[flag+1][ny][nx]) continue; // 이미 누가 먼저 도착했다면 
					else {//안부순상태라면
						visited[flag+1][ny][nx] = true; //아니라면 벽 부수고 들어갔으니까 값 갱신
						q.offer(new int[] {ny, nx, flag+1, cnt+1});
					}
				} else{ //벽이 아니라면
					if (visited[flag][ny][nx]) {//만약 누가 먼저 방문했다면
						continue;
					} else  {
						visited[flag][ny][nx] = true;
						q.offer(new int[] {ny, nx, flag, cnt+1});
						
					}
				}
			}
			
			
		}
		
		
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}

}