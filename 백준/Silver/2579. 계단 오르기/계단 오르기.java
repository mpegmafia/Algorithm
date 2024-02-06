import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		int[] dp = new int[N+1];
		for(int i=0; i<N;i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		if(N==1) {
			System.out.println(nums[0]);
			return;
		}
		if(N==2) {
			System.out.println(nums[0]+nums[1]);
			return;
		}
		dp[1] = nums[0];
		dp[2] = dp[1]+nums[1];
		
		for(int i=3; i<=N; i++) {
			dp[i] = Math.max(nums[i-2]+nums[i-1]+dp[i-3], dp[i-2]+nums[i-1]);
		}
		System.out.println(dp[N]);
		
		
		
	}

}