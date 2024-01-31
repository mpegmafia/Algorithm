import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 남학생은 스위치 번호의 배수를 다 바꿈
	 * for문으로 증감수를 번호수로 해서 바꾸기
	 * 여학생은 좌우 대칭 체크 후에 대칭이라면 변환
	 * 입력:
	 * 	스위치 개수
	 * 	스위치 상태	
	 * 	셋째 줄에는 학생 수
	 * 	학생수의 줄동안 학생성별, 받은 스위치 번호
	 * 출력:
	 * 	한줄에 스위치 20개씩 출력
	 * 	21번부터는 새로 출력
	 */
	static int[] switches;
	
	private static void men(int number) {
		for(int i=number-1; i<switches.length; i+=number) {
			switches[i] = (switches[i]+1)%2;
		}
	}
	private static void women(int number) {
		switches[number-1] = (switches[number-1] +1) %2;
		int cnt = 1;
		
		while (number+cnt-1<switches.length && number-cnt-1>=0 && switches[number+cnt-1]==switches[number-cnt-1]) {
			switches[number+cnt-1] = (switches[number+cnt-1]+1)%2;
			switches[number-cnt-1] = (switches[number-cnt-1]+1)%2;
			cnt++;
		}
			
	}
	
	
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		switches = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int snum = Integer.parseInt(st.nextToken());
			if (gender==1) {
				men(snum);
			}
			else if ( gender==2 ) {
				women(snum);
			}
			
		}
		for(int i=0; i<switches.length; i++) {
			if (i%20==0&& i!=0) sb.append("\n");
			sb.append(switches[i]+" ");
		}
		System.out.println(sb.toString());
		
		
		
	}

}