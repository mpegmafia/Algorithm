import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] graph = new int[N][N];
			int ans = 0;

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					graph[i][j] = s.charAt(j) - '0';
				}
			}
			for (int i = 0; i < N; i++) {
				ans += graph[i][N / 2];
				if (i <= N / 2) {
					for (int j = 1; j <= i; j++) {
						ans += graph[i][N / 2 + j];
						ans += graph[i][N / 2 - j];

					}
				} else {
					for (int j = N - 1; j > i; j--) {
						ans += graph[i][N / 2 + j - i];
						ans += graph[i][N / 2 - j + i];
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

}
