import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	
	static int N;
	static int M;
	static List<Integer>[] adjList;
	static int[] degrees;
	static Queue<Integer> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adjList = new List[N+1];
		degrees = new int[N+1];
		for(int i=1;i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			degrees[b]++;
		}
		
		for(int i=1; i<=N; i++) {
			if (degrees[i] ==0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			int from = q.poll();
			sb.append(from+" ");
			
			for(int number: adjList[from]) {
				if(--degrees[number]==0) {
					
					q.offer(number);
				}
			}
			
		}
		System.out.println(sb);
		

	}

}