import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * NxM크기의 직사각형
	 * K개의 CCTV가 설치되어 있음 (최대 8개)
	 * CCTV는 5가지
	 * 1. 오른쪽
	 * 2. 왼쪽, 오른쪽
	 * 3. 위쪽, 오른쪽
	 * 4. 왼쪽, 위쪽, 오른쪽
	 * 5. 왼쪽, 위쪽, 오른쪽, 아래쪽
	 * CCTV는 벽을 통과할 수 없음(같은 CCTV는 통과 가능)
	 * CCTV는 회전할 수 있지만 항상 90도 방향으로만 회전함
	 * CCTV의 방향들을 적절히 조절해서 사각지대의 최소크기를 구하라
	 * 입력:
	 * 	세로크기 N, 가로크기 M
	 * 	N줄동안 그래프 상태, 1~5는 CCTV종류, 6은 벽, 0은 빈칸
	 * 출력:
	 * 	사각지대의 최소 크기
	 * --------------
	 * 사각지대를 돌리는 모든 경우의수를 구해서 각각 크기를 구한후 최솟값을 갱신해주면 된다.
	 * 최악의경우 5^8 = 400만정도 시간은 될거같음
	 * 그래프를 입력받을 때 CCTV만 따로 입력받아서 그것만 돌려보기
	 */
	
	
	
	static class CCTV {
		int y, x, no;
		
		int dir = -1;

		public CCTV(int y, int x, int no) {
			super();
			this.y = y;
			this.x = x;
			this.no = no;
		}
	}
	
	static int[] dy = {0,1,0,-1}; //우, 하, 좌, 상
	static int[] dx = {1,0,-1,0};
	static int[] cnt = {0, 4, 2, 4, 4, 1}; //CCTV별 회전 횟수
	
	static int N;
	static int M;
	static int[][] graph;
	static int[][] copygraph;
	
	static List<CCTV> list = new ArrayList<>();
	
	static int ans = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		copygraph = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]>=1&&graph[i][j]<=5) list.add(new CCTV(i, j, graph[i][j]));
			}
		}
		DFS(0);
		System.out.println(ans);

	}
	
	public static int chk() {
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copygraph[i][j]==0) temp++;
			}
		}
		return temp;
	}
	
	public static void DFS(int depth) {
		if (depth==list.size()) { // 모든 CCTV의 방향을 다 골랐을때
			for(int i=0; i<N; i++) {
				System.arraycopy(graph[i], 0, copygraph[i], 0, M);
			}
			
			fillGraph(copygraph);

			ans = Math.min(ans, chk());
			return;
		}
		
		CCTV c = list.get(depth);
		for(int i=0; i<cnt[c.no]; i++) {
			c.dir = i;
			DFS(depth+1);
		}
		
	}
	
	
	public static void fillGraph(int[][] map) {
		
		for(CCTV c : list) {
			int y = c.y;
			int x = c.x;
			if(c.no==1) {
				forward(y, x, c.dir);
				
			}
			else if(c.no==2) {
				forward(y, x, c.dir);
				forward(y, x, c.dir+2);
				
			}
			else if(c.no==3) {
				forward(y, x, c.dir);
				forward(y, x, (c.dir+1)%4);
				
			}
			else if(c.no==4) {
				forward(y, x, c.dir);
				forward(y, x, (c.dir+1)%4);
				forward(y, x, (c.dir+2)%4);
				
			}
			else if(c.no==5) {
				forward(y, x, c.dir);
				forward(y, x, c.dir+1);
				forward(y, x, c.dir+2);
				forward(y, x, c.dir+3);
				
			}
			
		}
		
		
	}
	
	public static void forward(int y, int x, int dir) {
		int ny = y;
		int nx = x;
		while(true) {
			if(!isInRange(ny,nx)) break;
			if(copygraph[ny][nx] == 6) break;
			if(copygraph[ny][nx]>=1&&copygraph[ny][nx]<=7) {
				ny = ny+ dy[dir];
				nx = nx+ dx[dir];
				continue;
			} else {
				copygraph[ny][nx] = 7;
				ny = ny+dy[dir];
				nx = nx+dx[dir];
			}
		}
		
	}
	public static boolean isInRange(int y, int x) {
		return y>=0&y<N&&x>=0&&x<M;
	}

}