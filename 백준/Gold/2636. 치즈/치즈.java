import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 치즈의 테두리는 한시간마다 녹아 없어짐
	 * 테두리를 판단한다음 녹이기
	 * 0,0은 무조건 공기이므로 거기서부터 판단해서 BFS
	 * 치즈를 처음 만나면 거기로 들어가진 않고 체크만함
	 * 치즈가 다 없어질때의 걸리는 시간과 없어지기 직전 치즈칸의 갯수 구하기
	 */
	
	static int[][] graph;
	static int N;
	static int M;
	
	static int anstime;
	static int anstile;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(chk()) {
			BFS();
		}
		System.out.println(anstime);
		System.out.println(anstile);
		
		
		

	}
	
	static void BFS() {
		Queue<int[]> q  =new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		visited = new boolean[N][M];
		visited[0][0] = true;
		
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(isInRange(ny,nx)&&!visited[ny][nx]) {
					if(graph[ny][nx] == 0) {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					} else {
						visited[ny][nx] = true;
						graph[ny][nx] = 0;
					}
				}
			}
		}
		anstime++;
		
	}
	
	static boolean chk() {//1이 하나라도 있는지 체크
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (graph[i][j] == 1) temp++;
			}
		}
		if(temp>0) {
			anstile = temp;
			return true; //1이 있으면 갯수 갱신해주고 트루 반환
		} else {
			return false;
		}
	}
	
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}

}