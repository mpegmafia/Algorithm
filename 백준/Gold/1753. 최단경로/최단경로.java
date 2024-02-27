import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 다익스트라 연습
	 */

	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		List<int[]>[] adjList = new List[V+1];
		boolean[] visited = new boolean[V+1];
		int[] minEdge = new int[V+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new int[] {b, c});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
		minEdge[K] = 0;
		pq.offer(new int[]{K,0});
		
		while(!pq.isEmpty()) {
			int from = pq.peek()[0];
			int weight = pq.peek()[1];
			pq.poll();
			if(visited[from]) continue;
			
			visited[from] = true;
			
			for(int[] to : adjList[from]) {
				if(visited[to[0]]) continue;
				if(to[1]+weight>=minEdge[to[0]]) continue;
				minEdge[to[0]] = weight+to[1];
				pq.offer(new int[] {to[0], weight+to[1]});
			}
			
			
			
			
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=V; i++) {
			if(minEdge[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(minEdge[i]+"\n");
		}
		System.out.println(sb);
		

	}

}