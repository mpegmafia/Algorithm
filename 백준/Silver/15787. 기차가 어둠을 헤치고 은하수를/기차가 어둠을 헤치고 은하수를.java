import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
N개의 기차, 20개의 좌석, M개의 명령
명령의 종류:
    1 i x : i번째 기차에 x번째 좌석에 사람을 태워라(이미 타있으면 스킵)
    2 i x : i번째 기차에 x번째 좌석에 앉은 사람 하차 (없으면 스킵)
    3 i : i번째 기차에 승객들은 모두 뒤로 한칸씩 이동(맨 마지막은 명령 후 하차)
    4 i : i번째 기차에 승객들은 모두 앞으로 한칸씩 이동(맨 앞자리는 명령 후 하차)
기차가 은하수를 건널때 조건:
    순서대로 지나간다.
    기차의 상태를 기록하여 이미 기록되어 있는 상태면 못 지나간다.
은하수를 건널 수 있는 기차의 수 출력
=====================================================
입력:
    N, M (1<=N, M<=100,000)
    M줄동안 명령의 상태
====================================================
N,M이 최대 10만
M의 최악의 수를 가정하면 10만이 배열을 다 민다고 가정, 20번을 밀어야하므로 2백만
그 후 중복체크에서 2^20가지= 100만 패턴 경우의수가 나오고 이걸 10만개랑 비교시 터짐
중복체크로직이 중요 => 비트마스킹으로 값들 다 더해서 가지고 있기?
흠,,
명령어 수행 => 중복체크

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<boolean[]> trains = new ArrayList<>();
        for(int i=0; i<=N; i++){
            trains.add(i, new boolean[21]);
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int train = Integer.parseInt(st.nextToken());
            if(order==1){
                int passenger = Integer.parseInt(st.nextToken());
                trains.get(train)[passenger] = true;
            } else if(order==2){
                int passenger = Integer.parseInt(st.nextToken());
                trains.get(train)[passenger] = false;
            } else if (order==3){
                moveBackward(trains.get(train));
            } else{
                moveForward(trains.get(train));
            }
        }
        int ans = 0;
        List<Integer> passedTrains = new ArrayList<>();
        for(int i=1; i<=N; i++){
            boolean[] train = trains.get(i);
            int bit = 0;
            for(int j=1; j<=20; j++){
                if(train[j]){
                    bit |= 1 << j;
                }
            }
            //System.out.println(bit);
            if(!passedTrains.contains(bit)){
                ans++;
                passedTrains.add(bit);
            }
        }
        System.out.println(ans);
    }

    public static void moveForward(boolean[] train){
        for(int i=1; i<20; i++){
            train[i] = train[i+1];
        }
        train[20] = false;
    }

    public static void moveBackward(boolean[] train){
        for(int i=20; i>1; i--){
            train[i] = train[i-1];
        }
        train[1] = false;
    }
}