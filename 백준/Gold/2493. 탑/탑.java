import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 일직선의 레이저탑이 오른쪽에서 왼쪽방향으로 레이저 방출,
	 * 왼쪽에 더 높은 높이의 레이저타워가 그것을 받음
	 * 입력:
	 * 	탑의 수 N (1<=500,000)
	 * 	탑들의 높이 상태(1<=H<=100,000,000)
	 * 출력:
	 * 	탑들의 순서대로 각각의 탑들이 발사한 레이저를 수신한 탑들의 번호 출력(1부터 시작, 없으면 0)
	 * -------
	 * 
	 */

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());

		int tmp = 0;
		int[] ans = new int[N];

		Stack<Integer> stack = new Stack<>();

		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}


		for(int i=0; i<N; i++) {
			if(stack.isEmpty()) {
				stack.push(i);
				continue;
			}
			else if(nums[stack.peek()]>nums[i]) {
				ans[i] = stack.peek()+1;
				stack.push(i);
			} else if(nums[stack.peek()]<nums[i]){
				while(!stack.isEmpty()) {
					if(nums[stack.peek()]>nums[i]) {
						ans[i] = stack.peek()+1;
						break;
					}
					stack.pop();
				}
				stack.push(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(ans[i]+" ");
		}
		System.out.println(sb);



	}

}