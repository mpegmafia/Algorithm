import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static StringBuilder[] sb;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder[N];
		for(int i=0; i<N; i++) {
			sb[i] = new StringBuilder();
		}
		
		DFS(N, 0);
		
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<N; i++) {
			ans.append(sb[i]+"\n");
		}
		System.out.println(ans);

	}
	
	public static void DFS(int n, int y) {
		if(n==3) {
			for(int i=y; i<y+3; i++) {
				for(int j=0; j<3; j++) {
					if(i==y+1&&j==1) {
						sb[i].append(" ");
					}else {
						sb[i].append("*");
					}
				}
			}
			return;
		}
		
		for(int i=y; i<n+y; i+=n/3) {
			for(int j=0; j<n; j+=n/3) {
				if(i==y+n/3&&j==n/3) {
//					System.out.println("n : "+n+" if + i: "+ i + " j : "+j);
					for(int r = y+n/3; r<y+(n/3)*2; r++) {
						for(int c=0; c<n/3; c++) {
							sb[r].append(" ");
						}
					}
				} else {
//					System.out.println("n : "+n+" else + i: "+ i + " j : "+j);
					DFS(n/3, i);
				}
				
			}
		}
		
	}

}