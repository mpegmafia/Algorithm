import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Flower implements Comparable<Flower>{
		int std, endd;

		public Flower(int std, int endd) {
			super();
			this.std = std;
			this.endd = endd;
		}

		@Override
		public int compareTo(Flower o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.std, o.std);
		}
	}
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Flower[] flowers = new Flower[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())*100;
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken())*100;
			int d = Integer.parseInt(st.nextToken());
			flowers[i] = new Flower(a+b, c+d);
		}
		
		Arrays.sort(flowers);
		int ans = 0;
		int cnt = 0;
		int endday = 301;
		int idx = 0;
		int curend = 0;
		while(curend<1201) {
			
			boolean find = false;
			
			for(int i=idx; i<flowers.length; i++) {
				
				if(flowers[i].std<=endday) {
					if(curend<flowers[i].endd) {
						curend = flowers[i].endd;
						find = true;
					}
				} else {
					idx = i;
					break;
				}
				
			}
			if(find) {
				cnt++;
				endday = curend;
			} else break;
			
		}
		if(curend>=1201) ans = cnt;
		System.out.println(ans);
		
		

	}

}