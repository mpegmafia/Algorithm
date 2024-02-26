import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 입력:
	 * 	테스트케이스 T
	 * 	종이컵의 수 N(3<=N<=200000), 종이컵이 왼쪽에서 몇번째에 있는 종이컵인지 알려주는 X(1<=X<=N)
	 * 		그리고 컵의 위치를 맞바꾸는 횟수 K(1<=K<=100000)가 공백으로 주어진다
	 * 	셋째 줄부터 K개의 줄에는 순서대로 바꾼 두 컵의 위치 A,B가 공백으로 구분되어 주어진다
	 * 출력:
	 * 	간식이 위치한 종이컵이 왼쪽부터 몇번째 종이컵인지 출력예시에 맞춰 출력하시오
	 * 	
	 * 
	 */
	
	//static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		//int T = Integer.parseInt(br.readLine());
		
		//for (int tc=1; tc<=T; tc++){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken()); //현 종이컵 위치
			int K = Integer.parseInt(st.nextToken()); //
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(a==X) {
					X = b;
				}
				else if (b==X) {
					X = a;
				}
				
			}
			System.out.println(X);
			//sb.append("#"+tc+" "+X+"\n");
		//}
		//

	}

}