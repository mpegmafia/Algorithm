import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    static boolean[] ispicked; //이미 선택됐었던 컬럼 
	static boolean[] selected; // 현재 선택한 컬럼
	static String[][] relation;
	static int ans;
	static String[] strings;	
	static List<String>[] list;
    
    public int solution(String[][] relation2) {
        relation = relation2;

        ispicked = new boolean[relation[0].length];
		selected = new boolean[relation[0].length];
		
		strings = new String[relation.length];
		
		list = new List[relation.length+1];
		for(int i=1; i<=relation.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		
		
		powerSet(0);
        
        return ans;
    }
    static boolean chk(String cur, String s, int depth) {
//		System.out.println("cur : "+cur +" s :"+s);
		if(depth==s.length()) {
//			System.out.println("depth : "+depth);
			return true;
		}
		if(cur.contains(String.valueOf(s.charAt(depth)))) {
//			System.out.println(String.valueOf(s.charAt(depth)));
			return chk(cur, s, depth+1); //  여기서 return 해줘야 안꼬
		}
	
			
		return false;
		
		
	}
    static void powerSet(int depth) {
		if(depth==relation[0].length) {
			int cnt = 0; //열 갯수 체크
			String cursel = "";
			boolean flag = false; // 만약 all false라면 리턴
			for(int i=0; i<relation[0].length; i++) { //먼저 그전에 선택됐었는지 체크
//				if(ispicked[i]&&selected[i]) {
//					System.out.println(Arrays.toString(selected));
//					System.out.println(Arrays.toString(ispicked));
//					System.out.println();
//					return; 
//				}
				if(selected[i]) {
					cnt++;
					cursel += i;
					flag = true;
				}
			}
			if(flag==false) return;
			
			
			
for(int i=1; i<cnt; i++) {
				
				for(String s : list[i]) {
					if (chk(cursel, s, 0)) {
//						System.out.println("boom");
						return;
					}
//					if (cursel.contains(s)) return;
				}
			}
			for(int i=0; i<relation.length; i++) { //  strings 초기화
				strings[i] = "";
				for(int j=0; j<selected.length; j++) { //리스트에 선택한 튜플의 칼럼들 담기
					if(selected[j]) {
						strings[i] += relation[i][j]; // 선택됐다면 추가하기
					}
				}
				
				for(int k=0; k<i; k++) {
					if(strings[k].equals(strings[i])) return;
				}
			}
			
			// 만약 다 중복없이 돌았다면 후보키
			list[cnt].add(cursel);
//			for(int i=0; i<relation[0].length; i++) {
//				if(selected[i]) {
//					ispicked[i]= true;
//				}
//			}
//			System.out.println("answer"+Arrays.toString(selected));
			ans++;

			return;
		}
		selected[depth] = false;
		powerSet(depth+1);
		selected[depth] = true;
		powerSet(depth+1);
		
		
		
	}
}