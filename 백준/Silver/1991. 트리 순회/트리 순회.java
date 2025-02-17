import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
전위 순회 = 루트 -> 왼자식 -> 오른자식
중위 순회 = 왼자식 -> 루트 -> 오른자식
후위 순회 = 왼자식 -> 오른자식 -> 루트
DFS로 위 순서대로 탐색하면됨
SB를 이용해 문자열 쌓아놓기
ex) A에서부터 들어가 SB 쌓고 왼 -> 오 탐색
A에서부터 들어가 왼 들어가고 SB로 현재(루트) 쌓기 그후 오른 탐색
A에서부터 들어가 왼들어가고 오른 들어가고 현재 SB 쌓기(루트)
============================
입력:
    N : 이진 트리의 노드 개수 (1<=N<=26)
    N줄동안 루트노드, 왼자식, 오른자식 (없다면 .)
============================
엣지를 어떻게 구성할 것이냐,

전위 순회는 루트부터 큐에서 하나씩 담기
 */
public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Character>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N+1];
        for(int i=0; i<N; i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            edges[root-'A'].add(a);
            edges[root-'A'].add(b);
        }

        preOrder('A');
        sb.append("\n");
        inOrder('A');
        sb.append("\n");
        postOrder('A');
        sb.append("\n");
        System.out.println(sb);

    }

    private static void postOrder(char root) {
        if(edges[root-'A'].get(0) !='.') postOrder(edges[root-'A'].get(0));
        if(edges[root-'A'].get(1) !='.') postOrder(edges[root-'A'].get(1));
        sb.append(root);
    }

    private static void inOrder(char root) {
        if(edges[root-'A'].get(0) !='.') inOrder(edges[root-'A'].get(0));
        sb.append(root);
        if(edges[root-'A'].get(1) !='.') inOrder(edges[root-'A'].get(1));
    }

    private static void preOrder(char root) {
        sb.append(root);
        if(edges[root-'A'].get(0) !='.') preOrder(edges[root-'A'].get(0));
        if(edges[root-'A'].get(1) !='.') preOrder(edges[root-'A'].get(1));

    }
}