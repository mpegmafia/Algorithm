import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int k;
	static int[] nums;
	static int[] picked;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// TODO Auto-generated method stub
		while(true) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k==0) break;
			nums = new int[k];
			for(int i=0; i<k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			picked = new int[6];
			
			combi(0, 0);			
			
			System.out.println(sb.toString());
		}
		
		
	}
	
	private static void combi(int cnt, int start) {
		if (cnt==6) {
			Arrays.stream(picked).forEach(a->sb.append(a+" "));
			sb.append("\n");
			return;
		}
		for (int i=start; i<k; i++) {
			picked[cnt] = nums[i];
			combi(cnt+1, i+1);
		}
		
		
		
	}

}