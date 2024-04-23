import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	/*
	 * +, - , ' ' 공백순으로 백트래킹
	 * 끝까지 간다음만약 0 이라면 sb.append
	 * DFS 매개변수로 현재값, 인덱스값, 스트링값
	 * 공백처리가 핵심
	 * 스트링의 그전값(밸류)과 그전전값(부호)를 얻어옴
	 * -라면 그값만큼 + , +라면 그값만큼 -, 그 후 그전밸류*10+이번값에다가 부호 곱해주기 그걸 value+
	 * 그후 인덱스가 마지막까지 왔다면 0체크, 처리 후 리턴
	 * -------------------------
	 * 연속공백을 처리를 못함 -> 그냥 스트링에 다 때려박고 마지막에 계산하기
	 */

	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static List<String> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			list = new ArrayList<>();
			DFS(2,"1");
			Collections.sort(list);
			for(String s: list) {
				sb.append(s+"\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void DFS(int idx, String s) {
		if(idx==N+1) {
			int val = 1;
			int tempval = 0;
			
			for(int i=1; i<s.length()-1; i+=2) {
				if(s.charAt(i)=='+') {
					val += tempval;
					tempval = Integer.parseInt(String.valueOf(s.charAt(i+1)));
					
				} else if(s.charAt(i)=='-') {
					val += tempval;
					tempval = -Integer.parseInt(String.valueOf(s.charAt(i+1)));
					
				} else {
					if(i==1) {
						val=0;
						tempval = 1;
					}
					tempval *= 10;
					if(tempval<0) {
						tempval -= Integer.parseInt(String.valueOf(s.charAt(i+1)));
					} else {
						tempval += Integer.parseInt(String.valueOf(s.charAt(i+1)));
					}
				}
			}
			val+=tempval;
			if (val==0) {
				list.add(s);
			}
			return;
		}
		
		DFS(idx+1, s+"+"+idx); // 더하기
		DFS(idx+1, s+"-"+idx); // 빼기
		DFS(idx+1, s+" "+idx); // 공백
	}

}