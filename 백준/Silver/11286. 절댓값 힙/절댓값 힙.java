import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	/*
	 * 정수 x는 영이아닌 Integer범위의 정수
	 * 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거
	 * 만약 절댓값이 가장 작은 값이 여러개일때는 가장 작은수를 출력(즉, 음수를 출력)
	 * ----
	 * 연산의 개수 N
	 * N줄동안 주어지는 정수 : 0이라면 위 조건 출력, 아니라면 x라는 값을 추가
	 * ---
	 * 입력에서 0이 주어지는 회수만큼 답 출력, 만약 배열이 비어있다면 0 출력
	 * 
	 * 0일때와 아닐때 구분
	 * 0이라면 가장 작은 절댓값 출력, 여러개라면 음수 출력, 없다면 0 출력
	 * 0이 아니라면 pq에 삽입
	 */

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(Math.abs(a)==Math.abs(b))?a-b:Math.abs(a)-Math.abs(b));
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int ord = Integer.parseInt(br.readLine());
			if(ord==0) {
				if(pq.isEmpty()) sb.append(0+"\n");
				else sb.append(pq.poll()+"\n");
			} else {
				pq.offer(ord);
			}
			
		}
		System.out.println(sb);

	}

}