import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] graph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for(int i=0; i<N; i++) {
			String s= br.readLine();
			for(int j=0; j<N; j++) {
				graph[i][j] = s.charAt(j)-'0';
			}
		}
		
		
		DFS(N, 0, 0);
		
		System.out.println(sb);
		

	}
	
	
	public static void DFS(int n, int y, int x) {
		
		if(chk(n,y,x)) {
			sb.append(graph[y][x]);
			return;
		}
		sb.append("(");
		DFS(n/2, y, x);
		DFS(n/2, y, x+n/2);
		DFS(n/2, y+n/2, x);
		DFS(n/2, y+n/2, x+n/2);
		sb.append(")");
		
		
		
		
		
	}
	
	
	
	public static boolean chk(int n, int y, int x) {
		int temp = graph[y][x];
		
		for(int i=y; i<y+n; i++) {
			for(int j=x; j<x+n; j++) {
				if (graph[i][j]!=temp) return false;
			}
		}
		
		return true;
		
		
		
	}

}