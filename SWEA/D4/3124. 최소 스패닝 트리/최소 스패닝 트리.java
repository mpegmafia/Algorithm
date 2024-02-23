import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Solution {
	
	static int V;
	static int E;
	
	static long ans;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	static int[] minEdge;
	
	static List<int[]>[] list;
	static PriorityQueue<int[]> pq;
	
//	static class Vertex implements Comparable<Vertex>{
//		int num, weight;
//
//		public Vertex(int num, int weight) {
//			super();
//			this.num = num;
//			this.weight = weight;
//		}
//
//		@Override
//		public int compareTo(Vertex o) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//				
//	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			ans = 0;
			visited = new boolean[V+1];
			minEdge = new int[V+1];
			list = new List[V+1];
			for(int i=1; i<=V; i++) {
				list[i] = new ArrayList<>();
				minEdge[i] = Integer.MAX_VALUE;
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				list[from].add(new int[] {to, weight});
				list[to].add(new int[] {from, weight});
			}
			
			pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
			
			minEdge[1] = 0;
			pq.offer(new int[] {1, 0});
			
			int cnt = 0;
			while(!pq.isEmpty()) {
				int from = pq.peek()[0];
				int weight = pq.peek()[1];
				pq.poll();
				if(visited[from]) continue;
				visited[from] = true;
				ans += weight;
				cnt++;
				if(cnt==V) break;
				
				for(int[] numbers : list[from]) {
					int target = numbers[0];
					int target_weight = numbers[1];
					if(minEdge[target]>target_weight&&!visited[target]) {
						pq.offer(numbers);
						minEdge[target] = target_weight;
					}
					
				}
				
			}
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);
		

	}

}