import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 총 입력받을 걸그룹 수 N은 100개, 퀴즈의 수 M은 100개
	 * 걸그룹 이름
	 * 걸그룹 인원수
	 * 멤버의 이름을 한줄씩
	 * 팀이나 멤버이름이 주어짐
	 * 퀴즈 M은 0이면 팀의이름, 1은 멤버의 이름이 주어짐
	 * 0일경우 해당 팀에 속한 멤버의 이름을 사전순대로 출력
	 * 퀴즈가 1이면 해당 멤버가 속한 팀의 이름을 출력
	 * ------------
	 * 완탐?
	 * 2중 어레이리스트로 처음에 걸그룹 이름받고 멤버이름받기
	 * 멤버 정렬하고 걸그룹 이름 0번에 넣기
	 * 질문이 1이라면 처음부터 다 탐색
	 * 0이라면 인덱스0만 탐색
	 */
	
	static List<String>[] list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new List[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			String group = br.readLine();
			int members = Integer.parseInt(br.readLine());
			for(int j=0; j<members; j++) {
				list[i].add(br.readLine());
			}
			Collections.sort(list[i]);
			list[i].add(0, group);			
		}
//		for(int i=0; i<N; i++) {
//			for(String s:list[i]) {
//				System.out.println(s);
//			}
//			System.out.println();
//		}
		
		for(int i=0; i<M; i++) {
			String name = br.readLine();
			int quiz = Integer.parseInt(br.readLine());
			answer(name, quiz);
		}
		System.out.println(sb);
		

	}
	
	static void answer(String name, int quiz) {
		if(quiz==0) {
			for(int i=0; i<list.length; i++) {
				if(list[i].get(0).equals(name)) {
					for(int j=1; j<list[i].size(); j++) {
						sb.append(list[i].get(j)+"\n");
					}
					return;
				}
			}
		} 
		
		else {
			for(int i=0; i<list.length; i++) {
				for(String s: list[i]) {
					if(s.equals(name)) {
						sb.append(list[i].get(0)+"\n");
						return;
					}
				}
			}
			
		}
		
		
		
	}

}