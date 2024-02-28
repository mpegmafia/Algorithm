import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * NxN 의 프로세서
	 * 한칸에는 core나 전선을 설치할 수 있음
	 * 프로세서 밖에는 전원이 흐르고 있다
	 * 전선은 무조건 1자로 설치해야하고 교차하면 안된다.
	 * 최대한 많은 코어에 전원을 연결하였을 경우 전선길이의 합을 구하라
	 * 만약 여러방법이 있을 경우 전선길이 합이 최소가 되는 값을 구하라
	 * -----------
	 * 만약 행이나 열이 0이나 N-1이라면 스킵
	 * 	아니라면 0부터 <5까지 탐색하는데
	 * 	만약 가는길에 와이어를 만나면 컷
	 * 	프로세서를 만나도 컷
	 * 	맵 끝까지 간다면 확정하고 연결된프로세서 +1
	 * 그렇게 12개의 방향을 다 정한다음 마지막에 코어에 연결된 것과 전선 길이를 구하기
	 * 
	 */

	static StringBuilder sb = new StringBuilder();
	static int[][] graph;
	static int N;
	
	static class cpu{
		int y,  x;

		public cpu(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	static int[] dy = {0,1,0,-1,0};
	static int[] dx = {1,0,-1,0,0};
	static List<cpu> list;
	static int ans;
	static int ans2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if(graph[i][j]==1) {
						if(!(i==0||i==N-1||j==0||j==N-1)) list.add(new cpu(i, j));
					}
				}
			}
			
			ans = 0;
			ans2 = Integer.MAX_VALUE;
			DFS(0, 0, 0);
			sb.append("#"+tc+" "+ans2+"\n");
			
			
			
		}
		System.out.println(sb);

	}
	
	public static void DFS(int start, int powered, int wired) {
		if(start==list.size()) {
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
//			System.out.println();
			if(powered>ans||(powered==ans&&wired<ans2)) {
				ans = powered;
				ans2 = wired;
			}
//			chk(powered);
			return;
		}
		
//		for(int i=0; i<N; i++) {
//			System.arraycopy(map[i], 0, copygraph[i], 0, N);
//		}
//		
		cpu c = list.get(start);
		DFS(start+1, powered, wired);
		
		for(int i=0; i<4; i++) {
			int wirenum = isPossible(c.y, c.x, i)-1;
			if(wirenum>0) {
//				forward(c, copygraph);
				DFS(start+1, powered+1, wired+wirenum);
				backward(c, i);
			} 
			
		}
		
		
	}
	
	public static void chk(int powernum) {
		if(ans>powernum) return;
		int wire = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph[i][j]==2) wire++;
			}
		}
		
		if (ans<powernum||(ans==powernum&&wire<ans2)) {
			ans = powernum;
			ans2 = wire;
		}
	}
	
	public static void backward(cpu c, int dir ) {
		int y = c.y;
		int x = c.x;
		
		while(true) {
			y = y+dy[dir];
			x = x+dx[dir];
			if(!isInRange(y, x)) break;
			graph[y][x] = 0;
		}
		
	}
	
	public static void forward(cpu c, int[][] map) {
//		int y = c.y;
//		int x = c.x;
////		int dir = c.dir;
//		
//		while(true) {
//			y = y+dy[dir];
//			x = x+dx[dir];
//			if(!isInRange(y, x)) break;
//			map[y][x] = 2;
//		}
	}
	
	public static int isPossible(int y, int x, int dir) {
		
		
		int ny = y+dy[dir];
		int nx = x+dx[dir];
		if(!isInRange(ny, nx)) return 1;
		if(graph[ny][nx]>=1) return 0;
		int n = isPossible(ny, nx, dir);
		
		if(n>0) {
			return graph[ny][nx] = n+1;
		} else {
			return 0;
		}
		
//		while(true) {
//			y = y +dy[dir];
//			x = x +dx[dir];
//			if(!isInRange(y, x)) {
//				q.clear();
//				return true;
//			}
//			if(map[y][x]==1||map[y][x]==2) break;
//			q.offer(new int[] {y, x});
//			map[y][x] = 2;
//		}
//		while(!q.isEmpty()) {
//			int ny = q.peek()[0];
//			int nx = q.peek()[1];
//			q.poll();
//			graph[ny][nx] = 0;
//		}
//		return false;
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}