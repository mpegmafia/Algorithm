import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 1부터 N까지의 숫자열
	 * K번째 인덱스를 계속 탐색
	 */

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> ans = new LinkedList<>();
		
		for (int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		while(!q.isEmpty()) {
			for(int i=1; i<=K; i++) { //K번돌기
				if(i==K) { //K번째일때 죽이기
//					if(i>=q.size()) {
//						i = i%q.size(); //어차피 q.offer로 계속 순회하므로 나머지처리할필요가음슴;;
//					}
					ans.add(q.poll());
					break;
				}
				q.offer(q.poll()); //1부터 K-1까지는 뒤로보내기
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i=0; i<ans.size(); i++) {
			sb.append(ans.get(i)+", ");
		}
		
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		
		System.out.println(sb);
		
	}

}