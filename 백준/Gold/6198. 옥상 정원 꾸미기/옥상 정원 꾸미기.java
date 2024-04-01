import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	/*
	 * 스택에 하나씩 넣음
	 * 만약 기존값보다 작다면 택에 넣고 큐의 사이즈만큼 +
	 * 만약 기존값보다 크다면 스택을 하나씩 빼서 비교해보기
	 * 	기존값이 더 큰게 나올때까지 pop
	 * 	더큰게 나온다면 그 큐의 사이즈만큼 +해주기
	 * 	더큰게 없다면 그냥 넣어주기
	 * 만약 큐에 아무것도 없다면?(초기값)
	 * 처음에 그냥 하나 넣어주기
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.push(nums[0]);
		
		long ans = 0;
		for(int i=1; i<N; i++) {
			if (nums[i]>=stack.peek()) { //벤치마킹 못할 때
				while(!stack.isEmpty()) {
					if(nums[i]>=stack.peek()) {
						stack.pop();						
					} else {
						ans+= stack.size();
						break;
					}
				}
				stack.push(nums[i]);
				
			} else { //벤치마킹할 수 있을 때
				ans+= stack.size();				
				stack.push(nums[i]);
			}
			
		}
		System.out.println(ans);

	}

}