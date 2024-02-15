import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 * N자리수
	 * 4자리라면 1000부터 9999까지
	 * 즉 10^n-1 부터 10^n미만까지
	 * 숫자 직전까지 for문, 2부터 나눠떨어지는 값이 있으면 리턴
	 * 만약 없다면 /10을 한다음 또 똑같은 행위 반복
	 * 그렇게해서 n이 1까지 갔을때도 성공한다면 그건 소수니까 추가해줌
	 */
	static StringBuilder sb = new StringBuilder();
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		chk(1,2);
		chk(1,3);
		chk(1,5);
		chk(1,7);
		
		
		
		System.out.println(sb);

	}
	
	public static void chk(int n, int target) {
		if(n==N) {
//			System.out.println("target : "+target);
			sb.append(target+"\n");
			return;
		}		
		target *= 10;
	A:	for(int i=1; i<10;i++) {
			int temp = target+i;
			for(int j=2; j*j<=temp; j++) {
				if(temp%j==0) continue A;
			}
			chk(n+1, temp);
		}
	}

}