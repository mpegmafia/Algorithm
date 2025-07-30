import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
같은색 뿌요가 4개이상 상하좌우로 연결되면 1연쇄
위에 뿌요들이 다시 중력으로 인해 아래로 내려오고 새로운 연쇄 시작
터질수있는 뿌요가 여러개라면 동시에 터지고 이래도 1연쇄이다.
------------------------
입력:
12x6의 그래프
.는 빈공간 R,G,B,Y,P는 뿌요 색
------------------------
단순 완탐 문제같다. 12X6이니 시간도 충분하고.
visited 배열을 놓고 '.'가 아닌 문자를 볼때마다 탐색
BFS를 돌려서 4개이상 탐색하면 탐색했던 위치들 전부 저장해놨다가 한꺼번에 .로 바꾸기
그 후 연쇄를 +1하고 그래프 중력작용 처리.
그래프의 밑에서부터 올라와서 (M을 기준으로 N-1부터 0까지 탐색)
 */
public class Main {
    static char[][] graph;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0,1,0,-1};
    static ArrayDeque<int[]> q = new ArrayDeque<int[]>();

    static ArrayDeque<int[]> gravityQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new char[12][6];
        for(int i=0; i<12; i++){
            graph[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        while(true){
            gravityQ.clear();
            visited = new boolean[12][6];
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(graph[i][j]!='.'&&!visited[i][j]){
                        BFS(i, j);
                    }
                }
            }
            if(gravityQ.isEmpty()) break;
            ans++;
            gravity();
//            for(int i=0; i<12; i++){
//                System.out.println(Arrays.toString(graph[i]));
//            }
//            System.out.println();
        }

        System.out.println(ans);

    }

    public static void BFS(int y, int x){
        visited[y][x] = true;
        q.clear();
        q.offer(new int[]{y,x});
        int tmp = 1; //몇개 연결되어있는지 갯수확인
        gravityQ.offer(new int[]{y,x});

        while(!q.isEmpty()){
            int cy = q.peek()[0];
            int cx = q.peek()[1];
            q.poll();
            for(int i=0; i<4; i++){
                int ny = cy+dy[i];
                int nx = cx+dx[i];
                if(!isInRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(graph[ny][nx] != graph[cy][cx]) continue;
                visited[ny][nx] = true;
//                System.out.println("===BFS check==="+"char "+graph[cy][cx]+ " cy: "+cy+" x: "+cx+" new char : "+graph[ny][nx]+" ny: "+ny+" nx: "+nx);
                q.offer(new int[]{ny, nx});
                tmp++;
//                System.out.println("tmp = "+tmp);
                gravityQ.offer(new int[]{ny,nx});
            }
        }

//        System.out.println("before tmp");
//        System.out.println(Arrays.deepToString(gravityQ.toArray()));
        if(tmp<4){
            for(int i=0; i<tmp; i++){
                gravityQ.pollLast();
            }
        }
//        System.out.println("after tmp");
//        System.out.println(Arrays.deepToString(gravityQ.toArray()));
    }

    public static void gravity(){
//        System.out.println(Arrays.deepToString(gravityQ.toArray()));
//        System.out.println("============before delete============");
//        for(int i=0; i<12; i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        System.out.println();
        while(!gravityQ.isEmpty()){
            int y = gravityQ.peek()[0];
            int x = gravityQ.peek()[1];
            gravityQ.poll();
            graph[y][x] = '.';
        }

//        System.out.println("============after delete============");
//        for(int i=0; i<12; i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }

//        for(int j=0; j<6; j++){
//            int u = 10;
//            int d = 11;
//            while(u>=0){
//                if(graph[d][j]!='.'){
//
//                }
//            }
//        }
        for(int j=0; j<6; j++){
            int idx = 11;
            for(int i=11; i>=0; i--){
                if(graph[i][j] == '.'){
                    continue;
                } else{
//                    System.out.println("before down");
//                    for(int k=0; k<12; k++){
//                        System.out.println(Arrays.toString(graph[k]));
//                    }
                    if(idx==i){
                        idx--;
                        continue;
                    }
                    graph[idx][j] = graph[i][j];
                    graph[i][j] = '.';
//                    System.out.println("after down");
//                    for(int k=0; k<12; k++){
//                        System.out.println(Arrays.toString(graph[k]));
//                        }
                    idx--;
                }
            }
        }
//        System.out.println("============after Gravity============");
//        for(int i=0; i<12; i++){
//            System.out.println(Arrays.toString(graph[i]));
//        }
    }

    public static boolean isInRange(int y, int x){
        return y>=0 && y<12 && x>=0 && x<6;
    }
}
