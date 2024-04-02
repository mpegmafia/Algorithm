import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 상하좌우, 크기가 같으면 지나갈수만 있고 크기가 작은 물고기는 먹을 수 있음
	 * 아기상어의 초기 크기는 2
	 * 먹을수있는 물고기가 1마리보다 더 많다면 거리가 가장 가까운 물고기를 먹으러 간다 (최단경로로 간다)
	 * 거리가 가까운 물고기가 많다면 가장 위에있는 물고기를, 만약 그러한 물고기가 여러마리라면 가장 왼쪽에 있는 물고기를 먹는다.
	 * ------------------------------------
	 * 상어의 위치를 받음
	 * 레벨별 BFS
	 * 상,좌,우,하 순서대로 제일 가까운 먹이를 찾음
	 * 자기보다 작은 먹이를 찾으면 실제로 BFS를 탐색하고 먹고 그만큼 몸집을 키운다.
	 * 이걸 반복, 만약 잡아먹을게 없으면 리턴 
	 */

	static int N;
	static int[][] graph;
	static boolean[][] visited;
	
	static int[] dy = {-1,0,0,1};
	static int[] dx = {0,-1,1,0};

	static int sharkY;
	static int sharkX;
	static int sharkW;
	static int exp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]==9) {
					sharkY = i;
					sharkX = j;
					sharkW = 2;
					graph[i][j] = 0;
				}
			}
		}
		visited = new boolean[N][N];
		BFS();
	}
	
	static void BFS() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]!=b[0]?Integer.compare(a[0], b[0]):Integer.compare(a[1], b[1]));
		q.offer(new int[] {sharkY, sharkX, sharkW});
		visited[sharkY][sharkX] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			boolean flag = false;
			
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
//			System.out.println("y : "+sharkY+" x :"+sharkX);
//			System.out.println("time : "+time);
//			System.out.println(sharkW);
			if(!findFish(sharkW)) break;
			
			int size = q.size();
			while(size-->0) {
				
				int y = q.peek()[0];
				int x = q.peek()[1];
				int w = q.peek()[2];
				q.poll();
								
				for(int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					
					if(!isInRange(ny, nx))continue;
					if(graph[ny][nx]>w) continue;
					if(visited[ny][nx]) continue;
					if(graph[ny][nx]==w) {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx, w});
					} else if (graph[ny][nx]==0) {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx, w});
					}
					else if(graph[ny][nx]<w&&graph[ny][nx]!=0) {
						flag = true;
						pq.offer(new int[] {ny, nx, w});
					}
				}
			}
			if (flag) {
				sharkY = pq.peek()[0];
				sharkX = pq.peek()[1];
				exp++;
				if(exp==sharkW) {
					sharkW++;
					exp = 0;
				}
				q.clear();
				pq.clear();
				visited = new boolean[N][N];
				visited[sharkY][sharkX]= true;
				graph[sharkY][sharkX] = 0;
				q.offer(new int[] {sharkY, sharkX, sharkW});
				flag = false;
			}
			time++;
		}
		
		System.out.println(time);
	}

	private static boolean findFish(int weight) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean[][] visited2 = new boolean[N][N];
		q.offer(new int[] {sharkY, sharkX, sharkW});
		visited2[sharkY][sharkX] = true;
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			int w = q.peek()[2];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(!isInRange(ny, nx))continue;
				if(graph[ny][nx]>w) continue;
				if(visited2[ny][nx]) continue;
				if(graph[ny][nx]==w) {
					visited2[ny][nx] = true;
					q.offer(new int[] {ny, nx, w});
				} else if (graph[ny][nx]==0) {
					visited2[ny][nx] = true;
					q.offer(new int[] {ny, nx, w});
				}
				else if(graph[ny][nx]<w&&graph[ny][nx]!=0) {
					return true;
				}
			}
			
		}
			
		return false;
	}
	
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}