import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
다익스트라?
연결여부만 체크하면되는거니까.. 음..
유니온파인드?
 */
public class Main {

    static int[][] adjMatrix;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        adjMatrix = new int[N+1][N+1];
        parents = new int[N+1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        int[] plans = new int[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            plans[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(plans[0]);

        boolean[] visited = new boolean[N+1];
        visited[plans[0]] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
//            System.out.println(Arrays.toString(parents));
            for(int i=1; i<=N; i++){
                if(adjMatrix[node][i]==1 && !visited[i]){
                    visited[i] = true;
                    union(node, i);
                    q.offer(i);
                }
            }
        }

        boolean flag = true;
        for(int i=0; i<M-1; i++){
            if(find(plans[i])!=find(plans[i+1])){
                flag = false;
                break;
            }
        }
        System.out.println(flag? "YES":"NO");



    }

    public static int find(int x){
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);

    }

    public static void union(int x, int y){
        int a = find(x);
        int b = find(y);
        parents[a] = b;
    }

}
