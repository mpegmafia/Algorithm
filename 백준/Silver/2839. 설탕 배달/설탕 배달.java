import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();


		int cnt = 0;
		while(N>=0) {
			if (N%5==0) {
				System.out.println(N/5+cnt);
				return;
			} else{
			}
			N -=3;
			cnt++;
		}
		System.out.println(-1);
		

	}

}
