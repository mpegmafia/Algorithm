import java.util.Arrays;


class Solution {
    static int N;
    static int[][] road;
    static int[][] graph;
    static int[] visited;
    static int K;
    static int ans = 1;
    public int solution(int n, int[][] ROAD, int k) {
        N = n;
        K = k;
        road = ROAD;
        
        Arrays.sort(road, (a,b)->(Integer.compare(a[2], b[2])));
		visited = new int[N+1];
		for(int i=1; i<=N; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		
		graph = new int[N+1][N+1];
		
		for(int[] status : road) {
			int a = status[0];
			int b = status[1];
			int c = status[2];
//			System.out.println("a : " + a + " b : "+b + " c : "+c);
//			if(union(a,b)) continue;
			if(graph[a][b]!=0 && graph[a][b]<c) continue;
			graph[a][b] = c;
			graph[b][a] = c;
			
		}
//		for(int i=1; i<=N; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		
		visited[1] = -1;
		DFS(1, 0);
//		System.out.println(Arrays.toString(visited));
		System.out.println(ans);

        return ans;
    }
    static void DFS(int start, int weight) {
//		System.out.println(start);
		for(int i=1; i<graph[start].length; i++) {
//			if(start==5) System.out.println("5 : " + i);
			if(graph[start][i] == 0) continue;
			int sumweight = graph[start][i] + weight;
			if(sumweight<=K&&visited[i]>sumweight) {
//				System.out.println("start : "+start+" to : "+ i + " weight : "+graph[start][i]+" before weight : "+weight);
				if(visited[i] == Integer.MAX_VALUE) {
					ans++;
				} 
				visited[i] = sumweight;
				DFS(i, sumweight);
			}
		}
		
		
	}
}