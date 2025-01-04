import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
R*C 크기의 직사각형 격자판
각 칸은 비어있거나 폭탄이 들어있다.
폭탄이 있는 칸은 3초후에 폭발하고 상하좌우가 같이 파괴됨.
다만 연쇄반응은 없다.
봄버맨은 다음과 같이 활동한다.
    처음에 일부 칸에 폭탄 설치, (설치된 시간은 같다.)
    다음 1초는 아무것도 하지 않는다.
    다음 1초는 폭탄이 설치되어있지 않은 모든 칸에 폭탄을 설치된다. (동시에 설치됐다고 가정)
    1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
    그 후 파괴된 칸에 다시 폭탄을 설치하는 것을 반복한다.
N초가 지난 격자판 상태를 출력하라
==========================================
입력: R, C, N (1이상 200이하)
    초기 격자판 상태 (폭탄은 0, 평지는 .)
시간복잡도:
    BFS로 최대한 돌았을 때
    200*200*200 *4(4번씩 돈다고 가정하면?) = 8000000
    브루트포스 ㄱㄱ링
===========================================
초기상태 1초일때 아무것도 안하고 그 후 설치,폭파가 반복됨 1만 예외처리하기?
홀수 짝수일 때 나눠서 분기처리하기
그 전에 설치되어 있던 폭탄들 위치를 큐에 저장하기
짝수: (폭탄 설치)
    맵 빈 부분 좌표를 저장하고 큐에 넣기
    맵 상태 바꾸기
홀수: (폭파)
    큐에 들어가있던 폭탄들 좌표를 하나씩 받아서 .로 바꾸기
    BFS로 하나씩 처리하기
    범위 벗어나는 것만 처리
폭탄 좌표 저장 및 받는 로직:
    굳이 큐보다는 리스트에 저장하기?
    리스트에 리스트로 2차원 배열 저장 후 터트릴때는 마지막 -1 인덱스꺼 뽑아서 터트리기
============================
문제에 연쇄반응 없이 폭탄만 파괴된다 했는데 예제보니까 있는것같아서 그냥 큐에 넣고 한번에 폭파시켰습니다.

원래 생각했던 로직은 설치후 폭파 -> 그전에 설치되어있던 폭탄들 전부 폭파였는데
생각해보니 폭파를 하면 이전에 남아있던 폭탄 자리에 폭탄이 없어질 수 있기 때문에
폭파후 폭탄들의 위치를 다시 생각해야합니다.
그냥 폭파 후 남은 폭탄들을 그대로 다시 터트리면 로직완성인것같슴다.
연쇄반응은 위에 있는 경우를 말하는 것 같슴.

그럴려면 폭탄 심어놓는 행위와 폭탄이 폭파 후 남은 폭탄들을 큐에 집어넣는 행위를 분리해야했다.

 */
public class Main {

    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    static String[][] arr;
    static List<List<int[]>> queue = new ArrayList<>();

    static int R,C,N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new String[R][C];

        List<int[]> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                arr[i][j] = s.charAt(j)+""; //캐릭터를 스트링으로 바꾸는 더 좋은 방법이 머였더라
                if(arr[i][j].equals("O")) list.add(new int[]{i,j});
            }
        }
        queue.add(list);

        //N이 1이면 그대로 출력
        if (N==1){
            printAns();
        }
        else{
            //처음 1초 빼고 나머지 초 계산
            for(int i=2; i<=N; i++){
                //폭탄 설치 (짝수)
                if(i%2==0){
                    plant();
                }
                //폭파 (홀수)
                else{
                    bomb();
                }
            }
            printAns();
        }
    }

    private static void printAns() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void plant(){
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[i][j].equals(".")) {
                    arr[i][j] = "O";
                }
            }
        }
//        for(List<int[]> l: queue){
//            for(int[] a : l){
//                System.out.println(Arrays.toString(a));
//            }
//            System.out.println();
//        }
//        System.out.println("=======");
    }

    private static void bombChk(){
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[i][j].equals("O")) list.add(new int[]{i,j});
            }
        }
        queue.add(list);
//        System.out.println("=======");
    }

    private static void bomb(){
        List<int[]> list = queue.get(queue.size()-1); // 마지막에서 -1 리스트를 가져오기
//        System.out.println("qsize :" + queue.size() +" bomb =============");

        //빈 리스트일 경우 예외처리
        if(list.isEmpty()) return;

        for(int[] yx : list){
            int y = yx[0];
            int x = yx[1];

            arr[y][x] = ".";
            for(int i=0; i<4; i++){
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(rangeChk(ny, nx)){
                    arr[ny][nx] = ".";
                }
            }
        }
        bombChk();
    }

    private static boolean rangeChk(int i, int j){
        return i>=0 && i<R && j>=0 && j<C;
    }
}
