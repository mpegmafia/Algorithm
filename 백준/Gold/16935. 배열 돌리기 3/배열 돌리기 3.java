import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 크기가 N*M인 배열에 연산을 R번 적용, 연산은 총 6가지가 있다
	 * 1. 배열을 상하반전
	 * 2. 배열을 좌우반전
	 * 3. 오른쪽으로 90도 회전
	 * 4. 왼쪽으로 90도 회전
	 * 5,6은 배열을 사분면으로 나눔 좌상, 우상, 우하, 좌하 순으로 1,2,3,4,
	 * 5는 사분면을 시계방향으로 회전
	 * 6은 사분면을 반시계방향으로 회전
	 * 입력:
	 * 	N,M,R 이 한번에 주어짐
	 * 	N줄동안 M개의 원소가 주어짐
	 * 마지막줄에는 연산이 공백으로 구분되어져 주어짐, 순서 지켜야함
	 * 출력:
	 * 	그래프상태 출력
	 */
	static int[][] graph;
	static int N;
	static int M;
	static int R;	
	static int[] dy = {1,0,0,-1};
	static int[] dx = {0,-1,1,0};
	static int[] dy2 = {0,1,-1,0};
	static int[] dx2 = {1,0,0,-1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int order = Integer.parseInt(st.nextToken());
			if (order==1) graph = upsidedown();
			if (order==2) graph = inversion();
			if (order==3) graph = rotateClock();
			if (order==4) graph = rotateCounter();
			if (order==5) graph = splitClock();
			if (order==6) graph = splitCounter();
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(graph[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		

	}
	
	public static int[][] inversion(){ // 좌우반전
		int[][] map = new int[N][M]; //넘겨줄 배열
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]  = graph[i][M-1-j];
			}
		}
		return map;		
	}
	
	public static int[][] upsidedown(){ //상하반전
		int[][] map = new int[N][M];
		
		for(int j=0; j<M; j++) {
			for(int i=0; i<N; i++) {
				map[i][j] = graph[N-1-i][j];
			}
		}
		return map;
	}
	
	public static int[][] rotateClock(){
		int[][] map =new int[M][N];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = graph[N-1-j][i];
			}
		}
		int temp =N;
		N=M;
		M=temp;
		return map;
	}
	public static int[][] rotateCounter(){
		int[][] map =new int[M][N];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = graph[j][M-1-i];
			}
		}
		int temp =N;
		N=M;
		M=temp;
		return map;
	}
	
	public static int[][] splitClock(){
		int[][] map = new int[N][M];
		
		int dir = 0;
		
		
		for(int i=0; i<N; i++) {
			dir = 0;
			if (i>=N/2) dir = 2;
			for(int j=0; j<M; j++) {
				if(j==M/2) dir+=1;
				map[i][j] = graph[i+N/2*dy[dir]][j+M/2*dx[dir]];				
			}
		}
		return map;	
		
	}
	public static int[][] splitCounter(){
		int[][] map = new int[N][M];
		
		int dir = 0;
		
		
		for(int i=0; i<N; i++) {
			dir = 0;
			if (i>=N/2) dir = 2;
			for(int j=0; j<M; j++) {
				if(j==M/2) dir+=1;
				map[i][j] = graph[i+N/2*dy2[dir]][j+M/2*dx2[dir]];				
			}
		}
		return map;	
		
	}
	

}