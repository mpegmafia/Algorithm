import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
테트리스 모양 블럭 하나를 특정  NxM 모양 안에 넣었을 때
최대한의 점수를 얻는 경우의 수 출력
================================
입력:
    N, M (4<=N, M<=500)
    N줄동안 그래프
================================
DFS? 테트리스 모양이라는건 한점에서 4가지 모양으로 뻗어나가는 모든 경우의 수이다.
visited 배열로 재귀 관리, 한 점에서 4번 이동했을 때 최댓값 갱신후 재귀 탈출
만약 range를 벗어나면 continue
500x500x4x3x3 = 6백만?
========== ㅗ < 이모양이 구현이 안됨;;
 */
public class Main {
    static int N,M;
    static int[][] graph;
    static boolean[][] visited;

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited[i][j] = true;
                DFS(i,j,1,graph[i][j]);
                visited[i][j] = false;
                tShape(i,j);
            }
        }
        System.out.println(ans);

    }

    public static void DFS(int y, int x, int cnt, int sum){
        if(cnt==4){
            ans= Math.max(ans, sum);
            return;
        }
        for(int i=0; i<4; i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(!isInRange(ny, nx)) continue;
            if(visited[ny][nx]) continue;
            visited[ny][nx] = true;
            DFS(ny, nx, cnt+1, sum+graph[ny][nx]);
            visited[ny][nx] = false;
        }
    }
    public static void tShape(int y, int x){
        //T자 모양 블럭 따로 찾아주기

        OuterLoop:
        for(int i=0; i<4; i++){
            int tmp =graph[y][x];
            for(int j=0; j<3; j++){
                int idx = (i+j)%4;
                int ny = y+dy[idx];
                int nx = x+dx[idx];
                if(!isInRange(ny, nx)) continue OuterLoop;
                tmp += graph[ny][nx];
            }
            ans = Math.max(ans, tmp);
        }
    }

    public static boolean isInRange(int y, int x){
        return y>=0&&y<N&&x>=0&&x<M;
    }
}