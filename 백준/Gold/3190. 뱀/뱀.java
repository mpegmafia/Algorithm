import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 뱀의 처음 길이는 1, 위치는 맨위 맨좌측 처음 방향은 오른쪽을 향함
	 * 뱀은 먼저 머리길이를 늘려 머리를 다음칸에 위치시킴
	 * 만약 벽이나 자기자신을 부딪히면 끝
	 * 만약 이동한 칸에 사과가 없다면 몸길이를 줄여서 꼬리가 위치한 칸을 비워둔다
	 * 만약 이동한 칸에 사과가 있다면 꼬리의 위치는 그대로고 사과는 없어진다.
	 * 사과의 위치와 뱀의 이동경로가 주어질 때 게임이 몇초에 끝나는지 출력하라
	 * 입력:
	 * 	보드의 크기 N(정사각형)
	 * 	사과의 개수 K
	 * 	K줄동안 사과의 위치 (행, 렬순인데 -1해줘야함)
	 * 	방향 변환 횟수 L
	 * 	L줄동안 X, C (X초가 끝난 후 C의 방향으로 방향을 전환시킨다는 뜻)
	 * -------
	 * 0,0부터 시작, 시간초를 올리고 먼저 머리를 움직임, 움직인 곳이 범위를 벗어나거나 자기 자신인지 체크
	 * 만약 끝나는 조건이면 시간초 출력
	 * 아니라면 사과가 있는지 체크, 사과가 있다면 몸통늘리기, 아니라면 몸통 줄이기
	 * 몸통 줄이기 늘이기가 핵심 
	 * 
	 */
	
	
	static int N;
	static int[][] graph;

	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static int dir = 0;
	
	static int sec;
	
	static Queue<Integer> q1 = new ArrayDeque<>();
	static Queue<String> q2 = new ArrayDeque<>();
	
	static Queue<int[]> snake = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			graph[y-1][x-1] = 2;
		}
		
		int L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String C = String.valueOf(st.nextElement());
			q1.offer(X);
			q2.offer(C);			
		}
		int ny = 0;
		int nx = 0;
		graph[ny][nx] = 1;
		snake.offer(new int[] {ny,nx});
		
		while(true) {
			sec++;
			ny = ny+dy[dir];
			nx = nx+dx[dir];
			if(!isInRange(ny, nx)||graph[ny][nx]==1) break; // 자기자신이거나 벽밖이라면
			
			if(graph[ny][nx]==0) {
				int taily = snake.peek()[0];
				int tailx = snake.peek()[1];
				snake.poll();
				graph[taily][tailx] = 0;
			}
			graph[ny][nx] = 1;
			snake.offer(new int[] {ny, nx});
			
			if(!q1.isEmpty()&&sec == q1.peek()) {
				q1.poll();
				String d = q2.poll();
				if (d.equals("L")) {
					dir--;
					if(dir<0) dir = 3;
				}
				
				if (d.equals("D")) {
					dir++;
					if(dir>3) dir = 0;
				}
			}
					
			
		}
		
		System.out.println(sec);
		

	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}
	

}