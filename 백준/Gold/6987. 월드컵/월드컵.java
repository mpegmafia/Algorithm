import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
6나라가 토너먼트, 총 15경기를함
승무패가 정해지니까 3^15 = 대략 천오백만
하지만 우리는 경기 결과 4개중에 이 결과가 올바른 결과인지만 확인하면 됨.
백트래킹은 일단 확실하다.(물론 안해도 풀긴 했음)
----------------
4번 반복
    팀별 승,무,패가 총 18개 띄어쓰기로 나옴
----------------
각각 팀별 승무패를 일단 받자.
모든 승 무 패의 합이 30이 아니라면 실패처리

DFS에서는 left right팀이 승일때, 무일때, 패일때를 각각 재귀로 들어가준다.
이때 해당 팀의 결과표를 --해주면서 들어가야하고, 가기전에 둘 다 0이 초과하는지 체크해준다.
left는 0부터, right는 1부터 시작
right를 계속 ++해준다.
만약 right++ 값이 6이라면 left++한 후 right는 left+1
left가 5가 되면 더이상 경기가 없는 것이고


 */
public class Main {
    static final int WIN = 0, DRAW = 1, LOSE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            int[][] result = new int[6][3];
            int sum = 0;
            for(int j=0; j<6; j++){
                result[j][WIN] = Integer.parseInt(st.nextToken());
                result[j][DRAW] = Integer.parseInt(st.nextToken());
                result[j][LOSE] = Integer.parseInt(st.nextToken());
                sum = sum + result[j][WIN] + result[j][DRAW] + result[j][LOSE];
            }
            if(sum!=30){
                sb.append(0+" ");
            }
            else{
                sb.append(DFS(0, 1, result)?1:0).append(" ");
            }

        }
        System.out.println(sb);

    }

    public static boolean DFS(int left, int right, int[][] result){
        if(left == 5){
            return true;
        }
        int nextLeft = left;
        int nextRight = right+1;
        if(nextRight==6){
            nextLeft +=1;
            nextRight = nextLeft+1;
        }
        //left가 이겼을 때
        if(result[left][WIN]>0 && result[right][LOSE]>0){
            result[left][WIN]--;
            result[right][LOSE]--;
            if(DFS(nextLeft, nextRight, result)) return true;
            result[left][WIN]++;
            result[right][LOSE]++;
        }

        //비겼을 때
        if(result[left][DRAW]>0 && result[right][DRAW]>0){
            result[left][DRAW]--;
            result[right][DRAW]--;
            if(DFS(nextLeft, nextRight, result)) return true;
            result[left][DRAW]++;
            result[right][DRAW]++;
        }
        //right가 이겼을 때

        if(result[left][LOSE]>0 && result[right][WIN]>0){
            result[left][LOSE]--;
            result[right][WIN]--;
            if(DFS(nextLeft, nextRight, result)) return true;
            result[left][LOSE]++;
            result[right][WIN]++;
        }


        return false;


    }


}
