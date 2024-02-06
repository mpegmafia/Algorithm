import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 달팽이회전처럼 회전시키기 하지만 이번엔 땡기는 걸로
	 * 먼저 기준점을 잡고 그 값을 임시값에 저장시킴
	 * 그다음 델타방향따라 다음에있는값을 현재값에 저장시킴
	 * 그다음 range를 벗어나면 방향바꿈
	 * 이렇게 반복하다 처음 기준점의 행+1을 만나면 임시값을 거기다 넣음
	 * 그다음 안쪽 행렬로 파고들어감
	 * 
	 * 파고들어가야하는 횟수는 0<=s<Math.min(N,M)/2
	 * 기준점은 0,0 1,1 2,2 즉 s랑 같음
	 * range도 0<=r<=N-1 , 0<=c<=M-1
	 * 파고들어가면 1<=r<=N-2, 1<=c<=M-2
	 * 일반화하면 s<=r<=N-1-s, s<=c<=M-1-s
	 * 
	 */
	static int[][] graph;
	static int N;
	static int M;
	static int R;
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};

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
		
		for(int i=0; i<R; i++) {
			rotate();
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}

	}
	
	public static void rotate() {
		int s = Math.min(N, M)/2;
		
		for(int i=0; i<s; i++) {//파고들어가는 횟수
			int temp = graph[i][i];
			int y = i;
			int x = i;
			int dir = 0;
			int ny = 0;
			int nx = 0;	
			
			while(true) {
				ny = y+dy[dir];
				nx = x+dx[dir];
				if (ny==i&&nx==i) { //마지막값에 도달했을경우
					graph[y][x] = temp;
					break;
				}
				
				if(ny<i||ny>N-1-i||nx<i||nx>M-1-i) {
					dir = dir+1;
					continue;
				}
				graph[y][x] = graph[ny][nx];
				y = ny;
				x = nx;			
				
			}			
			
			
		}
		
		
		
	}

}