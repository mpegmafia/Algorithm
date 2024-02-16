import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 인접리스트로 풀기
 * 인접리스트는 먼저 List<Integer>를 배열로 선언 후 그 배열안에있는 리스트 하나하나를 
 * 빈 ArrayList로 생성한다. 
 */

public class Main {
	
	static int N;
	static int M;
	static int V; //시작 정점
	static List<Integer>[] adjList;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjList = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		visited = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);	
			
		}
		for(int i=1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		DFS(V);
		visited = new boolean[N+1];
		sb.append("\n");
		BFS(V);
		System.out.println(sb);
		

	}
	
	public static void BFS(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int vertex = q.poll();
			sb.append(vertex+" ");
			for(int number: adjList[vertex]) {
				if(!visited[number]) {
					q.offer(number);
					visited[number]= true;
				}
			}
		}
		
	}
	public static void DFS(int start) {
		int vertex = start;
		visited[vertex] = true;
		sb.append(vertex+" ");
		
		for(int number:adjList[vertex]) {
			if(!visited[number]) DFS(number);
		}
		
		
		
	}

}