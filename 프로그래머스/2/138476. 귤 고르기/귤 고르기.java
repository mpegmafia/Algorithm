import java.util.*;
import java.io.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int size : tangerine){
		    map.put(size, map.get(size)==null? 1: map.get(size)+1); 
        }
        
		List<Integer> list = new ArrayList<>(map.keySet());
		Collections.sort(list, (a,b)->Integer.compare(map.get(b), map.get(a)));
        int temp = 0;
        for(Integer size : list){
            temp+=map.get(size);
            answer++;
            if(temp>=k) break;
        }
        
        return answer;
    }
}