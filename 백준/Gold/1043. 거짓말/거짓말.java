import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
진실을 말하거나 과장되게 말하거나
그 자리에서 진실을 아는 사람이 있으면 말 못함
과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하자
========================
입력:
    사람의 수 N, 파티의 수 M (1<=N,M<=50)
    진실을 아는 사람의 수 A와 번호들 (1~N)
    M개의 줄에 각 파티마다 오는 사람의 수B와 번호 (1~N)
====================================
하나의 이야기를 진실 혹은 거짓으로 말해야 한다.
처음 진실을 아는 사람이 있으면 그 파티에는 진실을 말해야 한다.
그 후 진실을 들은 사람이 또 다른 파티에 존재하면 그 파티에도 진실을 말해야 한다.
50x50 인접리스트에 먼저 같은 파티에 가는 사람들 담아놓기
그 후 진실을 아는 사람과 같이 파티에 가는 사람 리스트를 큐에 넣고 계속 순회하면서
명단에 넣어놓기
그 후 파티를 다시 순회하며 해당 파티에 진실을 아는 사람이 있는지 없는지 전부 확인

진실명단을 큐에 담아놓고 boolean[] N개 만들어서 방문여부,진실여부 처리
List<List<int>> 만들고 처음에 N개를 초기화시켜줘야함
파티참석인원을 전부 int배열에 담아놓기
한 파티에 같이 참석한다면 그인원들 전부 양방향으로 전부 연결시켜줌(2중포문)
그 후 큐에서 하나씩 뽑아서 해당 리스트 순회하며 진실명단과 큐에 담아놓고
마지막으로 파티를 전부 순회하며 한명이라도 진실을 알면 컨티뉴, 다 모르면 거짓말 ㄱㄱ
 */
public class Main {
    static int N,M;
    static boolean[] truth;
    static int[][] parties;
    static List<List<Integer>> list = new ArrayList<>();
    static Queue<Integer> q = new ArrayDeque<>();
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        truth = new boolean[N+1];
        parties = new int[M][];
        for(int i=0; i<N+1; i++){
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        int truthNum = Integer.parseInt(st.nextToken());
        for(int i=0; i<truthNum; i++){
            int a = Integer.parseInt(st.nextToken());
            q.offer(a);
            truth[a] = true;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());
            parties[i] = new int[partyNum];
            for(int j=0; j<partyNum; j++){
                parties[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makePartyList();
        makeTruthList();
//        System.out.println(Arrays.toString(truth));
        solution();
        System.out.println(ans);

    }

    private static void makePartyList() {
        for(int i=0; i<M; i++){
            for(int j=0; j<parties[i].length; j++){
                for(int k=j+1; k<parties[i].length; k++){
                    list.get(parties[i][j]).add(parties[i][k]);
                    list.get(parties[i][k]).add(parties[i][j]);
                }
            }
        }
    }
    private static void makeTruthList() {
        while(!q.isEmpty()){
            int knowingOne = q.poll();
            for(int adjacentOne : list.get(knowingOne)){
                if(truth[adjacentOne]) continue;
                q.offer(adjacentOne);
                truth[adjacentOne] = true;
            }
        }
    }

    private static void solution() {
        OuterLoop:
        for(int i=0; i<M; i++){
            for(int j=0; j<parties[i].length; j++){
                if(truth[parties[i][j]]) continue OuterLoop;
            }
            ans++;
        }
    }

}