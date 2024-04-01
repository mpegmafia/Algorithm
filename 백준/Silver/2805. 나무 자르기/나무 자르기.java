import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//나무의 수 N, 나무의 길이 M
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] woods = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i=0; i<N; i++) {
			woods[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, woods[i]);
		}
		
		int min = 0;
		max*=2;
		
		int ans = 0;
		
		while(min<max) {
			
			int mid = (min+max)/2;
			
			long temp = 0;
			for(int i=0; i<N; i++) {
				if(woods[i]-mid>0) temp += woods[i]-mid;
			}
			
			if(temp>=M) {
				min = mid+1;
				ans = mid;
				
			} else {
				max = mid;
			}
			
		}
		
		System.out.println(ans);
		
		
		
		

	}

}