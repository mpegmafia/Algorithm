import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 맥주 20개, 50m에 한병씩 마시기
	 * 즉, 50미터를 가려면 그 직전에 맥주 한명을 마셔야함 
	 * 편의점에서 빈병을 버려야 새 맥주병을 살 수 있음, 맥주는 20병이 한도, 편의점을 나선 직후에도 50m를 가기전에 맥주 한병을 마셔야함
	 * 입력:ㅣ
	 * 	테케
	 * 	맥주를 파는 편의점의 개수 n
	 * 	집
	 * 	편의점
	 * 	락페 좌표 
	 * x좌표와 y좌표의 차이가 거리임(맨해튼거리)
	 * -----------------
	 * 좌표에 음수가 나올 수 있다는 점, 집의 좌표가 꼭 0,0이 아닐 수 있다는 점, 좌표순대로 주어지는게 아닐 수 있다는 점, 
	 * 가장 가까운점으로 가는게 이득이 아닐수 있다는 점을 다 고려해야함
	 * ->완탐?
	 * 
	 * 
	 * 
	 */
	static boolean[] visited;
		
	static int[][] gs;
	static int homeY;
	static int homeX;
	static int rockY;
	static int rockX;
	static int n;
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			homeY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			gs = new int[n][2];
			visited = new boolean[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				gs[i] =  new int[] {y, x};
				
			}
			
			st = new StringTokenizer(br.readLine());
			rockY = Integer.parseInt(st.nextToken());
			rockX = Integer.parseInt(st.nextToken());
			BFS();
			
			
		}
		System.out.println(sb);
	
	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {homeY, homeX});
		
		while(!q.isEmpty()) {
			int y= q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			if(distance(y, x, rockY, rockX)<=1000) {
				sb.append("happy\n");
				return;
			}
			
			for(int i=0; i<n; i++) {
				if(distance(y, x, rockY, rockX)<=1000) {
					sb.append("happy\n");
					return;
				}
				if(visited[i]) continue;
				if(distance(y, x, gs[i][0], gs[i][1])<=1000) {
					q.offer(new int[] {gs[i][0], gs[i][1]});
					visited[i] = true;
				}
			}
			
			
		}
		
		sb.append("sad\n");
		
		
		
	}
	
	public static int distance(int y, int x, int ty, int tx) {
		return Math.abs(y-ty)+Math.abs(x-tx);
		
	}
	
	

}