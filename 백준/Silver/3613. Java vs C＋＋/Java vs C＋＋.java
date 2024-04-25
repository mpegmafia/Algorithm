import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * 대문자가 나온다면 c언어형식으로
	 * 밑줄이 나온다면 자바 형식으로 바꾸기
	 * c언어 형식 -> 대문자가 나온다면 _추가후 lowercase로 바꾸기
	 * 자바형식 -> 밑줄이나온다면 스킵하고 그다음문자 uppercase로 바꾸기
	 * 
	 * _가 연속으로 있을 경우 
	 * 대문자가 연속으로 있을 경우
	 * 첫문자가 대문자로 시작할 경우
	 * 첫문자가 _ 로 시작할 경우
	 * 마지막이 대문자로 시작할 경우
	 * 마지막이 _로 시작할 경우
	 * _와 대문자가 동시에 있을 경우
	 * 안에 공백이 있을 때
	 * 
	 * 그외의 다른 특수문자일때
	 * 
	 */

	static String ans; 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		ans = "";
		int up = 0; 
		int un = 0;
		int space = 0;
		boolean isFalse = false;
		for(int i=0; i<s.length(); i++) {
			if(Character.isUpperCase(s.charAt(i))) {
				if(i==0) {
					isFalse = true;
					break;
				}
				up++;
			} else if (s.charAt(i)=='_') {
				if(i==0|| i==s.length()-1) {
					isFalse = true;
					break;
				}
				un++;
				if(s.charAt(i-1)=='_') isFalse = true;
			} else if (s.charAt(i)==' ') isFalse = true;
			else if (!(s.charAt(i)>='a'&&s.charAt(i)<='z')) {
				isFalse = true;
				break;
			}
		}
		if(isFalse) ans="Error!";
		else if (up>0&&un==0) {
			ans = toC(s);
		} else if(un>0&&up==0) {
			ans = toJ(s);
		}else if (up==0&&un==0) {
			ans = s;
		}else {
			ans = "Error!";
		}
		System.out.println(ans);
		
	}
	
	public static String toC (String s) {
		String temp = "";
		
		for(int i=0; i<s.length(); i++) {
			if(Character.isUpperCase(s.charAt(i))) {
				temp+= "_";
				temp+= Character.toLowerCase(s.charAt(i));
			} else {
				temp+= s.charAt(i);
			}
		}
		
		return temp;
		
	}
	
	public static String toJ (String s) {
		String temp = "";
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='_') {
				i++;
				temp+= Character.toUpperCase(s.charAt(i));
			} else {
				temp+=s.charAt(i);
			}
			
		}
		
		return temp;
		
	}

}