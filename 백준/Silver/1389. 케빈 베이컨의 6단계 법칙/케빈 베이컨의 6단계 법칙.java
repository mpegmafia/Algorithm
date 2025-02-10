import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
모든 노드와의 최소거리를 계산해서 가장 작은 거리의 합을 가지는 노드 구하기
모든 노드는 서로 어떻게든 연결되어 있다.
=========================
입력:
    유저 수 N, 친구 관계의 수 M (2<=N<=100) (1<=M<=5000)
    M줄동안 친구 관계 (양방향)
=========================
레벨별 BFS? 한점에서 모든점 최단거리 구하기니까 다익스트라?
가중치가 없으니까 일단 레벨별 BFS로 ㄱㄱ
인접리스트로 갈수있는 노드를 저장해놓은 후
레벨별BFS로 방문하지 않은 노드를 방문할때마다 cnt값 더해줌
    처음q에 node집어넣음
    visited[node] true로 만들어줌
    node에 해당하는 간선들 전부 돌면서 visited확인
    없다면 visited체크후 q에 넣고 tmp값에 현재 cnt값 더해주기
    그후 cnt++;
    만약 모든 노드를 방문했다면 큐종료
 */
public class Main {
    static int N,M;
    static List<List<Integer>> adjList = new ArrayList<>();
    static int ans;
    static int minKevinBacon = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        for(int i=1; i<=N; i++){
            int temp = BFS(i);
            if(minKevinBacon > temp){
                ans = i;
                minKevinBacon = temp;
            }
        }
        System.out.println(ans);


    }

    public static int BFS(int node){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);
        int cnt = 1;
        int tmp = 0;
        visited[node] = true;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int cur = q.poll();
                for(int next : adjList.get(cur)){
                    if(!visited[next]){
                        visited[next] = true;
                        q.add(next);
                        tmp += cnt;
                    }
                }
            }
            cnt++;
        }
        return tmp;
    }
}