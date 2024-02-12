import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();

        int cnt = 0;
        int temp = -2;
        int idx = -1;
        for(int i=0; i< progresses.length; i++){

            while(speeds[i]*cnt+progresses[i]<100){
                cnt++;
            }
            if(temp==cnt){
                ans.set(idx, ans.get(idx)+1);
            } else{
                ans.add(1);
                idx++;
                temp = cnt;
            }

        }
        answer = ans.stream().mapToInt(i->i).toArray();
        return answer;
    }
}