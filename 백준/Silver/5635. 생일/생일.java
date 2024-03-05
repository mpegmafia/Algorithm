import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 이름 dd mm yyyy(생년월일)
	 * 가장 나이가 적은 사람의 이름, 가장 나이가 많은 사람의 이름 출력
	 * class만들어서 sort하기
	 */

	static class Name implements Comparable<Name> {
		String name;
		int mm, dd, yy;
		
		public Name(String name, int mm, int dd, int yy) {
			super();
			this.name = name;
			this.mm = mm;
			this.dd = dd;
			this.yy = yy;
		}

		@Override
		public int compareTo(Name o) {
			// TODO Auto-generated method stub
			return this.yy==o.yy? this.mm==o.mm? o.dd-this.dd :o.mm-this.mm :o.yy-this.yy;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		List<Name> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = String.valueOf(st.nextElement());
			int dd = Integer.parseInt(st.nextToken());
			int mm = Integer.parseInt(st.nextToken());
			int yy = Integer.parseInt(st.nextToken());
			list.add(new Name(s, mm, dd, yy));
		}
		Collections.sort(list);
		System.out.println(list.get(0).name);
		System.out.println(list.get(list.size()-1).name);

	}

}