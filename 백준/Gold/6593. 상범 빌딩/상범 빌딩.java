import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/*
정육면체는 지나갈수 없거나 있거나
대각선이동불가, 정육면체에서 인접한 6개의 칸으로만 이동 가능
======================
입력:
    테스트 케이스 T
    L,R,C (1<= L,R,C <= 30)
    L 은 z축, R은 y축, C는 x축
    각 칸은 지나갈 수 없으면 '#', 지나갈 수 있으면 '.' 로 표현
    시작지점은 S로 표현 탈출할 수 있는 탈출구는 E로 표현
    만약 L,R,C가 모두 0 이면 입력의 끝.
======================================
3차원 BFS 문제
탈출이 불가능 할 수도 있다라는 것을 유의

만약 다음칸이 방문한칸이면 X
'#'라면 X
범위 밖이라면 X
E라면 탈출

 */
public class Main {

    static int L,R,C;
    static int[] dy = {1,0,-1,0,0,0};
    static int[] dx = {0,1,0,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L==0 && R==0 && C==0) break;
            char[][][] arr = new char[L][R][C];
            for(int i=0; i<L; i++){
                for(int j=0; j<R; j++){
                    //바로 char Array로 변경
                    arr[i][j] = br.readLine().toCharArray();
                }
                //공백 털기
                br.readLine();
            }

            BFS(arr);


        }

        System.out.println(sb);
    }

    private static void BFS(char[][][] arr) {
        int[] start = new int[3];
        //int[] end = new int[3];
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[L][R][C];

        for(int i=0; i<L; i++){
            for(int j=0; j<R; j++){
                for(int k=0; k<C; k++){
                    if(arr[i][j][k]== 'S') start = new int[]{i,j,k};
                    //if(arr[i][j][k]== 'E') end = new int[]{i,j,k};
                }
            }
        }
        //add 대신 offer를 써야 exception이 아닌 false가 반환됨(에러시)
        q.offer(start);
        visited[start[0]][start[1]][start[2]] = true;

        while(!q.isEmpty()){
            int size =q.size();

            for(int i=0; i<size; i++) {
                int[] curr = q.poll();
                int cz = curr[0];
                int cy = curr[1];
                int cx = curr[2];
                for(int j=0; j<6; j++){
                    int nz = cz + dz[j];
                    int ny = cy + dy[j];
                    int nx = cx + dx[j];
                    if(!isInRange(nz,ny,nx)) continue;
                    if(visited[nz][ny][nx]) continue;
                    if(arr[nz][ny][nx]== '#') continue;
                    if(arr[nz][ny][nx]== 'E'){
                        sb.append("Escaped in ")
                                .append(cnt)
                                .append(" minute(s).")
                                .append("\n");
                        return;
                    }
                    visited[nz][ny][nx] = true;
                    q.offer(new int[]{nz,ny,nx});
                }
            }
            cnt++;

        }
        sb.append("Trapped!").append("\n");

    }

    private static boolean isInRange(int z, int y, int x){
        return z>=0 && z<L && y>=0 && y<R && x>=0 && x<C;
    }

}