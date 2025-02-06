import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
A에서 B까지 가는데 걸리는 최소 비용
=====================
입력:
    N (1<=N<=1,000)
    M (1<=M<=100,000)
    M줄동안 간선 정보(단방향, 출발, 도착, 비용)
    출발점, 도착점  (무조건 갈 수 있음)
=====================
다익스트라 알고리즘인가?
출발점에서 시작해 도착지점이 나올때까지 최소가중치로 그래프 순회하기
출발 node의 간선정보를 pq에 집어넣자
pq에서 제일 weight가 낮은 node 부터 poll
현재 node 방문처리
현재 node가 도착 노드인지 확인
    도착node라면 현재까지의 weight return
그 후 해당 node의 간선들을 전부 순회
만약 해당 간선 도착노드가 방문안한 노드라면
    현재까지의 weight를 더해서 pq에 넣기
 */
public class Main {
    static int N,M;
    static List<List<int[]>> adjList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(s).add(new int[]{e,w});
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int ans = Dijkstra(start, end);
        System.out.println(ans);



    }

    private static int Dijkstra(int start, int end) {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> a[1] - b[1]);
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new int[]{start, 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;
            if(cur[0]==end) break;
            for(int[] adj : adjList.get(cur[0])){
                int nextW = cur[1]+adj[1];
                if(nextW<dist[adj[0]]){
                    dist[adj[0]] = nextW;
                    pq.offer(new int[]{adj[0], nextW});
                }
            }
        }
        return dist[end];

        /* 최적화 X (중복값이 들어감)
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> a[1] - b[1]);
        pq.add(new int[]{start,0});
        int tmp = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == end){
                tmp = cur[1];
                break;
            }
            visited[cur[0]] = true;
            for(int[] adj : adjList.get(cur[0])){
                if(visited[adj[0]]) continue;
                pq.add(new int[]{adj[0], cur[1]+adj[1]});
            }
        }
        return tmp;
         */



    }
}