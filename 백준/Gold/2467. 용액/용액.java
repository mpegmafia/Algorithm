import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = N-1;
		
		long gap = Integer.MAX_VALUE;
		
		int ans1 = 0;
		int ans2 = 0;
		/*
		 * 왼쪽을 옮겨야 하는지 오른쪽을 옮겨야 하는지 확정이 안되는 상태
		 * 왼쪽을 옮긴 것과 오른쪽을 옮긴 것을 비교해보기?
		 */
		
		while(left<right) {
			long temp = nums[left]+nums[right];
			if(Math.abs(temp)>Math.abs(gap)) {
				if(Math.abs(nums[left+1]+nums[right])>Math.abs(nums[left]+nums[right-1])) {
					right--;
				} else {
					left++;
				}
			} else {
				ans1 = nums[left];
				ans2 = nums[right];
				gap = temp;
				if(Math.abs(nums[left+1]+nums[right])>Math.abs(nums[left]+nums[right-1])) {
					right--;
				} else {
					left++;
				}
			}
		}
		System.out.println(ans1 +" "+ans2);

	}

}