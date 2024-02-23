import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 1~N개 까지의 도시가 있다
	 * N개의 구역을 2개로 나눠야한다.
	 * 일단 부분집합으로 2개로 나눌 수 있는 모든 경우의 수를 구하기 (최적화?)
	 * 그 2개의 도시가 서로 연결되어있는지 확인하기
	 * 	visited 배열을 새로 만들어서 인접한 도시를 모두 구한다음
	 * 	구한 후에 포함되야하는 도시가 전부 포함되어있는지 확인하기
	 *  
	 * 연결되어있다면 두 도시의 인구 차이 구해서 최솟값을 구하기
	 * 
	 */
	
	static int N;
	static int[] population;
	static List<Integer>[] adjList;
	
	static boolean picked[];
	
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		adjList = new List[N+1];
		picked= new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=1; j<=cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[i].add(to);
				adjList[to].add(i);
			}
		}
		
		powerSet(1);//1번도시부터 시작
		
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);

	}
	
	public static void powerSet(int depth) {
		if(depth==N+1) { //1번도시부터 시작했으니까 N+1번 골라야함
			
			boolean[] teamA = new boolean[N+1];
			boolean[] teamB = new boolean[N+1];
			for(int i=1; i<=N; i++) {
				if(picked[i]) teamA[i] = true;
				
				else teamB[i] = true;
			}
			
			if(!isConnected(teamA)||!isConnected(teamB)) return; // 둘중 하나라도 false라면 리턴
			
			ans = Math.min(ans,Math.abs(getPopulation(teamA)-getPopulation(teamB)));
			
			
			
			return;
			
		}
		
			picked[depth] = true;
			powerSet(depth+1);
			picked[depth] = false;
			powerSet(depth+1);
		
	}
	
	public static int getPopulation(boolean[] arr) {
		int temp = 0;
		for(int i=1; i<=N; i++) {
			if (arr[i]) temp+=population[i];
		}
		return temp;
		
		
	}
	
	public static boolean isConnected(boolean[] arr) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(arr[i]) {
				q.offer(i);
				visited[i] = true;
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			for(int to : adjList[from]) {
				if(arr[to]&&!visited[to]) {
					q.offer(to);
					visited[to] = true;
				}				
			}			
		}
		
		for(int i=1; i<=N; i++) {
			if(arr[i]&&!visited[i]) return false;
		}
		
		return true;
		
		
		
		
	}
	
	

}