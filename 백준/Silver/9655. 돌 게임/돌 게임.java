import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/*
	 * 1 = sk
	 * 2 = cy
	 * 3 = sk
	 * 4 = cy
	 * 5 = sk
	 * 6 = cy
	 * 7 = 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N%2==0) {
			System.out.println("CY");
		} else {
			System.out.println("SK");
		}
		

	}

}