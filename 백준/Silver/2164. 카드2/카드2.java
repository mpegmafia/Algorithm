import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		int idx = 0;
		int ans = 0;
		while (q.size() != 1) {
			if (idx % 2 == 0) {
				q.poll();
			} else if (idx % 2 == 1) {
				q.offer(q.poll());
			}
			idx++;
		}

		ans = q.poll();
		System.out.println(ans);

	}

}