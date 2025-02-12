import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1에서 N까지의 최단거리 다익스트라

 */
public class Main {
    static int N,M;
    static ArrayList<int[]>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adjList[i] =  new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new int[]{e,w});
            adjList[e].add(new int[]{s,w});
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new int[]{1,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            visited[cur[0]] = true;
            if(cur[0]==N) break;
            for(int[] next : adjList[cur[0]]){
                if(visited[next[0]]) continue;
                int tmp = cur[1]+next[1];
                if(tmp<dist[next[0]]) {
                    dist[next[0]] = tmp;
                    pq.offer(new int[]{next[0], tmp});
                }
            }
        }
        System.out.println(dist[N]);


    }
}