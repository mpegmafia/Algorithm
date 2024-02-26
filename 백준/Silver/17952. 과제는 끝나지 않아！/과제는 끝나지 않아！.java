import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 업무는 가장 최근에 주어진 순서대로 한다
	 * 업무를 받으면 바로 시작한다
	 * 업무를 하던 도중 새로운 업무가 추가된다면 하던 업무를 중단하고 새로운 업무를 진행한다
	 * 새로운업무가 끝났다면 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다.
	 * 업무가 몇분걸릴지 정확히 알 수 있고 마감한 업무는 무조건 만점을 받는다
	 * 
	 * 입력:
	 * 	이번 분기는 몇분인지 나타내는 N (1<=N<=1,000,000)
	 * 	이번분기가 시작하고 N분째에 주어진 업무의 정보가 주어진다
	 * 		1 A T : 업무의 만점은 A점, 해결하는데 걸리는 시간 T분
	 * 		0 : 해당 시점에는 업무 X
	 * 출력:
	 * 	김삼성이 받을 이번 분기 업무 평가 점수 출력
	 */
	
	static class Work {
		int remain, point;

		public Work(int remain, int point) {
			super();
			this.remain = remain;
			this.point = point;
		}
		boolean minus() {
			this.remain -=1;
			if(this.remain == 0) return true;
			return false;
		}
		@Override
		public String toString() {
			return "Work [remain=" + remain + ", point=" + point + "]";
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Stack<Work> stack = new Stack<>();
		
		
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a!=0) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				stack.push(new Work(c, b));
			}
			if(stack.isEmpty()) continue;
			
			if(stack.peek().minus()) {
				ans += stack.pop().point;
			}
		}
		System.out.println(ans);
		

	}

}