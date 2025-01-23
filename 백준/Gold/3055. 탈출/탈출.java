import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
R행 C열의 티떱숲
비어있는곳은 '.', 물은 '*', 돌은'X', 비버굴은 'D', 고슴도치 위치는 'S'
고슴도치는 매분 상하좌우로 움직일수 있다. 물은 매분마다 상하좌우로 확장함
물, 고슴도치 모두 돌로 이동할 수 없다.
고슴도치는 물이 있는 구역에 갈 수 없다.
물은 비버의 소굴로 이동할 수 없다.
비버의 굴로 이동할 수 있는 가장 빠른시간 출력
만약 할 수없다면 kaktus출력
=============
입력:
    R,C (1<=R,C,<=50)
    R줄동안 그래프
=============
레벨별 BFS돌리기. 큐 두개를 사용하자.
먼저 물을 퍼뜨리고 그다음 고슴도치를 움직이기
물:
    범위체크
    돌체크
    비버굴 체크
    간곳인지 체크 (*인지 체크)
비버:
    범위체크
    돌체크
    물체크
    비버굴체크(목표도착)
 */
public class Main {
    static int R,C;
    static char[][] graph;
    static Queue<int[]> waterQ = new ArrayDeque<>();
    static Queue<int[]> beaverQ = new ArrayDeque<>();
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = s.charAt(j);
                if(graph[i][j]=='S') beaverQ.offer(new int[]{i,j});
                if(graph[i][j]=='*') waterQ.offer(new int[]{i,j});
            }
        }
        int ans = BFS();
        System.out.println(ans==0?"KAKTUS":ans);



    }
    private static int BFS(){
        boolean[][] visited = new boolean[R][C];
        visited[beaverQ.peek()[0]][beaverQ.peek()[1]] = true;
        int ans = 0;
        while(!beaverQ.isEmpty()){
            int waterSize = waterQ.size();
            for(int i=0; i<waterSize; i++){
                int[] water = waterQ.poll();
                for(int j=0; j<4; j++){
                    int y = water[0] + dy[j];
                    int x = water[1] + dx[j];
                    if(!isInRange(y, x)) continue;
                    if(graph[y][x]=='X') continue;
                    if(graph[y][x]=='D') continue;
                    if(graph[y][x]=='*') continue;
                    graph[y][x] = '*';
                    waterQ.add(new int[]{y,x});
                }
            }
            int beaverSize = beaverQ.size();
            for(int i=0; i<beaverSize; i++){
                int[] beaver = beaverQ.poll();
                for(int j=0; j<4; j++){
                    int y = beaver[0] + dy[j];
                    int x = beaver[1] + dx[j];
                    if(!isInRange(y, x)) continue;
                    if(visited[y][x]) continue;
                    if(graph[y][x]=='X') continue;
                    if(graph[y][x]=='*') continue;
                    if(graph[y][x]=='D') return ans+1;
                    visited[y][x] = true;
                    beaverQ.offer(new int[]{y,x});
                }
            }
            ans++;
        }

        return 0;
    }
    private static boolean isInRange(int y, int x){
        return y>=0 && y<R && x>=0 && x<C;
    }
}