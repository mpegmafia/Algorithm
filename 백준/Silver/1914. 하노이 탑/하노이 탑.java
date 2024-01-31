import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static void hannoi(int num, int from, int mid, int to) {
		if (num==1) {
			sb.append(from+" "+to+"\n");
			return;
		}
		hannoi(num-1,from,to,mid);
		sb.append(from+" "+ to+"\n");
		hannoi(num-1,mid, from, to);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N>20) {
			System.out.println(BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE));
			return;
		}
		sb.append((int)(Math.pow(2, N)-1) + "\n");
		hannoi(N, 1, 2, 3);
		System.out.print(sb.toString());
	}

}
