import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
첫째줄의 숫자와 둘째줄에 숫자가 일치하는 집합중 가장 긴 것
입력:
N (1<=N<=100)
N개의 줄동안 숫자
-----------------------
처음엔 아예 감이 안왔다 이게 그래프 탐색 문제라고?
정확히는 사이클을 찾는 문제였다. 1~N까지의 노드가 있고 거기에 각자 단방향 간선이 하나씩 있다고 생각하면 된다.

일단 기본적으로 배열에서 "모든 사이클"을 찾아야 하므로 전체 visited 배열을 관리해서 중복 탐색을 막아준다.
    4-5, 5-4 이렇게 있을 때 4에서 한번, 5에서 또 한번 탐색하면 곤란하므로.
기본적인 로직은 방문하지 않은 노드를 발견할 때마다 재귀로 타고 들어가서 방문 가능한 모든 노드를 탐색한다.
    visited가 true라면 skip한다.
이때 visited 배열과는 또 다른 이번 DFS에서 탐색한 노드들을 기록하는 배열을 준비한다음, 거기서 이미 방문했던 노드를 또 탐색한다면
그것이 바로 하나의 사이클을 발견하는 과정이다. 즉 사이클의 일부분을 (꼬리일지라도) 타고 들어가서 사이클을 찾는셈.

DFS 로직
    노드로 들어옴
    해당 노드 visited = true, path = true처리
        path는 DFS 빠져나오면서 다시 false 처리 해줘야함.
    다음 노드가 visited에서 방문하지 않았다면 방문.
    다음 노드가 이미 visited에서 방문했다면
        path에 다음 노드가 없으면 사이클 x
        path에 있으면 사이클 형성이라는 뜻
            다음노드가 사이클의 시작이므로 그 노드의 다음부터 다시 시작이 나올때까지의 노드들을 전부 리스트에 담아놓기.
 */
public class Main {

    static boolean[] visited, path;
    static int[] arr;

    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        path = new boolean[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++){
            if(!visited[i]) DFS(i);
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for(int num : list){
            sb.append(num).append('\n');
        }
        System.out.println(sb);

    }

    public static void DFS(int node){
        visited[node] = true;
        path[node]= true;
        int nextNode = arr[node];

        if(!visited[nextNode]){
            DFS(nextNode);
        }
        else{
            if(path[nextNode]){
                int startNode = nextNode;
                while(true){
                    list.add(startNode);
                    startNode = arr[startNode];
                    if(startNode == nextNode) break;
                }
            }
        }


        path[node] = false;
    }

}
