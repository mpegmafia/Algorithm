import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 조카의수 M
	 * 과자의수 N
	 * 과자를 나눠서 줄 수 있음
	 * 먼저 배열을 정렬
	 * 투포인터로 가운데값부터 찾음
	 * 맨뒤에서부터 for문으로 /값을 temp에 +=
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		int left = 1;
		int right = nums[N-1];
		int ans = 0;
		
		while(left<=right) { // <가 아닌 <=로
			int mid = (left+right)/2;
			
			int temp = 0;
			for(int i=N-1; i>=0; i--) {
				if(nums[i]>=mid) temp+= nums[i]/mid;
				else break;
			}
			
			if(temp>=M) {
				left = mid+1;
				ans = mid;
			} else {
				right = mid-1;
			}
			
		}
		System.out.println(ans);
		
		
	}

}