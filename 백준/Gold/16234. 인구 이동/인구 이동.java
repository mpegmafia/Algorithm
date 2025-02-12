import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
인구차이가 L명 이상 R명 이하라면 두나라 국경선 개방
국경선이 모두 열렸다면 인구 이동 시작
만약 3개이상 나라가 인접한 칸만을 이용해 이동한다면 연합이라 칭함
연합을 이루고 있는 각 칸의 인구수는 연합의 인구수 / 연합을 이루고 있는 칸의 개수가 된다.(소수점은 버림)
연합을 해체하고 모든 국경선을 닫는다
인구이동이 며칠동안 발생하는가?
============================
입력:
    N, L, R (1<=N<=50, 1<=L<=R<=100)
    N줄동안 graph 상태
============================
그래프 상태를 두개 써서 구현?
- 어디까지 국경이 이어지는지 체크해야함
- 이어지는 국경끼리 한번에 더한 후 나눈 값으로 인구 재분배를 해줘야함
1. 2중포문으로 완탐후 (아래랑 오른쪽만 보면 됨) 만약 L이상 R차이가 난다면 flag값 트루로 변경
2. 그 후 해당 좌표를 BFS로 돌려서 L이상 R이하의 모든 좌표들을 visited에 넣음
3. 다음으로 visited에서 true라면 해당 좌표부터 BFS로 타고 들어가서 플러드필로 모든 값과 cnt를 받아놓고 좌표들도 다른 큐에 저장해놓음
4. 그 후 다른 그래프에 그 좌표값들을 그대로 저장.
5. 반복문 다시돌때 그래프 깊은 복사, visited 초기화
 */
public class Main {

    static int N,L,R;
    static int[][] graph, copyGraph;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];
        graph = new int[N][N];
        copyGraph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j]) continue;
                    if(isUnited(i,j)){
                        flag = true;
                        visited[i][j] = true;
                        floodFill(i,j);
                    } else copyGraph[i][j] = graph[i][j];
                }
            }
            if(flag){
                visited = new boolean[N][N];
                for(int i=0; i<N; i++){
                    System.arraycopy(copyGraph[i], 0, graph[i], 0, N);
                }
                copyGraph = new int[N][N];
                ans++;
            }
        }
        System.out.println(ans);

    }


    public static void floodFill(int y, int x){
        Queue<int[]> q =new ArrayDeque<>();
        Queue<int[]> q2 =new ArrayDeque<>();
        q.offer(new int[]{y,x});
        q2.offer(new int[]{y,x});
        int cnt = 1;
        int sum = graph[y][x];
        while(!q.isEmpty()){
            int cy = q.peek()[0];
            int cx = q.peek()[1];
            q.poll();
            for(int i=0; i<4; i++){
                int ny = cy+dy[i];
                int nx = cx+dx[i];
                if(!isInRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                int diff = Math.abs(graph[cy][cx] - graph[ny][nx]);
                if(diff<L || diff>R) continue;
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
                cnt++;
                sum += graph[ny][nx];
                q2.offer(new int[]{ny,nx});
            }
        }
        int population = sum/cnt;
        while(!q2.isEmpty()){
            int cy = q2.peek()[0];
            int cx = q2.peek()[1];
            q2.poll();
            copyGraph[cy][cx] = population;
        }
    }
    //아래, 오른쪽에 인접한 나라 있는지 확인
    public static boolean isUnited(int y, int x){
        for(int i=1; i<3; i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(!isInRange(ny,nx)) continue;
            int tmp = Math.abs(graph[y][x]-graph[ny][nx]);
            if (tmp>=L && tmp<=R) return true;
        }
        return false;
    }

    public static boolean isInRange(int y, int x){
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}