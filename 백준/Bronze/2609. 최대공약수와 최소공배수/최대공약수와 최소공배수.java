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
		
		int max =  1;//최대공약수
		int min =  0;//최소공배수
		
		for(int i=q; i>=1; i--) {
			if(p%i==0&&q%i==0) {
				max = i;
				break;
			}
		}
		
		for(int i=max; i<= p*q; i++) {
			if(i%p==0&&i%q==0) {
				min = i;
				break;
			}
		}
		System.out.println(max);
		System.out.println(min);
		
		
	}

}