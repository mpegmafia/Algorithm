import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	/* 
	 * N*N크기의 방 , 1부터 2^N까지 다 다른 숫자들이 적혀있음
	 * 상하좌우 움직일 수 있고 다음방은 현재방보다 크기가 정확히 +1만큼만 커야함
	 * 처음에 어떤 방으로 가야 가장 많은 방을 이동할 수 있는지 출력하기
	 * 입력:
	 * 	테케T
	 * 	정수 N
	 * 	N줄동안 그래프
	 * 출력:
	 * 	#tc 최대로 이동할 수 있는 방번호, 몇개의 방을 이동할 수 있는지 숫자
	 * 	만약 중복값이 있다면 가장 번호가 작은 방을 출력
	 */
	static int[][] graph;
	static int[][] sum;
//	static boolean[][] bool;
//	static int ans;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static int[] ans;

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			sum = new int[N][N];
//			bool = new boolean[N][N];
			ans = new int[2];
			ans[0] = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					BFS(i,j);
				}
			}
			
			sb.append("#"+tc+" "+ans[0]+" "+(ans[1]-1)+"\n");
			
			
			
		}
		System.out.println(sb);

	}
	public static void BFS(int y, int x) {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] bool = new boolean[graph.length][graph.length];
		q.offer(new int[] {y, x});
		int cnt = 1;
		int flag = 0;
		
		
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.peek()[1];
			q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = r+dy[i];
				int nc = c+dx[i];
				
				if(nr>=0&&nr<graph.length&&nc>=0&&nc<graph[nr].length) {
					if (graph[nr][nc]-graph[r][c]==1&&bool[nr][nc] == false) {
						flag = 1;
						q.offer(new int[] {nr,nc});
						bool[nr][nc] = true;
					}
					
				}
			}
//			System.out.println("r :"+r +"c :" +c+" flag :"+ flag+ "cnt :"+cnt);
//			System.out.println(Arrays.deepToString(bool));
			if(flag==0) continue;
			cnt++;			
			
		}
		if (ans[1]<=cnt) {
			if (ans[1]==cnt&& ans[0]>graph[y][x]) ans[0] = graph[y][x];
			else if (ans[1]<cnt) ans[0] = graph[y][x];
//			if(ans[0]>graph[y][x]) {
//				ans[0] = graph[y][x];
//			}
			ans[1] = cnt;
		}
		
		
	}

}