import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] visited;
	static int[] graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[101]; //0부터 100까지
		visited = new int[101];
		Arrays.fill(visited, Integer.MAX_VALUE);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a] = b;
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a] = b;
			
		}
//		System.out.println(Arrays.toString(graph));
		DFS(1, 0);
//		System.out.println(Arrays.toString(visited));
		System.out.println(visited[100]);

	}
	
	public static void DFS(int from, int cnt) {
//		System.out.println(from);
		if(from==100) {
			if(visited[from]>cnt) {
				visited[from] = cnt;
			}
			return;
		}
				
		
		for(int i=1; i<=6; i++) { //주사위 6번을 굴려서 다가보기
			int to = from+i;
			if(to>100) return;
			if (graph[to]!=0) {
				to = graph[to];
			}
			if(visited[to]<cnt+1) continue;
			visited[to] = cnt+1;
			DFS(to, cnt+1);
			
		}
		
		
		
	}

}