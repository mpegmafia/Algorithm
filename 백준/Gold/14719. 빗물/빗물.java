import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] nums = new int[W];
		
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<W; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		
		for(int i=1; i<W-1; i++) { //하나씩 완탐 돌리기
			
			int left_max = 0;
			int right_max = 0;
			for(int j=0; j<W; j++) {//왼쪽과 오른쪽 나눠서 생각
				if(j<i) { // 왼쪽 물용량 구하기
					if(nums[i]<nums[j]) left_max = Math.max(left_max, nums[j]);
					
				} else if (j==i) continue; 
				
				else {
					if(nums[i]<nums[j]) right_max = Math.max(right_max, nums[j]);
				}
				
			}
			if(Math.min(left_max, right_max)-nums[i]>0) {
				ans += (Math.min(left_max, right_max)-nums[i]);
			}
		}
		System.out.println(ans);
		

	}

}