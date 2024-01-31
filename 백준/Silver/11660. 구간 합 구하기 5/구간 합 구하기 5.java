import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * NxN크기의 표를 처음부터 더해가면서 채워감
	 */

	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N+1][N+1];
		int[][] quest = new int[M][];
		int[][] ysum = new int[N+1][N+1];
		int[][] xsum = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				ysum[i][j] += ysum[i-1][j] + graph[i][j];
				xsum[i][j] += xsum[i][j-1] + graph[i][j];
			}
		}
		
		
		for (int i=0; i<M; i++) {
			quest[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int sum = 0;
			if (quest[i][2]-quest[i][0]>=quest[i][3]-quest[i][1]) {
				for(int j=quest[i][1]; j<=quest[i][3]; j++) {
					sum += ysum[quest[i][2]][j] - ysum[quest[i][0]-1][j];
					
				}
			}else {
				for(int j=quest[i][0]; j<=quest[i][2]; j++) {
					sum += xsum[j][quest[i][3]]-xsum[j][quest[i][1]-1];
				}
				
			}
			sb.append(sum+"\n");
			
		}
		System.out.println(sb.toString());
	}

}