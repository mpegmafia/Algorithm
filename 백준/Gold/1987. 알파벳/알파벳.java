import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R;
	static int C;
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static char[][] graph;
	static int ans;
	static int[] alphabets = new int[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		if(R==1&&C==1) {
			System.out.println(1);
			return;
		}
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C;j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		
		
		DFS(0,0,0);
		System.out.println(ans);

	}
	
	public static void DFS(int y, int x, int cnt) {
		if(alphabets[graph[y][x]-'A']==1) {
			ans = Math.max(ans, cnt);
			return;
		}
		alphabets[graph[y][x]-'A'] = 1;
		for(int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(isInRange(ny,nx)) {
				DFS(ny, nx, cnt+1);
			}
		}
		alphabets[graph[y][x]-'A'] = 0;
		
	}
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<R&&x>=0&&x<C;
	}
}