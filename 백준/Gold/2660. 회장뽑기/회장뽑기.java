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
	 * 한 개인의 점수는 최대로 멀어진 사람과의 차이임
	 * 모두다 직접적으로 친구면 1점, 한명이라도 친구의 친구라면 2점
	 * 한 개인은 모두랑 직 간접적으로 연결되어있음
	 * 가장 작은 점수와 그 점수를 가진 명수를 출력,
	 * 그 점수를 가진 사람들을 오름차순으로 출력하기
	 * -----------
	 * 인접리스트 사용
	 * 양방향으로 지정해주기
	 * 1번부터 N번까지 각각 다 돌면서 몇점인지 체크
	 * 	자기자신의 간선부터 돌고, 모두랑 연결되어있는지 체크, 없다면 점수+1한다음 친구의 친구들을 체크
	 * 	이거 반복
	 * 또한 돌면서 최소 점수 체크하기
	 * 그다음 마지막으로 1번부터 n번까지 돌면서 최솟값인사람 ans에 출력하기
	 */
	static int N;
	static List<Integer>[] list;
	static int[] point;
	static int minimum= Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new List[N+1]; // 1번부터 n번까지
		point = new int[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a==-1&&b==-1) break;
			list[a].add(b);
			list[b].add(a);
		}
		for(int i=1; i<=N; i++) {
			point[i] = BFS(i);
		}
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(point[i]==minimum) {
				ans++;
				sb.append(i+" ");
			}
		}
		System.out.println(minimum+" "+ans);
		System.out.println(sb);
		
		
		

	}
	static int BFS(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		q.offer(start);
		int cnt = 1;
	A:	while(!q.isEmpty()) {
			int qsize = q.size();
			while(qsize-->0) {
				int from = q.poll();
				for(int to : list[from]) {
					if(visited[to]) continue;
					visited[to] = true;
					q.offer(to);
				}
				
			}
			
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					cnt++;
					continue A; // 실수;;
				}
			}
			if(cnt<minimum) minimum = cnt;
			break;
			
		}
		
		return cnt;
		
		
	}

}