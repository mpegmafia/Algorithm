import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
나이트가 이동하는 방법은 2칸과 1칸의 조합이다.
좌2칸 위1칸, 좌2칸 아래 1칸, 좌1칸 위2칸, 좌1칸 아래2칸
우2칸 위1칸, 우2칸 아래 1칸, 우1칸 위2칸, 우1칸 아래2칸

한변의 길이 l 이 최대 400이므로 400*400 = 160000
테스트케이스가 몇개인지 안나와있지만 최대 16만을 BFS로 돈다고 가정하면500개까진 가능하다
일단 완탐 ㄱㄱ륑
============================
입력:
    테스트케이스 N
    체스판 한변의 길이 l
    둘째줄엔 현재 나이트가 있는 칸
    셋째줄엔 나이트가 가야하는 칸
============================
visited 배열이랑 그 단계형 BFS였나 머시기로 고고
===============
왜 나는 350~400ms 가 나오지 다른 코드는 200ms인데
 */
public class Main {

    static int[] dy = {1,-1,2,-2,1,-1,2,-2};
    static int[] dx = {-2,-2,-1,-1,2,2,1,1};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            int l = Integer.parseInt(br.readLine());
            //이부분 리팩토링하는법?
            st = new StringTokenizer(br.readLine());
            int[] current = new int[2];
            current[0] = Integer.parseInt(st.nextToken());
            current[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] target = new int[2];
            target[0] = Integer.parseInt(st.nextToken());
            target[1] = Integer.parseInt(st.nextToken());

            BFS(l,current, target);
        }
        System.out.println(sb);

    }

    private static void BFS(int l, int[] current, int[] target) {
        boolean[][] visited = new boolean[l][l];
        Queue<int[]> q = new ArrayDeque<int[]>();
        q.add(current);
        visited[current[0]][current[1]] = true;
        int count = 0;

        OuterLoop:
        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0; i<size; i++){
                int[] curr = q.poll();
                int cy = curr[0];
                int cx = curr[1];
                if (cy == target[0] && cx == target[1]) break OuterLoop;
                for(int j=0; j<8; j++){
                    int ny = cy+dy[j];
                    int nx = cx+dx[j];
                    if(!isInRange(l, ny, nx)) continue;
                    if(visited[ny][nx]) continue;
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
            count++;
        }
        sb.append(count).append("\n");

    }

    private static boolean isInRange(int l, int y, int x){
        return x>=0 && x<l && y>=0 && y<l;
    }
}