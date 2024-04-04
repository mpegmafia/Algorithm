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
	 * 학생들의 수 N 두 학생의 키를 비교한 횟수 M a, b (a가 b보다 작음) 자신의 키가 몇번째인지 알 수 있는 방법은? 자신의 키보다
	 * 큰 사람, 작은 사람
	 * 
	 * 정방향 역방향 리스트 정방향 -> 자기보다 큰 사람을 알 수 있음 역방향 -> 자기보다 작은 사람을 알 수 있음 정방향과 역방향을 한번 씩
	 * 돈 다음에 만약 전부다 visited처리되어있으면 cnt++
	 */

	// 110,500 kb 1,761 ms
	static int N;
	static int M;
	static int ans;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;

	static List<Integer>[] list; // 리스트
	static List<Integer>[] listR; // 역방향 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new List[N + 1];
		listR = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();// 자기보다 큰사람 가리키기
			listR[i] = new ArrayList<>(); // 자기보다 작은사람 가리키기
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			listR[b].add(a);
		}

		ans = 0;

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			if (find(i))
				ans++;
		}
		System.out.println(ans);

	}

	static boolean find(int vertex) {

		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(vertex);
		visited[vertex] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int node : list[cur]) {
				if (visited[node])
					continue;
				visited[node] = true;
				cnt++;
				if (cnt == N - 1)
					return true;
				q.offer(node);
			}

		}
		q.clear();
		q.offer(vertex);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int node : listR[cur]) {
				if (visited[node])
					continue;
				visited[node] = true;
				cnt++;
				if (cnt == N - 1)
					return true;
				q.offer(node);
			}
		}

		return false;

	}

}