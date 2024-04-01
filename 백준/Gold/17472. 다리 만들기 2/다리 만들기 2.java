import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 다리의 길이는 2이상이어야 하고 일자로 이어져야함
	 * 다리가 가로일시 가로 양끝에 섬이 위치해야하고 세로일시 세로 양끝에 위치해야한다
	 * 다리가 교차할 경우 다리의 길이는 각가 다 더해줘야한다.
	 * 모든 섬을 연결하는 다리길이의 최솟값
	 * ---------------------
	 * 처음에 섬의 구획 갯수를 받아놓는다
	 * for문을 1을 만날때까지  돈다
	 * 1을 만나면BFS로 섬의 구획 갯수를 받는다 그후 visited처리를 한다
	 * 그다음 섬의 끝좌표에서 수직으로 다리를 뻗어본다
	 * 다리를 뻗어서 또다른 섬을 만나면 BFS로 섬 구획갯수를 받는다
	 * 만약 구획갯수가 다채워지면 Math.min으로 리턴
	 * -----------------------------
	 * MST
	 * 각 섬들의 모든 좌표들 찾기 각 좌표들마다상하좌우 탐색해서 다른 섬과의 "간선"을 구하기 (이때 길이가 1이라면 추가X)
	 * 구해진 간선들을 크루스칼 조지기
	 * 
	 * 맨 바깥 좌표들을 일단 먼저 구해주고 구할때 가로방향인지 세로방향인지 정해줘야함
	 * 그리고 다른 점들과의 간선을 구할 때 일단 같은방향으로 거르고 같은 좌표인지 거르고
	 * 
	 */
	
	static int N;
	static int M;
	static int[][] graph;
	static int idx;
	static int[] parents;
	static int ans=0;
	
	static boolean[][] visited;
	static List<int[]>[] list;

	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[2], b[2]));
	
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		idx = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j]==1&&!visited[i][j]) {
					BFS(i, j, idx++);//섬을 숫자별로 나누기 
				}
			}
		}
		parents = new int[idx];
		list = new List[idx];
		for(int i=1; i<idx; i++) { //섬 별로 숫자 나누기, 리스트 초기화하기
			parents[i] = i;
			list[i] = new ArrayList<>();
		}
		visited = new boolean[N][M];
		
		
		for(int i=0; i<N; i++) { //가장자리 좌표 구하기 
			for(int j=0; j<M; j++) {
				if(graph[i][j]>0&&!visited[i][j]) {
					getLoc(i,j, graph[i][j]);
				}
			}
		}
		
		ans = -1;
		
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		
		getEdges();
		getMinimum();
		System.out.println(ans);
		

	}
	
	private static void getLoc(int y, int x, int num) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[y][x] = true;
		q.offer(new int[] {y, x});
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();
			for(int i=0; i<4; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(!isInRange(ny, nx))continue;
				if(visited[ny][nx]) continue;
				if(graph[ny][nx]==0) { // 만약 바다를 보고있다면
					list[num].add(new int[] {cy, cx, i}); //그 방향 그대로 보내주기 
				} else if(graph[ny][nx]==num) { // 자기자신이라면 타고들어가기
					visited[ny][nx] = true;
					q.offer(new int[] {ny, nx});
				}
			}
			
			
		}
		
		
	}

	public static void getMinimum() {
		
		int cnt = 1;
		int temp = 0;
		
		while(!pq.isEmpty()) {
			int a = pq.peek()[0];
			int b = pq.peek()[1];
			int weight = pq.peek()[2];
			pq.poll();
			if(union(a,b)) continue;
			temp+=weight;
			cnt++;
//			System.out.println("a :"+a+" b :"+b+" weight : "+weight);
//			System.out.println(Arrays.toString(parents));
			if(cnt==idx-1) {
				ans = temp;
				return;
			}
		}
		return;
	}
	public static void getEdges() {
		
		
		for(int i=1; i<idx; i++) {
			for(int[] node: list[i]) {
				DFS(node[0], node[1], node[2], 0, graph[node[0]][node[1]]);
			}
		}
		
		
		
//		for(int i=1; i<idx-1; i++) { //2중포문으로 각자 간선 만들어주기
//			for(int j=i+1; j<idx ; j++) {
//				for(int[] aloc: list[i]) {
//					for(int[] bloc: list[j]) {
//						if(aloc[2]==bloc[2]&&aloc[0]==bloc[0]&&Math.abs(aloc[1]-bloc[1])-1>=2) { //같은 방향일때
////							System.out.println("debugging");
////							System.out.println("y : "+aloc[0]+"aloc[1] "+aloc[1]+" bloc[1]"+bloc[1]);
//							pq.offer(new int[] {i, j, Math.abs(aloc[1]-bloc[1])-1});
//						} else if (aloc[2]==bloc[2]&&aloc[1]==bloc[1]&&Math.abs(aloc[0]-bloc[0])-1>=2) {
//							pq.offer(new int[] {i, j, Math.abs(aloc[0]-bloc[0])-1});
//						}
//					}
//				}
//			}
//		}
	}
	
	public static void DFS(int y, int x, int dir, int val, int num) {
		
		int ny = y+dy[dir];
		int nx = x+dx[dir];
		if(!isInRange(ny, nx)) return;
		if(graph[ny][nx] == 0) {
			DFS(ny, nx, dir, val+1, num);
		} else if (graph[ny][nx]==num) return;
		else { //다른 섬을 만났다면
			if(val>=2) {
				pq.offer(new int[] {num, graph[ny][nx], val});
			}
			else return;
		}
	}

	private static void BFS(int y, int x, int num) { // y, x
		Queue<int[]> q = new ArrayDeque<>(); //y좌표, x좌표
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		graph[y][x] = num;
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.peek()[1];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				if(!isInRange(ny, nx))continue;
				if(visited[ny][nx]) continue;
				if(graph[ny][nx]==1) {
					graph[ny][nx] = num;
					visited[ny][nx] = true;
					q.offer(new int[] {ny, nx});
				}
			}
		}
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA==rootB) return true;
		
//		if(rootA>rootB) {
			parents[rootA] = rootB;
//			
//		} else parents[b] = rootA;
		
	
		return false;
		
	}
	
	public static int find(int a) {
		if (a==parents[a]) return a;
		
		return parents[a] = find(parents[a]);
		
	}
	
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}
	
	

}