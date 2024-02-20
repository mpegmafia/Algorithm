import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char[][] graph;
	static int N;
	static int ans1;//색약아닌사람
	static int ans2;//색약인사람
	static boolean[][] visited;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					DFS(i,j,1);
					ans1++;
					
				}
			}
		}
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					DFS(i,j,2);
					ans2++;					
				}
			}
		}
		System.out.println(ans1+" "+ans2);
		

	}
	
	public static void DFS(int y, int x, int status) {
		visited[y][x]= true;
		
		for(int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(isInRange(ny, nx)&&!visited[ny][nx]) {
				if(status==1&&graph[y][x]==graph[ny][nx]) {  //RGBs 의 인덱스 안쓰고 바로 비교해보기
					DFS(ny, nx, 1);
				} else if(status==2&&(graph[y][x]==graph[ny][nx]||(graph[y][x]!='B'&&graph[ny][nx]!='B'))) { //적록색약은 같거나 둘다 B가 아니라면 가능
					DFS(ny, nx, 2);
				}

			}
		}

	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}