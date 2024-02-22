import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 * NxM의 행렬, 0은 이동할 수 있는 곳, 1은 이동 할 수 없는 곳 
	 * 1,1에서 N,M까지 이동하려하는데 이떄 최단경로로 가려 한다, 시작하는 칸과 끝나는 칸 갯수도 포함이다.
	 * 이동하는 도중 벽을 한개까지는 부술 수 있다.
	 * BFS로 구현, queue에다가 넘겨줄 인자로 y,x,cnt,flag를 사용, flag=0은 벽을 안부순 상태, flag=1은 부순 상태
	 * 큐를 돌 때 거기까지 갈 수 있는 최솟값을 flag=0일때의 배열과 flag=1일때의 배열 2개로 나눠 관리함
	 * BFS를 돌때 공통적으로 해야할 것
	 * 	만약 거기가 빈칸이라면?
	 * 	y,x에서 새로운 곳으로 이동할 때 거기 안에 있는 카운트 값을 보고 현재 카운트값 보다 낮다면 더 빠른 이동경로가 있다는 것이니까 캔슬
	 * 	만약 현재 카운트값이 더 낮다면 카운트값을 갱신하고 거기로 들어감
	 * 	  만약 벽이 있다면?
	 * 	  위에 조건문에서 현재 flag가 1인지 아닌지 체크하고 벽을 부쉈던 상태면 못들어가고 캔슬
	 * 이렇게 N-1, M-1까지 먼저 도달하면 cnt값을 sysout하고 system.exit
	 * 만약 다돌았는데도 출력을 못했다면 -1출력
	 * 
	 */
	
	static int N;
	static int M;
	static int[][] graph;
	static boolean[][] flag0graph;
	static boolean[][] flag1graph;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		
		graph = new int[N][M];
		flag0graph = new boolean[N][M];
		flag1graph = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				graph[i][j] = s.charAt(j)-'0';
			}
		}
		
		BFS();
		System.out.println(-1);
		

	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0,0,1}); // y, x, flag, cnt값 순서
		flag0graph[0][0] = true;
		flag1graph[0][0] = true;
		
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			int flag = q.peek()[2];
			int cnt = q.peek()[3];
			q.poll();
			
			if(y == N-1 && x == M-1) {
//				for(int i=0; i<N; i++) {
//					System.out.println(Arrays.toString(flag0graph[i]));
//				}
//				System.out.println();
//				for(int i=0; i<N; i++) {
//					System.out.println(Arrays.toString(flag1graph[i]));
//				}
//				
				System.out.println(cnt);
				System.exit(0);
			}
			
			for(int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(!isInRange(ny,nx)) continue; //범위 밖이면 스킵
				
				if(graph[ny][nx] == 1) {//만약 벽이라면
					if(flag==1) continue; // 이미 부쉈다면 
					else {//안부순상태라면
						if(flag1graph[ny][nx]) continue; //만약 내가 더 길게 돌아왔다면 스킵
						flag1graph[ny][nx] = true; //아니라면 벽 부수고 들어갔으니까 값 갱신
						q.offer(new int[] {ny, nx, 1, cnt+1});
					}
				} else if (graph[ny][nx] == 0) {
					if (flag==0) {
						if(flag0graph[ny][nx]) continue;
						flag0graph[ny][nx] = true;
						q.offer(new int[] {ny, nx, 0, cnt+1});
					} else if (flag==1) {
						if(flag1graph[ny][nx]) continue;
						flag1graph[ny][nx] = true;
						q.offer(new int[] {ny, nx, 1, cnt+1});
						
					}
				}
			}
			
			
		}
		
		
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<M;
	}

}