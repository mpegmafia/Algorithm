import java.util.HashMap;
class Solution {
    public int[] solution(int n, String[] words) {
		int[] answer= {0, 0};
		
		
		HashMap<String, Boolean> map = new HashMap<>();
		char last = words[0].charAt(words[0].length()-1);
		char first = 'A';
		map.put(words[0], true);
		
		for(int i=1; i<words.length; i++) {
			first = words[i].charAt(0);
			if(last!=first
				||words[i].length()==1
				||map.get(words[i])!=null) {
				answer = new int[] {i%n+1, i/n+1};
				break;
			}
			last = words[i].charAt(words[i].length()-1);
			map.put(words[i], true);
		}

        return answer;
    }
}