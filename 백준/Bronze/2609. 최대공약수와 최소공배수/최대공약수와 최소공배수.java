import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int p = Math.max(a, b);
		int q = Math.min(a, b);
		
		int max =  gcd(p, q); //최대공약수
		int min =  p*q/max;   //최소공배수
		
		System.out.println(max);
		System.out.println(min);
		
		
	}
	
	public static int gcd(int a, int b) {
		
		if(a%b==0) return b;
		
		return gcd(b, a%b);
		
		
	}

}