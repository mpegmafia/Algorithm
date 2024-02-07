import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 서로다른 C개의 문자들중 서로다른 L개의 문자들을 조합하여 암호 구성
	 * 최소 한개의 모음과 두개의 자음으로 이루어짐
	 * 문자 오름차순으로 출력
	 *  3<=L<=C<=15
	 *  순열  or 재귀
	 *  이 아니라 조합이었음bac는어차피 abc로 출력되기때문에
	 *  그러면 모음,자음 체크랑 문자 사전순으로만 출력하면됨
	 *  
	 */
	static int L;
	static int C;
	static char[] candidate;
	static char[] picked;
	static List<Character> vowel = Arrays.asList('a','e','i','o','u');
	static StringBuilder sb = new StringBuilder();
	static List<String> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken()); // 비밀번호 길이
		C = Integer.parseInt(st.nextToken()); // 주어지는 문자열의 길이
		candidate = br.readLine().replace(" ", "").toCharArray();
		picked = new char[L];
		combi(0,0);
		Collections.sort(ans);
		for(int i=0; i<ans.size();i++) {
			System.out.println(ans.get(i));
		}
		

	}
	
	public static void combi(int depth, int start) {
		if(depth==L) {
			if (chk()) {
				char[] s = Arrays.copyOf(picked, picked.length);
				Arrays.sort(s);
				ans.add(String.valueOf(s));
			}
					
			return;
		}
		
		for(int i=start; i<C; i++) {
			picked[depth] = candidate[i];
			combi(depth+1, i+1);		
			
		}
		
	}
	public static boolean chk() {
		int vowels = 0;
		int consonant = 0;
		for(int i=0; i<L; i++) {
			if (vowel.contains(picked[i])){
				vowels+=1;
			} else {
				consonant+=1;
			}
//			System.out.println("picked :"+Arrays.toString(picked)+ "vowel :"+ vowels+ "con : "+consonant);
			if(vowels>=1&&consonant>=2) return true;			
		}
		return false;
		
	}
	

}