
/*
n개의 도시, 한 도시에서 다른 도시로 가는 m개의 버스. 각 버스는 비용이 있다.
모든 도시의 쌍 (A,B)에 대해서 A에서 B로 가는데 필요한 비용의 최솟값을 구하라.
------------------
n (1<=n<=100)
m (1<=m<=100,000)
m개의 줄동안 버스 정보 (출발, 도착, 비용) 시작도시와 도착도시가 같은 경우는 없다. 비용은 100,000 이하의 자연수
같은 노선의 버스가 여러 개일 수 있다.
---------------------
n개의 줄을 출력하고 i번째 줄의 j번째 숫자는 i에서 j로 가는데 필요한 최소 비용이다. 만약 갈 수 없는 경우 0을 출력한다.
---------------------
전형적인 그래프 문제.
모든도시에서 모든도시로 가는 값의 최솟값을 전부 구하는거니까 플로이드워셜?
플로이드 워셜의 핵심은 3중포문에 중간, 출발, 도착 순으로 for문을 돌리는 것.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] ans = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(ans[i], 100_000_001);
            ans[i][i] = 0;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ans[s][e] = Math.min(ans[s][e], w);
        }

        //floyd-washall
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int k=1; k<=N; k++){
                    ans[j][k] = Math.min(ans[j][k], ans[j][i] + ans[i][k]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                sb.append(ans[i][j]==100_000_001? 0:ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);



    }
}
