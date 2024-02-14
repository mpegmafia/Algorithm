import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] lans;
	static int N;
	static long ans;
	/*
	 * 배열중 가장 큰 숫자부터 나눠들어감
	 * 나눈다음 숫자가 목표값 N보다 높다면 
	 * 	최대치를 갱신해주고 숫자를 더 높임
	 * 	더 높은값에서 N개가 나오는지
	 * 나눈다음 숫자가 목표값 N보다 낮다면
	 * 	더 낮은값에서 N개가 나오는지 확인
	 *  
	 */
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken()); //만들어야하는 랜선 숫자
		lans = new long[K];
		long max = 0;
		for(int i=0; i<K; i++) {
			lans[i] = Integer.parseInt(br.readLine());
			if(lans[i]>max) max = lans[i];
		}
		biSearch(max*2);
		System.out.println(ans);

	}

	public static void biSearch(long target) {
		long start = 1;
		long end = target;
		
		while(start<=end) {
//			System.out.println("start : "+ start + " end : "+end);
			long mid = (start+end)/2;
			long temp = 0;
			for(int i=0; i<lans.length; i++) {
				temp += lans[i]/mid;
			}
			if(temp>=N) {
				ans = Math.max(ans, mid);
				start = mid+1;
			}else {
				end = mid-1;
			}				
			
		}
		
		
		
		
		
		
		
		
		
		
	}

}