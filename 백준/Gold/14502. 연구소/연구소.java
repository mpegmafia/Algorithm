import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * NxM의 직사각형인 연구소
	 * 바이러스는 상하좌우로 움직이며 벽으로는 못움직임
	 * 주어진 지도에 벽을 단 3개만 세워서 빈칸개수의 최댓값을 구하기
	 * 벽의 설치좌표를 조합으로 구해서 모든 벽의 경우의수를 구한 다음 거기서 BFS를 돌려 0의 개수 찾기
	 */

	static int N;
	static int M;
	static int[][] graph;
	static int ans;
	static int[][] copygraph;
	
	static class Node{
		int y,x;
		
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static List<Node> viruses = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		copygraph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j]==2) viruses.add(new Node(i,j));
			}
		}
		
		combi(0,0);
		System.out.println(ans);
		

	}
	public static void combi(int depth, int start) {
		if(depth==3) {
			for(int i=0; i<N; i++) {
				copygraph[i] = Arrays.copyOf(graph[i], graph[i].length);
			}
			
			for(Node n : viruses) {
				int y = n.y;
				int x = n.x;
				DFS(y, x);
			}
			ans = Math.max(ans, count());
			
			return;
		}
		
		for(int i= start; i<N*M; i++ ) {
			int r = i/M;
			int c = i%M;
			if(graph[r][c] != 0) continue;
			graph[r][c] = 1;
			combi(depth+1, i+1);
			graph[r][c] = 0;
		}
		
		
	}
	
	public static void DFS (int y, int x) {
		copygraph[y][x] = 2;
		
		for(int i=0; i<4; i++) {
			int ny = y +dy[i];
			int nx = x +dx[i];
			
			if(ny<0||ny>=N||nx<0||nx>=M) continue;
			if(copygraph[ny][nx]==0) {
				DFS(ny, nx);
			}
		}


	}
	
	public static int count() {
		int temp = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copygraph[i][j]==0) temp++;
			}
		}
		return temp;
	}
}