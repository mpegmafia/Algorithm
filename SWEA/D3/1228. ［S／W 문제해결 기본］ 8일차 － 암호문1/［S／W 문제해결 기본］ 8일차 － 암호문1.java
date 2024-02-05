import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * 0~999999사이의 수를 나열하여 만든 암호문
	 * l(삽입) x,y,s : 앞에서부터 x의 위치 바로다음에 y개의 숫자를 삽입, s는 덧붙일 숫자들
	 * 입력:
	 * 	원본 암호문의 길이 N
	 * 	원본 암호문
	 * 	명령어의 개수
	 * 	명령어
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());//N 입력처리
			List<String> str = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				str.add(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			
			int idx;
			int cnt;
			List<String> target = new ArrayList<>();
			
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				st.nextToken();
				idx = Integer.parseInt(st.nextToken());
				cnt = Integer.parseInt(st.nextToken());
				target = new ArrayList<>();
				for(int j=0; j<cnt; j++) {
					target.add(st.nextToken());
				}
				str.addAll(idx, target);
			}
			
			sb.append("#"+tc+" ");
			for(int i=0; i<10; i++) {
				sb.append(str.get(i)+" ");
			}
			sb.append("\n");
			
			
		}
		System.out.println(sb);

	}

}