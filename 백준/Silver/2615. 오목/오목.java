import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 19x19의 바둑판
	 * 1부터 19까지 써있으 
	 * 검은바둑알은 1, 흰 바둑알은 2
	 * 0이면 빈자리 
	 * 검은색이 이겼으면 1 
	 * 흰색이 이겼으면 2
	 * 승부가 안났다면 2
	 * 가장 왼쪽에있는 바둑알(같은열이면 맨 위에있는것)
	 * 행, 열 출력
	 */
	
	static int[][] dt = {{-1,1},{0,1},{1,1},{1,0}}; //우상 우 우하 하
	static int[][] graph = new int [19][19];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<19; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				if(graph[i][j]!=0&&chk(i,j)) {
					System.out.println(graph[i][j]);
					System.out.println((i+1)+" "+(j+1));
					return;
				}
			}
		}
		System.out.println(0);
		
		
		
		
		
		
	}
	
	public static boolean chk(int y, int x) {
		
		for(int i=0; i<4; i++) {
			int ny = y+ dt[i][0];
			int nx = x+ dt[i][1];
			int cnt = 1;
			
			if (isInRange(y-dt[i][0],x-dt[i][1])&&graph[y-dt[i][0]][x-dt[i][1]] == graph[y][x]) continue; //처음 시작점이 아니라면 컨티뉴
			for(int j=0; j<5; j++) {
				
				if (!isInRange(ny,nx)||graph[ny][nx] != graph[y][x]) break; //범위체크, 값이 맞는지 체크
				cnt++;
				ny += dt[i][0];
				nx += dt[i][1];
				
			}
			if (cnt==5) return true;
				
			
		}
		return false;
		
		
		
	}
	
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<graph.length&&x>=0&&x<graph[y].length;
	}
	
	
	

}