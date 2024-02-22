import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 프린터기의 인쇄 조건
	 * 1. 현재 문서의 중요도 확인
	 * 2. 만약 현재 문서보다 더 중요한 것이 있다면 인쇄하지 않고 큐의 뒤로 보내기
	 * 3. 중요한 것이 없다면 그것을 인쇄
	 * 4. 목표 타겟이 몇번째에 인쇄되었는지 맞추기.
	 * 인쇄순서는 큐로 관리
	 * 인쇄 우선순위는 우선순위큐로 관리
	 * 만약 현재 인쇄할 목표의 중요도가 우선순위큐의 첫번째보다 낮다면 큐 뒤로 보내기
	 * 아니라면 바로 인쇄
	 * 인쇄하면 cnt++
	 * 목표값을 어떻게하면 찾을 수 있을 것인가.
	 */
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;//0번째부터 시작
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	static Queue<Integer> q = new ArrayDeque<>();
	static Queue<Integer> idxq = new ArrayDeque<>();
	static int cnt ;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int a = Integer.parseInt(st.nextToken());
				q.offer(a);
				pq.offer(a);
				if(i==M) {
					idxq.offer(1);
				}
				else {
					idxq.offer(0);
				}
			}
			
			while(true) {
				int cur = q.peek();
				int priority = pq.peek();
				if(cur<priority) {
					q.offer(q.poll());
					idxq.offer(idxq.poll());
				} else{
					pq.poll();
					q.poll();
					cnt++;
					if(idxq.poll()==1) {
						sb.append(cnt+"\n");
						break;
					} 
				}
			}
			q.clear();
			pq.clear();
			idxq.clear();
			
			
		}
		
		System.out.println(sb);

	}

}