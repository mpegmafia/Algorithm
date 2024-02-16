import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int V;
	static List<Integer> [] list;
	static boolean[] visited;
	static int[] ans; // 번호와 순서
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			list = new List[101];
			visited = new boolean[101];
			ans = new int[] {-1, -1};
			
			for(int i=0; i<101; i++) {
				list[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i+=2) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
			}
			BFS(V);
			sb.append("#"+tc+" "+ans[0]+"\n");
						
		
		}
		System.out.println(sb);
	}
	
	static void BFS(int start) {
		Queue<int[]> q =new ArrayDeque<>();
		q.offer(new int[] {start, 0}); //첫 시작 번호와 연락받는 순서
		visited[start] = true;
		while(!q.isEmpty()) {
			int vertex = q.peek()[0];
			int ord = q.peek()[1];
			q.poll();
			if(ans[1]<=ord) {
				if(ans[1]==ord){
					if(ans[0]<vertex) ans[0] = vertex;					
				} else {
					ans[0] = vertex;
					ans[1] = ord;
				}
			}
			for(int number:list[vertex]) {
				if(!visited[number]) {
					q.offer(new int[] {number, ord+1});
					visited[number] = true;
				}
			}
		}
	}

}