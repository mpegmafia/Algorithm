/*
초침이 시침/분침과 겹칠 때마다 알람이 울림
초당 각도의 움직임을 계산
24시간 = 86400초 -> 오전, 오후 2로 나누면 43200 => 360/43200 = 0.008333...
60분 = 3600초 => 360/3600 = 0.1
360/60 = 6
겹치는 각도는 현재 각도에서 +1초후 각도를 계산해서 겹치는지 안겹치는지 계산하기
    현재각도 < 목표침 && 다음각도 >= 1초후 목표침
만약 겹치는 시간이 12시 0분이거나 00시 00분이면 한번 빼주기
*/
class Solution {
    static double hAngle = (double) 360/43200;
    static double mAngle = 0.1;
    static double sAngle = 6.0;
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = h1*60*60 + m1*60 + s1;
        int endTime = h2*60*60 + m2*60 + s2;
        if (startTime == 0 || startTime == 12*60*60) answer++;
        for(int i = startTime ; i<endTime ; i++){
            double curHour = (i*hAngle)%360;
            double curMin = (i*mAngle)%360;
            double curSec = (i*sAngle)%360;
            
            double nextHour = (i+1)*hAngle%360 == 0 ? 360 : (i+1)*hAngle%360;
            double nextMin = (i+1)*mAngle%360 == 0 ? 360 : (i+1)*mAngle%360;
            double nextSec = (i+1)*sAngle%360 == 0 ? 360 : (i+1)*sAngle%360 ;
            
            if(curSec<curHour && nextHour<=nextSec) {
                answer++;
            }
            
            if(curSec<curMin && nextMin<=nextSec) {
                answer++;
            }
            if(i+1 == 12*60*60) answer--;
            
            
            
        }
        
        return answer;
    }
}