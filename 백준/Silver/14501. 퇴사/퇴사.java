import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] table = new int[2][N+2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			table[0][i] = T;
			table[1][i] = P;
		}
		
		for(int i= N; i>=1; i--) {
			if(table[0][i]+i>N+1) table[1][i] = table[1][i+1];
			else table[1][i] = Math.max(table[1][i+1], table[1][i]+table[1][i+table[0][i]]);
		}
		System.out.println(table[1][1]);
	}

}