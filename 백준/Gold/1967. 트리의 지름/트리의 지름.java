import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
무방향 그래프의 간선들 정보가 주어지면
정점들 사이 가장 경로가 긴 거리를 구하라
========================
입력:
    노드의 갯수 n (1<=n<=10,000)
    간선 정보 parent child weight
========================
노드 갯수가 만개이므로 플로이드워셜이나 다익스트라는 불가능할거같다
트리형식의 그래프라는 것을 이용? 항상 자식=>자식으로 갈때 최고값일 것이다.
    그래도 n이 만개라면 자식노드가 대략 5천개정도 있음, 또한 1->2, 2->3 그래프 형식이라면 자식->부모에서 최댓값이 나옴
만약 pq로 간선정보를 다 넣은다음 높은순으로 집어넣는다고 가정
    엣지케이스 -> 현재의 10인길보다 2로 가는 길이 추후엔 더 길이가 길어질 수 있다
트리라는 점을 이용해 한 노드를 임의로 찍고 거기서 가장 멀리있는 노드를 구한다.
그 후 그 노드에서 제일 멀리있는 노드까지의 거리를 구하면 그게 트리의 지름이 된다.
BFS나 DFS로 구하기
항상 루트에서 시작후 가장 멀리있는 노드를 구하기
처음 1에서 시작, 할때마다 weight 갱신 후 현재 최고 weight보다 높다면 제일멀리있는 노드 갱신
 */
public class Main {
    static class Node{
        int e,w;
        public Node(int e, int w){
            this.e = e;
            this.w = w;
        }
    }

    static List<Node>[] graph;

    static int farthestNode = 0;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e,w));
            graph[e].add(new Node(s,w));
        }

        BFS(1);
        int ans = BFS(farthestNode);
        System.out.println(ans);


    }

    public static int BFS(int s){
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        visited[s] = true;
        q.offer(new Node(s, 0));
        int tmp = 0;

        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.w>tmp){
                tmp = node.w;
                farthestNode = node.e;
            }
            for(Node n : graph[node.e]){
                if(!visited[n.e]){
                    visited[n.e] = true;
                    Node newNode = new Node(n.e, n.w+node.w);
                    q.offer(newNode);
                }
            }
        }
        return tmp;

    }
}