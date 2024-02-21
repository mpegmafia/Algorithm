import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 인접리스트를 사용해서 4번 타고 들어가면 문제에서 말하는 관계가 맞는거임
	 * 하지만 타고들어갈 때 주의할 점은 직전에 타고들어온 노드는 들어가면 안되고
	 * 지금까지 타고들어갔던 노드들도 들어가면안됨, 즉 visited배열을 인자로 넘겨서 가야함
	 * 그래서 완탐으로 갈기기
	 */
	
	static int N; //총 사람 명수 (0부터 N-1까지 번호)
	static int M; //총 친구관계 숫자
	static List<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		list =  new List[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a); //양방향으로 추가해주기
		}
		
		for(int i=0; i<N; i++) {
			visited[i] = true;
			DFS(i, 0);
			visited[i] = false;
		}
		
		
		System.out.println(0);

	}
	
	public static void DFS(int start, int cnt) {
		if (cnt==4) {
			System.out.println(1);
			System.exit(0);
		}
		
		for(int to : list[start]) {
			if(!visited[to]) {
				visited[to] = true;
				DFS(to, cnt+1);
				visited[to] = false;
			}			
		}		
		
	}

}