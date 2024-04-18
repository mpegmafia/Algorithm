import java.util.*;
import java.io.*;

class Solution {
    public int solution(String str1, String str2) {
       int answer = 0;
		HashMap<String, Integer> map1 = new HashMap<>();
		HashMap<String, Integer> map2 = new HashMap<>();
		
		for(int i=0; i<str1.length()-1; i++) {
			char a = str1.charAt(i);
			char b = str1.charAt(i+1);
			a = Character.toLowerCase(a);
			b = Character.toLowerCase(b);
			if('a'>a||a>'z') continue; 
			if('a'>b||b>'z') continue;
			String s = String.valueOf(a);
			s+= String.valueOf(b);
			if(map1.get(s)==null) map1.put(s, 1);
			else map1.put(s, map1.get(s)+1);
		}
		
		for(int i=0; i<str2.length()-1; i++) {
			char a = str2.charAt(i);
			char b = str2.charAt(i+1);
			a = Character.toLowerCase(a);
			b = Character.toLowerCase(b);
			if('a'>a||a>'z') continue; 
			if('a'>b||b>'z') continue;
			String s = String.valueOf(a);
			s+= String.valueOf(b);
			if(map2.get(s)==null) map2.put(s, 1);
			else map2.put(s, map2.get(s)+1);
		}
		List<String> keys = new ArrayList<>(map1.keySet());
		
		int inter = 0;
		for(String s : keys) {
			if(map2.get(s)==null) continue;
			inter += Math.min(map1.get(s), map2.get(s));
		}
		int union = 0;
		List<String> keys2 = new ArrayList<>(map2.keySet());
 		HashMap<String, Integer> unionMap = new HashMap<>();
 		
 		for(String s : keys) {
 			unionMap.put(s, map1.get(s));
 		}
 		for(String s : keys2) {
 			if(unionMap.get(s)==null) unionMap.put(s, map2.get(s));
 			else unionMap.put(s, Math.max(map1.get(s), map2.get(s)));
 		}
 		
 		List<String> unionKeys = new ArrayList<>(unionMap.keySet());
 		
 		for(String s : unionKeys) {
 			union+= unionMap.get(s);
 		}
 		double temp = 0;
 		if(inter==0&&union==0) temp = 1;
 		else temp = 1.0*inter/union;
 		answer = (int)Math.floor(temp*65536);
        return answer;
    }
}