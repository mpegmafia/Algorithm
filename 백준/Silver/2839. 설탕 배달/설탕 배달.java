import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/*
	 *  정확하게 N킬로그램 설탕배달을 해야함
	 *  설탕봉지는 5kg와 3kg로 정해져있다
	 *  입력:	
	 *  	N이 주어진다
	 *  출력:
	 *  	봉지의 최소개수, 만약 정확하게 N킬로그램을 만들 수 없다면 -1 출력
	 *  처음에 5kg을 다 가져간다고 가정하고 그다음에 3kg를 하나씩 빼가는걸로 진행
	 *  만약 3kg봉지가  5개이상이라면 들고갈수 없으니까 -1출력
	 */

	public static void main(String[] args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int ans_5 = N/5;
		N %= 5;
		int ans_3 = N/3;
		N %= 3;
		
		
		while(N>0&&ans_5>=1) {
			ans_5--;
			N+=5;
			ans_3+= N/3;
			N = N%3;
			if (ans_3>=5) break;
		}
		
		
		
		if(N==0) System.out.println(ans_5+ans_3);
		else System.out.println(-1);
		
		
		
		

	}

}