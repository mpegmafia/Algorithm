import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[101]; //0부터 100까지
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
		int ans = BFS();
		System.out.println(ans);

	}
	
	public static int BFS() {
		int[] distances = new int[101];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[1] = 0;
		Queue<Integer>q = new ArrayDeque<>();
		
		q.offer(1);
		int distance = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-->0) {
				int from = q.poll();
				for(int i=1; i<=6; i++) {
					int to = from+i;
					if(to==100) return distance;
					if(to>100||distances[to]<=distance) continue;
					if(graph[to]!=0) to = graph[to];
					distances[to] = distance;
					q.offer(to);
				}
			}
			distance++;
		}
		
		return -1;
			
		
		
		
	}

}