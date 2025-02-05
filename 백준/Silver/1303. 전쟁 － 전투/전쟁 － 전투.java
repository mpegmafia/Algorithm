import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
플러드필 문제다 완탐 ㄱㄱ
 */
public class Main {
    static int white = 0;
    static int black = 0;
    static int R,C;

    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i=0; i<R; i++){
            char[] chars = br.readLine().toCharArray();
            map[i] = chars;
        }
        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!visited[i][j]) BFS(i, j);
            }
        }
        System.out.println(white+" "+black);

    }

    public static void BFS(int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        visited[y][x] = true;
        int cnt = 1;
        q.offer(new int[]{y,x});
        char c = map[y][x];

        while(!q.isEmpty()){
            int cy = q.peek()[0];
            int cx = q.peek()[1];
            q.poll();

            for(int i=0; i<4; i++){
                int ny = cy+dy[i];
                int nx = cx+dx[i];
                if(!isInRange(ny,nx)) continue;
                if(visited[ny][nx]) continue;
                if(map[ny][nx] != c) continue;
                q.offer(new int[]{ny, nx});
                visited[ny][nx] = true;
                cnt++;
            }
        }
        if (c=='B') black = black+(cnt*cnt);
        else white = white+(cnt*cnt);

    }

    public static boolean isInRange(int y, int x){
        return y>=0&&y<R&&x>=0&&x<C;
    }
}