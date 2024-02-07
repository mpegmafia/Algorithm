import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 도화지의 크기는 100
	 * 색종이의 크기는 10
	 * visited 배열로 이미 붙인 구역은 true로 활성화 한다음
	 * for문을 돌면서 true인 곳을 돈다면 true가 아닐때까지 크기 저장하면서순회 
	 */	
	static int area = 10;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		boolean[][] graph = new boolean[100][100];
		int[][] paper = new int[N][2];
		int ans = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<2; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		for (int i=0; i<N; i++) {
			int y = paper[i][0]; // 인덱스 부여
			int x = paper[i][1];
			
			for(int r=y; r<y+area; r++) {
				for(int c=x; c<x+area; c++) {
					graph[r][c] = true;					
				}
			}
		}
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (graph[i][j]== true) {
					ans++;
				}
			}
		}
		System.out.println(ans);
		
		
		
		
		
	}

}
