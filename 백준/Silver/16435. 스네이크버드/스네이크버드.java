import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 스네이크버드의 길이는 L
	 * 먹이를 먹을때마다 1씩 늘어남
	 * 과일들은 지상으로부터 일정높이씩 떨어져있음
	 * 자신의 길이보다 작거나 같은 높이에 있는 과일들만 먹을 수 있음
	 * 먹어 늘릴 수 있는 최대길이를 구하시오
	 * 입력:
	 * 	과일의 개수 N, 스네이크버드의 초기 길이 L
	 * 	두번째줄에는 높이들이 공백을두고 주어짐
	 * 출력:
	 * 	스네이크버드의 최대길이
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] height = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(height);
		for(int i=0; i<N; i++) {
			if(height[i]>L) break;
			L++;
		}
		System.out.println(L);

	}

}