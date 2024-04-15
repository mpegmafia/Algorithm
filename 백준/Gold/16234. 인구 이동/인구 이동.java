import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * N*N 크기의 땅, 각 값은 인구수
	 * 상하좌우로 인접해서 국경선이 존재함
	 * 국경선을 공유하는 두 나라의 인구차이가 L명 이상 R명 이하라면 두 나라가 공유하는 국경선을 하루동안만 연다
	 * 국경선이 모두 열렸다면 인구이동을 시작한다
	 * 국경선이 열려있는 모든 인접한 칸은 그 날 하루동안 연합임
	 * 연합의 인구수/연합을 이루는 칸의 개수가 연합의 각 칸의 인구수가 됨 (소수점은 버린다)
	 * 연합을 해체하고 닫는다
	 * 이렇게 인구이동이 며칠동안 이루어 나는지 알아내기
	 * ---------------------------------------
	 * 그래프를 bfs로 탐색한다.
	 * 만약 사방탐색했는데 국경을 열어야한다면... 어디까지 열어야하는지 사방탐색으로 계속 가보기
	 * ->어디서부터 어디까지가 연결되어있는곳인지 모르지만 굳이 알필요가 없는 것이 나올때마다 바로바로 처리하면 됨
	 * 사방탐색후에 개방해야한다면 먼저 거기부터 타고들어가서 해결하기 그 후 visited처리
	 * 리스트에 담아놓기? 그 후에 갯수와 인구수값 다 받은 후 한번에 타다닥 바꾸기?
	 * ->바로바로 바꾸면 안되는 이유 : 국경을 한번에 개방해야하는데 인구수가 계속 바뀌니까 처리해야하는 값도 달라짐;;
	 * -> 그래프 새로 그리기?
	 */
	
	static int N, L, R;
	static int[][] graph;

	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = BFS();
//		System.out.println(Arrays.deepToString(graph));
		System.out.println(ans);

	}
	
	public static int BFS() {
		Queue<int[]> q2 = new ArrayDeque<int[]>();
		
		
		
		int cnt = 0;
		
		while(true) {
			int[][] copymap = new int[N][N];
			for(int i=0; i<N; i++) {
				System.arraycopy(graph[i], 0, copymap[i], 0, N);
			}
			boolean flag = false;
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int d=0; d<4; d++) {
						int ny = i+dy[d];
						int nx = j+dx[d];
						if(!isInRange(ny,nx)) continue;
//						if(visited[ny][nx]) continue;
						int gap = Math.abs(graph[i][j]-graph[ny][nx]);
						if(gap>=L&&gap<=R) {
							flag = true;
							q2.offer(new int[] {i,j});
						}
						
					}
					
					
				}
			}
			if(!flag) break;
			while(!q2.isEmpty()) {
				int[] cur = q2.poll();
				copymap = border(cur[0], cur[1], visited, copymap);
			}
			graph = copymap;
			cnt++;
			
		}
		
		return cnt;
	}
	static int[][] border(int y, int x, boolean[][] visited, int[][] map) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {y, x});
		
		List<int[]> list = new ArrayList<>();
		int count = 0;
		int population = 0;
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();
			if(visited[cy][cx]) continue;
			visited[cy][cx] = true;
			population += graph[cy][cx];
			count++;
			list.add(new int[] {cy, cx});
//			System.out.println("cy : "+cy+" cx: "+cx);
			
			for(int i=0; i<4; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(!isInRange(ny, nx)) continue;
				if(visited[ny][nx]) continue;
				int gap = Math.abs(graph[cy][cx]-graph[ny][nx]);
				if(gap>=L&&gap<=R) {
					q.offer(new int[] {ny, nx});
				}				
			}
			
		}
		int size = list.size();
		if(size<=1) return map;
		
		for(int i=0; i<size; i++) {
			int[] cur = list.remove(0);
			map[cur[0]][cur[1]] = population/count;
		}
//		System.out.println(Arrays.deepToString(graph));
		return map;
		
	}
	
	
	
	
	

	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}
}