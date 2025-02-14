/*
1~n 까지의 포인트
로봇마다 정해진 운송경로, m개의 포인트를 순서대로 지나가야함
1초마다 최단경로로 움직임, 이때 y좌표 먼저 변하고 그다음 x좌표를 이동함
이동중 같은 좌표에 로봇이 2대이상 모인다면 count++
모든 위험상황의 횟수를 return
==============================
points[i] = i+1번째 포인트의 좌표
routes[i][j] = i+1번째 로봇이 j+1번째 방문하는 포인트 번호
구현문제, 처음시작할때 겹쳐있다면 ++, 마지막 도착할때도 겹쳐있다면 ++ 해줘야함
로봇 이동 처리, 겹침 처리, 각각 로봇의 이동해야하는 좌표 처리
1. 각각 로봇은 class로 만들어서 시작 좌표, 경로 좌표들을 받아놓음 또한 move함수, 목적지에 도착했는지 체크하는 함수도 수행
    move함수는 움직인 좌표의 배열을 리턴(visited처리)
2. int배열을 visited로 만들어서 겹침이 동시에 2가 된다면 위험횟수 cnt+1해줌 (3부터는 무시)
3. 만약 도착지에 도착한다면 queue에서 빼내기

처음queue에 넣을 때 먼저 도착여부 체크, 도착지에 도착했다면 queue에 넣지말고 visited에만 체크
move함수는 먼저 y좌표 계산후 다르다면 y좌표 먼저, 같다면 x좌표 움직이기
경유좌표는 처음 초기화할 때 
배열형태로 받아놓기 그래서 인덱스로 현재 목표를 정해주기 ㄱㄱ

*/

import java.util.*;


class Solution {
    static class Robot{
        int r, c, idx;
        int[][] targets;
        
        public Robot(int r, int c, int size){
            this.r = r;
            this.c = c;
            this.idx = 0;
            this.targets = new int[size][2];
        }
        
        public void move(){
            if(this.r == this.targets[idx][0]) {
                if(this.c > this.targets[idx][1]){
                    this.c--;
                } else if (this.c < this.targets[idx][1]){
                    this.c++;
                }
            }
            else {
                if(this.r<this.targets[idx][0]) {
                    this.r++;
                } else if(this.r>this.targets[idx][0]){
                    this.r--;
                }
            }
            if(this.r==targets[idx][0]&&this.c==targets[idx][1])
                idx++;
        }
        
        public boolean isArrived(){
            return idx==targets.length;
        }
    }
        
        
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        ArrayDeque<Robot> q = new ArrayDeque<>();
        int[][] visited = new int[101][101];
        for(int i=0; i<routes.length; i++){
            int r = points[routes[i][0]-1][0];
            int c = points[routes[i][0]-1][1];
            int size = routes[i].length-1;
            Robot robot = new Robot(r, c, size);
            for(int j=1; j<routes[i].length; j++){
                robot.targets[j-1][0] = points[routes[i][j]-1][0];
                robot.targets[j-1][1] = points[routes[i][j]-1][1];
            }
            q.offer(robot);
            visited[robot.r][robot.c] ++;
            if(visited[robot.r][robot.c] ==2 ) answer++;
            
        }
        while(!q.isEmpty()){
            visited = new int[101][101];
            int size = q.size();
            for(int i = 0; i<size; i++){
                Robot bot = q.poll();
                bot.move();
                visited[bot.r][bot.c] +=1;
                if(visited[bot.r][bot.c] ==2) answer++;
                if(!bot.isArrived()){
                    q.offer(bot);
                }
            }
        }
        
        
        return answer;
    }
}