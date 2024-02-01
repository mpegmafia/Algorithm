class Solution {
    public int solution(String name) {
        String s = name;
        int answer = 0;		
		int cnt = s.length()-1; //오른쪽으로 쭉달렸을때 커서변경 횟수 
		System.out.println(s.length()-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) - 'A' <= 13) {
				answer += s.charAt(i) - 'A';
			} else {
				answer += 'Z' - s.charAt(i) + 1;
			}
			
			int idx = i+1;
			while(idx<s.length()&&s.charAt(idx)=='A') {
				idx++;
			}
						cnt = Math.min(cnt, (s.length()-idx)*2+i);
			cnt = Math.min(cnt, i*2+s.length()-idx);
			
		}
		answer+=cnt;
        return answer;
    }
}