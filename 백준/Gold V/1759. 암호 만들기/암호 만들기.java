import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 암호문의 길이 L
	 * 최소 한개의 모음과 두개의 자음이 있어야함
	 * 알파벳은 사전순으로 싹다 정렬
	 * 주어지는 문자의 종류는 C가지
	 * 3<=L<=C<=15임
	 * 입력:
	 * 	L, C
	 * 	공백을 기준으로 C가지 문자들이 주어짐
	 * 출력:
	 * 	각줄에 하나씩 사전순으로 가능성있는 암호 출력
	 * ----------
	 * 처음에 주어지는 문자열들을 정렬한 후 조합을 돌리면 애초에 사전순으로 이루어짐
	 * 모음, 자음 체크만 해주면 된다
	 */
	
	static int L;
	static int C;
	static String vowels = "aeiou";
	static List<String> ans = new ArrayList<String>();
	static int[] picked;
	static char[] letters;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		picked = new int[L];
		
		st = new StringTokenizer(br.readLine());
		letters = new char[C];
		for(int i=0; i<C; i++) {
			letters[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(letters);
//		System.out.println(Arrays.toString(letters));
		combi(0,0);
		
		System.out.println(sb);
		
		

	}
	
	public static void combi (int depth, int start) {
		if(depth==L) {
			String s = "";
			for(int i=0; i<L; i++) {
				s = s+letters[picked[i]];
			}
			if(chk(s)) {
				sb.append(s+"\n");
			}
			return;
			
		}
		
		for(int i=start; i<C; i++) {
			picked[depth] = i;
			combi(depth+1, i+1);			
			
		}
		
		
	}
	
	public static boolean chk(String s) {
		int vowel = 0;
		int consonant = 0;
		for(int i=0; i<L; i++) {
			if(vowels.contains(String.valueOf(s.charAt(i)))) {
				vowel++;
			}
			else consonant++;
		}
		if(vowel>=1&&consonant>=2) return true;
		else return false;
		
		
	}
	
	

}