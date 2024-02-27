import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static long ans = Integer.MAX_VALUE;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[] nums = { 0, 4, 2, 4, 4, 1 };

	public static void BackTracking(int[][] map, int y, int x) {
		int[][] newmap = new int[map.length][];
		for (int i = 0; i < map.length; i++) {
			newmap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		for (int i = y; i < newmap.length; i++) {
			for (int j = 0; j < newmap[y].length; j++) {
				if (newmap[i][j] >= 1 && newmap[i][j] <= 5) {
					for (int k = 0; k < nums[newmap[i][j]]; k++) {
						BackTracking(FillMap(newmap, i, j, newmap[i][j], k), i, j);
					}
					return;
				}
			}
		}
		ans = Math.min(ans, 
				Arrays.stream(newmap).flatMapToInt(Arrays::stream).filter(a -> a == 0).count()
				);
		return;
	}

	public static int[][] Forward(int[][] map, int y, int x, int num) {
		int ny = y + dy[num];
		int nx = x + dx[num];
		while (ny >= 0 && ny < map.length && nx >= 0 && nx < map[ny].length) {
			if (map[ny][nx] >= 1 && map[ny][nx] <= 5) {
				ny += dy[num];
				nx += dx[num];
				continue;
			} else if (map[ny][nx] == 0 || map[ny][nx] == 7) {
				map[ny][nx] = 7;
				ny += dy[num];
				nx += dx[num];
				continue;
			} else if (map[ny][nx] == 6)
				break;
		}
		return map;
	}

	// dir = 어떤cctv인지 num = 지금은 어떤방향인지
	public static int[][] FillMap(int[][] map, int y, int x, int dir, int num) {
		int[][] newmap = new int[map.length][];
		for (int i = 0; i < map.length; i++) {
			newmap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		newmap[y][x] = 7;

		if (dir == 1) {
			return Forward(newmap, y, x, num);
		}

		if (dir == 2) {
			newmap = Forward(newmap, y, x, num);
			newmap = Forward(newmap, y, x, num + 2);
			return newmap;
		}
		if (dir == 3) {
			newmap = Forward(newmap, y, x, num);
			newmap = Forward(newmap, y, x, (num + 1) % 4);
			return newmap;
		}
		if (dir == 4) {
			newmap = Forward(newmap, y, x, num);
			newmap = Forward(newmap, y, x, (num + 1) % 4);
			newmap = Forward(newmap, y, x, (num + 2) % 4);
			return newmap;
		}
		if (dir == 5) {
			newmap = Forward(newmap, y, x, num);
			newmap = Forward(newmap, y, x, num + 1);
			newmap = Forward(newmap, y, x, num + 2);
			newmap = Forward(newmap, y, x, num + 3);
			return newmap;
		}
		return newmap;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BackTracking(graph, 0, 0);
		System.out.println(ans);

	}

}