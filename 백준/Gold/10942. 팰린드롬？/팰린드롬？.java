import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * S는 시작 인덱스, E는 끝 인덱스 (1부터 시작)
	 * 먼저 모든 숫자를 그냥 스트링에 때려 박아놓음 +=로
	 * 그다음 반대방향 스트링도 하나 만들어놓음
	 * 그다음 구해야하는 범위의 크기에서 /2를 함 -> 짝홀 관계없음
	 * 그래서 정방향과 역방향을 서브스트링한다음 그 문자열이 같은지 비교후 1,0 출력
	 * ----------------
	 * S와 E의 차이에 /2를함 
	 * 정방향 substring S-1부터  위의 값+1을 구함
	 * 역방향 substring N-E 부터 위의값+1 
	 * ---------------------------------
	 * 위에껀 숫자가 한자리수일때만 가능한 거고 0~1000일때는 말이 안됨
	 * dp[N][N]을 선언
	 * 숫자들을 입력받고 미리 그 안에 값을 저장해놓기?
	 * 행은 시작숫자, 열은 끝 숫자
	 * 열은 행보다 작을 수 없다. (이상일때부터 돌면됨)
	 * 이중포문 j=i부터 시작, 첫 idx  
	 * 1~1 -> 정방향1 역방향1
	 * 1~2 -> 정방향1, 역방향 2
	 * 1~3 -> 정방향1 2의 절반, 2의절반 + 역방향 3
	 * 1~4 -> 정방향1~2, 역방향 3~4
	 * 2~2 -> 2 2
	 * 2~3 -> 2 3
	 * 11223
	 * 정방향 문자열은 그냥 더해주기
	 * 역방향 문자열은 .....
	 * 그냥 문자열 다 정방향으로 입력받고 체크함수를 따로 만들기?
	 * 그냥 문자열로 쭉 입력받고 그 크기를 반으로 뚝 잘라서
	 * 정방향 문자열하나 역방향 문자열하나 만들어주고 그것이 같은지에 대해 리턴해주기
	 * 정방향은 그냥 서브스트링
	 * 역방향은 끝에서부터 /2의 전까지 하나씩 추가해주기
	 * 그다음 equals로 비교후 같으면 1, 다르면 0 리턴
	 */

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); //수열의 크기
		int[] nums = new int[N];
		String ord = "";
		String rev = "";
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
//		int[][] dp = new int[N][N];
//		
//		for(int i=0; i<N; i++) {
//			String str = "";
//			for(int j=i; j<N; j++) {
//				str += nums[j];
//				dp[i][j] = chk(str);
//				
//			}
//		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken())-1; //1부터 시작함
			int E = Integer.parseInt(st.nextToken())-1; //N까지 가능
			if(chk2(nums, S, E)) sb.append(1+"\n");
			else sb.append(0+"\n");
//			sb.append(dp[S][E]+"\n");
		}
		
		System.out.println(sb);
		
		
	}
	
	public static boolean chk2(int[] nums, int S, int E) {
		
		int left = S;
		int right = E;
		
		while(left<right) {
			if(nums[left]!=nums[right]) return false;
			left++;
			right--;
		}
		return true;
		
		
		
		
	}
	
	public static int chk(String str) {
		int len = str.length()/2;
		
		String ord = str.substring(0,len);
		String rev = "";
		for(int i=str.length()-1; i>len; i--) {
			rev += str.charAt(i);
		}
		if(ord.equals(rev)) return 1;
		else return 0;
		
	}

}