import java.util.Arrays;

class Solution {
    public int solution(int k, int[] tangerine) {
        int[] new_tangerine = new int[10000001];
		for(int i=0; i<tangerine.length; i++) {
			new_tangerine[tangerine[i]]++;
		}
		int answer = 0;
		Arrays.sort(new_tangerine);
		for(int i=new_tangerine.length-1; i>=0; i--) {
			k -= new_tangerine[i];
			answer++;
			if(k<=0) break;
		}
		return answer;
    }
}