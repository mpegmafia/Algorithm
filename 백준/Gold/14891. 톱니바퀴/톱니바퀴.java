import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 톱니바퀴의 12시부터 시계방향으로 0번부터 7번까지의 톱니들 0번 톱니는 점수를 결정하는 톱니 2번톱니와 6번 톱니는 다른 톱니의 회전에
	 * 영향을 주는 톱니 N극은 0, S극은 1로 주어진다 특정 톱니를 회전시킨다. 그 오른쪽에 있는 톱니는 현재 톱니의 2번에 영향을 받는다.
	 * 그 왼쪽에 있는 톱니는 현재 톱니의 6번에 영향을 받는다. 재귀로 계속 타고들어가서 오른쪽방향이면 오른쪽 톱니만, 왼쪽방향이면 왼쪽톱니만
	 * 신경을 쓴다 회전은 한꺼번에 시킨다.
	 */

	// 23,340 kb 115 ms

	static int[][] gears;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		gears = new int[5][8];

		for (int i = 1; i <= 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken());
			int wise = Integer.parseInt(st.nextToken()); // 1은 시계, -1은 반시계
			select(gear, wise, 0);
		}

		int ans = 0;
		for (int i = 1; i <= 4; i++) {
			if (gears[i][0] == 1) {
				ans += Math.pow(2, i - 1);

			}
		}
		System.out.println(ans);

	}

	public static void select(int gear, int wise, int dir) {
		if (gear == 5 || gear == 0)
			return;

		if (dir == 0) { // 첨 들어오면 양방향으로 뿌리기
			if (gear + 1 <= 4 && gears[gear][2] != gears[gear + 1][6]) {
				select(gear + 1, wise == 1 ? -1 : 1, 1); // 오른쪽으로 뿌리기
			}
			if (gear - 1 >= 1 && gears[gear][6] != gears[gear - 1][2]) {
				select(gear - 1, wise == 1 ? -1 : 1, -1); // 왼쪽으로 뿌리기
			}
			rotate(gear, wise);
		} else if (dir == 1) { // 오른쪽 방향으로만 뿌리기
			if (gear + 1 <= 4 && gears[gear][2] != gears[gear + 1][6]) {
				select(gear + 1, wise == 1 ? -1 : 1, 1);
			}
			rotate(gear, wise);

		} else { // 왼쪽 방향으로만 뿌리기
			if (gear - 1 >= 1 && gears[gear][6] != gears[gear - 1][2]) {
				select(gear - 1, wise == 1 ? -1 : 1, -1);
			}
			rotate(gear, wise);
		}

	}

	public static void rotate(int gear, int wise) {

		if (wise == -1) { // 반시계방향
			int temp = gears[gear][0];
			for (int i = 0; i < 7; i++) {
				gears[gear][i] = gears[gear][i + 1];
			}
			gears[gear][7] = temp;
		} else { // 시계방향
			int temp = gears[gear][7];
			for (int i = 7; i >= 1; i--) {
				gears[gear][i] = gears[gear][i - 1];
			}
			gears[gear][0] = temp;
		}

	}

}