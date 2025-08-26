import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
두 노드를 모두 조상으로 가지면서 깊이가 가장 깊은 ( 두 노드에 가장 가까운) 노드 찾기
--------------------------
T (테케 갯수)
N (1<=N<=10,000)
N-1개의 줄에 트리를 구성하는 간선 정보
A B (A가 B의 부모라는 뜻)
v1 v2 (정답을 구해야할 두 노드)
---------------------------
BFS로 돌려서 가장 먼저 공통으로 만나는 녀석이 정답.
B->A로 가는 일방향 간선만 만들어놓기(아래는 갈 필요가 없음)
간선은 간선 리스트로 저장
visited배열로 방문체크를 해서 true인 것을 만나면 바로 exit후 해당 노드 출력
 */
public class Main {
    static int N;
    static List<Integer>[] adjList;
    static boolean[] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test=0; test<T; test++){
            N = Integer.parseInt(br.readLine());
            adjList = new ArrayList[N+1]; //1부터 N까지 있음.
            visited = new boolean[N+1];
            for(int i=1; i<=N; i++){
                adjList[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N-1; i++){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(br.readLine());
                adjList[b].add(a);
            }
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int ans = bfs(v1, v2);
            sb.append(ans + "\n");
        }
        System.out.println(sb);

    }

    public static int bfs(int v1, int v2){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(v1);
        q.offer(v2);
        visited[v1] = true;
        visited[v2] = true;
        while(!q.isEmpty()){
            int v = q.poll();
            for(int nextV : adjList[v]){
                if(visited[nextV]) return nextV;
                visited[nextV] = true;
                q.offer(nextV);
            }
        }
        return 0;
    }
}
