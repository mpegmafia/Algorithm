import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dwarves;
	static int[] picked;
//	static int flag = 0;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dwarves = new int[9];
		for(int i=0; i<9; i++) {
			dwarves[i] = Integer.parseInt(br.readLine());
		}
		picked = new int[7];
		combi(0, 0);
		
	}
	
	private static void combi(int cnt, int start) {
		if (cnt==7) {
			if(Arrays.stream(picked).sum()==100) {
				Arrays.sort(picked);
				Arrays.stream(picked).forEach(System.out::println);
				System.exit(0);
			}
			return;				
//			flag = 1;
		}
		
		for(int i=start; i<9; i++) {
//			if(flag==1) return;
			picked[cnt] = dwarves[i];
			combi(cnt+1, i+1);
		}
	}

}