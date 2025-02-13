import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
I A B 라면 A와 B는 같은 로봇의 부품이라는 뜻
서로 다른 로봇은 공통부품을 가지지 않는다.
로봇은 최소한 하나의 부품이 있다.
=========================
입력:
    호재의 지시 횟수 N (1<=N<=1,000,000)
    N줄동안 지시
    I a b = 라면 a와 b는 같은 로봇의 부품 (1<=a,b,<=1,000,000)
    Q c = robot(c)의 현재까지 알아낸 부품의 갯수(1<=c<=1,000,000)
=========================
유니온파인드인가..?
1 2 3 4 가 같은 부품이라고 쳤을때 robot(1)이랑 robot(2)가 똑같이 4여야함
그래프문제?
I a b 는 a와 b사이의 양방향 간선이라고 할 때.. Q를 물어볼때마다 해당 간선을 타고
BFS를 돌린다면 만약 50만개가 서로 연결되어 있다고 가정할때 50만번 물어보면
50만x50만 = 무조건터짐 -> I 들어올 때마다 배열로 부품갯수를 전부 업데이트 해줘야하나?
만약 유니온파인드라면 무조건 부모까지 타고들어감 부모에 연결되어있는 노드 갯수 저장해놓기
유니온 노드갯수 저장할 때 부모노드 +1 혹은 자식노드 +1 한것중 더 큰 갯수로 저장
 */
public class Main {
    static final int MAX = 10000001;
    static int[] parents = new int[MAX];
    static int[] cnt = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for(int i=1; i<MAX; i++){
            parents[i] = i;
            cnt[i] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            else {
                int c = Integer.parseInt(st.nextToken());
                sb.append(cnt[find(c)]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void union(int a, int b){
        int findA = find(a);
        int findB = find(b);
        if(findA != findB){
            parents[findB] = findA;
            cnt[findA] += cnt[findB];
        }
    }

    static int find(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}