import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S;
	static int[] B;
	static int[] picked;
	static boolean[] isSelected;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		picked = new int[N];
		S = new int[N]; // 신맛 : 곱
		B = new int[N]; // 쓴맛 : 합
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		partial(0);
		System.out.println(ans);
	}

	private static void partial(int depth) {
		if (depth == N) {
			int temps = 1;
			int tempb = 0;
			int flag = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					flag = 1;
					temps *= S[i];
					tempb += B[i];
				}
			}
			if (flag==1)ans = Math.min(ans, Math.abs(temps - tempb));
			return;
		}

		isSelected[depth] = true;
		partial(depth + 1);
		isSelected[depth] = false;
		partial(depth + 1);

	}

}