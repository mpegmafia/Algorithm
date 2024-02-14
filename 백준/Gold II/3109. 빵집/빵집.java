import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 맨왼쪽에서 맨오른쪽까지 갈 수 있는 경우의 수를 구한다
	 * 만약 도중에 x를 만나면 return
	 * visited를 만나면 return
	 * 만약 끝까지 도달한다면 경우의수 +1에다가 현재까지 지나온 경로를 전부 visited로 바꿔줌
	 * range chk
	 */
	static int[][] dt = {
			{-1,1},
			{0,1},
			{1,1}
			};
	
	static char[][] graph;
	
	static boolean[][] visited;
	static int ans;
	static int[] arrival;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		visited = new boolean[R][C];
		arrival = new int[R];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		for(int i=0; i<R; i++) {
			DFS(i, 0, i);
		}
		System.out.println(ans);
		

	}
	public static void DFS(int y, int x, int arrive) {
		visited[y][x] = true;
		if(x==graph[y].length-1) {
			ans+=1;
			arrival[arrive] = 1;
//			for(int i=0; i<graph.length; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//
//			}
//			System.out.println();
			return;			
		}

		for(int i=0; i<3; i++) {
			int ny = y+dt[i][0];
			int nx = x+dt[i][1];
			if(!isInRange(ny, nx)||graph[ny][nx] == 'x') continue;
			
			if(visited[ny][nx]==true||arrival[arrive]==1) continue;
			DFS(ny,nx, arrive);
		}
		
		return;



		
	}
	public static boolean isInRange(int y, int x) {
		
		return (y>=0&&y<graph.length&&x>=0&&x<graph[y].length);
	}
	
	
	

}