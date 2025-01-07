import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
N은 짝수
N/2 명으로 이루어진 스타트팀과 링크팀
능력치 배열에서 i번사람과 j번 사람이 같은 팀에 있을때 더해지는 능력치가 나옴
능력치의 차이를 최소로 하는 팀 구성을 구해보자
===========================
입력:
    N (4<= N <= 20)
    N개의 줄에 시너지가 주어짐
===========================
조합문제,
조합으로 반을 갈라서 팀을 정한 후 그 두 팀간의 힘 차이를 최소로 만들자

N은 최대 20이므로 완탐을 돌리면 된다
 */
public class Main {
    static int N;
    static int[][] graph;
    static boolean[] team1, team2;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        team1 = new boolean[N];
        team2 = new boolean[N];
        combi(0,0);
        System.out.println(ans);

    }

    public static void combi(int depth, int start){
        if(depth ==N/2) {
            int sum1 = 0;
            int sum2 = 0;
            //System.out.println("team completed ======");
            //System.out.println(Arrays.toString(team1));
            for(int i=0; i<N; i++){
                if(team1[i]) team2[i] = false;
                else team2[i] = true;
            }
            //System.out.println(Arrays.toString(team2));

            for(int i=0; i<N; i++){
                if(team1[i]){
                    for(int j=i+1; j<N; j++){
                        if(team1[j]){
                            sum1 += graph[i][j] + graph[j][i];
                        }
                    }
                }
                else if (team2[i]){
                    for(int j=i+1; j<N; j++){
                        if(team2[j]){
                            sum2 += graph[i][j] + graph[j][i];
                        }
                    }
                }
            }
            ans = Math.min(ans, Math.abs(sum1-sum2));
            return;
        }

        //12를 픽하나 34를 픽하나 두 팀은 똑같잖아..?
        for(int i = start; i<N; i++){
            team1[i] = true; //depth가 아니라 i로 true 표시 해줘야함
            combi(depth+1, i+1);
            team1[i] = false;//백트래킹느낌으로 false처리 다시해주기
        }

    }
}