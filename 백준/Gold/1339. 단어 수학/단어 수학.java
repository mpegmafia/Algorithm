import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
	/*
	 * 
	 * 단어마다 가중치를 구해준다(자리수 기준으로)
	 * 그래서 가중치가 제일 높은순서대로 9부터차례대로 숫자를 준다
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			int pow = 0;
			for(int j=s.length()-1; j>=0; j--) {
				if(map.get(String.valueOf(s.charAt(j)))== null) {
					map.put(String.valueOf(s.charAt(j)), (int)Math.pow(10, pow++));
				} else {
					map.put(String.valueOf(s.charAt(j)), (int)(map.get(String.valueOf(s.charAt(j)))+Math.pow(10, pow++)));
				}
			}
		}
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list, (o1,o2)->(map.get(o2).compareTo(map.get(o1))));
		int ans = 0;
		int val = 9;
		for(String s: list) {
			ans += map.get(s)*val--;
		}
		System.out.println(ans);
	}

}