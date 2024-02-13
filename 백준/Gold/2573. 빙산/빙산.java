import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * NxM 크기의 그래프
	 * 1년에 동서남북에 있는 0 칸수마다 줄어듬
	 * 빙산의 한 덩어리는 BFS로 돌려서 체크함
	 * 빙산이 두덩어리 이상으로 분리되는 최초의 시간을 구하기
	 * 만약 다 녹을때까지 분리가 안되면 0 출력
	 * --------
	 * 칸마다 BFS를 돌아서 -를 몇해야하는지 체크후 -해줌
	 * 만약 음수가 된다면 0 으로 셋
	 * 전부다해주면 그게 1년이 지난 것이고 그 후 덩어리 체크
	 * 덩어리는 비지티드 배열을 사용해서 끝까지 BFS로 들어가줌
	 * 만약 BFS 탐색이 끝나면 그게 덩어리 하나
	 * 다 돈다음에 덩어리가 2개이상이면 리턴 
	 */
	static int[][] graph;
	static int year;
	static int N,M;
	static int[][] temp;
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		temp = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			melt();
			year++;
			if(isSplit()) {
				System.out.println(year);
				break;
			}
			
		}
		

	}
	
	public static void melt() {
		
		for(int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(graph[i][j] != 0) {
					int ice = graph[i][j] - sum(i,j);
					temp[i][j] = ice<0?0:ice;
				} else {
					temp[i][j] = graph[i][j];
				}
			}
		}
		int[][] t = graph;
		graph = temp;
		temp = t;
	}
	public static int sum(int y, int x) {
		int temp = 0;
		
		for(int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(graph[ny][nx] ==0) temp++;
		}
		return temp;
		
	}
	public static boolean isSplit() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<int[]>();
		int ans = 0;
		int flag = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (graph[i][j]!=0&&visited[i][j]==false) {
					flag = 1;
					visited[i][j] = true;
					q.offer(new int[] {i,j});
					while(!q.isEmpty()) {
						int y = q.peek()[0];
						int x = q.peek()[1];
						q.poll();
//						System.out.println("q ny : "+ny +" nx : "+nx);
						for(int k=0; k<4; k++) {
							int ny = y+dy[k];
							int nx = x+dx[k];
							if(visited[ny][nx]==false&&graph[ny][nx]!=0) {
//								System.out.println("ny : "+ny +" nx : "+nx);
								visited[ny][nx] = true;
								q.offer(new int[] {ny, nx});								
							}
						}
						
					}
					ans++;
//					for(int p=0; p<N; p++) {
//						System.out.println(Arrays.toString(graph[p]));
//					}
//					for(int p=0; p<N; p++) {
//						System.out.println(Arrays.toString(visited[p]));
//					}
					
				}
			}
		}
		if (ans>=2) return true;
		else if (flag==0) {
			System.out.println(0);
			System.exit(0);
		}
		return false;
		
	}

}