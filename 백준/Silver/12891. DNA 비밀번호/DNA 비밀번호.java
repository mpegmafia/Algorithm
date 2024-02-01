import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int S;
	static int P;
	static int[] nums;
	static int ans;
	static String s;
	static int[] target;
	static int[] charnum;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		nums = new int[4]; // A C G T
		charnum = new int[4];
		s = br.readLine();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<s.length(); i++) {
			int temp = index(s.charAt(i));
			charnum[temp]++;
		}
		for(int i=0; i<4; i++) {
			if(charnum[i]<nums[i]) {
				System.out.println(0);
				return;
			}
		}
		
		target = new int[4];
		for(int i = 0; i<P; i++) {
			target[index(s.charAt(i))]++;
		}
		if(chk(target)) ans+=1;
		
		for(int i=P; i<S; i++) {
			target[index(s.charAt(i-P))]--;
			target[index(s.charAt(i))]++;
			if(chk(target)) ans+=1;
		}
		
		System.out.println(ans);
		
	}
	
	private static int index(char c) {
		if(c=='A') {
			return 0;
		}
		else if(c=='C') {
			return 1;
		}
		else if(c=='G') {
			return 2;
		}
		else {
			return 3;
		}
	}
	private static boolean chk(int[] c) {
		for(int i=0; i<4; i++) {
			if(c[i]<nums[i]) return false;
		}
		return true;
	}

}