import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
NxN크기의 graph, 1~K까지의 바이러스 종류
바이러스는 상하좌우로 증식, 번호가 낮은 순으로 증식한다.
만약 그 곳에 바이러스가 이미 존재한다면 증식 불가능
S초가 지난후 Y,X에 존재하는 바이러스 종류를 출력하라 (없다면0 출력)
=======================
입력:
    N,K
    N줄동안 graph
    S,Y,X
=======================
BFS를 돌리는데 PriorityQueue를 사용해서 완탐 돌리면 될 것 같다.
범위체크
바이러스 이미 존재하는지 체크
 */
public class Main {

    static class Virus implements Comparable<Virus>{
        int y,x,w;
        public Virus(int y,int x,int w){
            this.y = y;
            this.x = x;
            this.w = w;
        }
        public int compareTo(Virus o){
            return this.w - o.w;
        }
    }

    static int N,K;
    static int S,Y,X;
    static int[][] graph;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static PriorityQueue<Virus> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j]>0){
                    pq.offer(new Virus(i,j,graph[i][j]));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken()) - 1;
        X = Integer.parseInt(st.nextToken()) - 1;

        int cnt = 0;
        while(cnt<S){
            spread();
            cnt++;
        }
        System.out.println(graph[Y][X]);

    }

    private static void spread() {
        int size = pq.size();

        //pq에 바로 담지 말고 리스트에 담아놓고 pq에 한꺼번에 업데이트
        List<Virus> list = new ArrayList<>();
        for(int i=0; i<size; i++){
            Virus v = pq.poll();
            for(int j=0; j<4; j++){
                int ny = v.y+dy[j];
                int nx = v.x+dx[j];
                if(!isInRange(ny, nx)) continue;
                if(graph[ny][nx]>0) continue;
                list.add(new Virus(ny, nx, v.w));
                graph[ny][nx] = v.w;
            }
        }
        pq.addAll(list);
    }

    private static boolean isInRange(int y, int x){
        return y>=0 && y<N && x>=0 && x<N;
    }
}