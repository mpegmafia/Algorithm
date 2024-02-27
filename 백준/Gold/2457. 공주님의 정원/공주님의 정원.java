
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(a[0]==b[0]?a[1]-b[1]:a[0]-b[0]));
		//시작월, 시작일, 종료월, 종료일 순, 종료월 기준으로 정렬
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {a,b,c,d});
		}
		
		int ans = 0;
		int startm = 1;
		int startd = 1;
		int endm = 3;
		int endd = 1;
		int cnt = 0;
		int curmon = 0;
		int curday = 0;
		
		
		while(!pq.isEmpty()) {
			int[] d = pq.poll();
			int flag = 0;
			
			
			while(d!=null && (d[0]<endm||d[0]==endm&&d[1]<=endd)) { //만약 현재 꽃이 종료일 이전에  심을 수 있고
				if (flag==1) d = pq.poll();
				if(d[2]>curmon||(d[2]==curmon&&d[3]>curday)) {
					curmon = d[2];
					curday = d[3];
				}
				flag = 1;
				d = pq.peek();
				
			}
			cnt++;
//			System.out.println(Arrays.toString(d));
//			System.out.println("curmon : " + curmon + " curday : "+curday+ "cnt : "+ cnt);
			if(curmon>=12) {
				ans = cnt;
				break;
			}
			endm = curmon;
			endd = curday;
			
		}
		
		
		System.out.println(ans);
		
	}
	

}