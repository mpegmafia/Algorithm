import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
3x3인 배열, 인덱스는 1부터 시작
1초가 지날때마다 배열에 연산이 적용
R 연산 = R>=C 일때 적용, 배열의 모든 행에 대해서 정렬 수행
C 연산 = R<C 일때 적용,  배열의 모든 열에 대해서 정렬 수행

정렬이란 각각의 수가 몇번 나와있는지 순서로 정렬하는 것.
만약 똑같은 횟수로 나온 숫자가 있다면, 숫자 자체의 오름차순으로 정렬
또한 정렬후에는 수와 등장 횟수를 모두 넣어야함. 순서는 수가 먼저.
[3,1,1] -> [3,1,1,2]  (3은 1번, 1은 2번)

이렇게 하면 배열의 크기가 달라짐.
이때 다른 행이나 열은 가장 큰 행을 기준으로 크기가 변하고 커진 곳엔 0 이 채워짐.
다만 수를 정렬할 때에는 0을 무시해야함, [3,2,0,0] => [3,2]임

이때 행 또는 열 크기가 100이 넘어가면 처음 100개를 넘어간 나머지를 버림.
배열A 와 r,c,k,가 주어질때 A[r][c]값이 k가 되기 위한 최소 시간을 구해보자.
만약 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.
=======================================
r,c,k (1<=r,c,k<=100)
3개의 줄에 배열 A에 들어있는 수.
=======================================
완전 탐색 기준으로 생각.
처음 배열은 3,3으로 가서 최대 100,100이 됨.
100x100 = 10,000일 때 모든 열을 스캔하면서 pq에 삽입하고 뺌 logN(N은 최대 100)이니 8정도
또한 그걸 최대 100초동안 하니 백만*8  = 8백만 = 여기서 다시 순회하며 값넣는거까지 x2해도 충분

A를 그냥 int[100][100]으로 선언해버리고 처음부터 차곡차곡 넣자.
또한 숫자를 셀 때 자료구조형이 고민인데
pq값으로 한다음 int[2] 를 하면 나중에 횟수를 추가하지 못함.
어차피 숫자는 최대 100이니까 int[100]으로 선언후 횟수 카운트
그리고 발견된 숫자는 queue에 넣기 (처음 발견시에만) => 정렬을 어떻게?
hashmap인가..? hashmap 초기화한 후 넣어놓고 Node 클래스로 치환하여 pq에넣기?
    이때 숫자 0을 만나면 바로 break
그후 해당 배열 초기화 후 pq에서 하나씩 빼서 차곡차곡 넣어주기.
그후 maxR값이나 maxC값 갱신.


 */
public class Main {
    static int r,c,k;
    static int[][] graph;
    static int maxR, maxC;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        graph = new int[100][100];
        maxR = 3;
        maxC = 3;
        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = -1;

        for(int i=0; i<=100; i++){
            if(graph[r][c]==k){
                ans = i;
                break;
            }
            if(maxR>=maxC) sortR();
            else sortC();

            //debugging();

        }


        System.out.println(ans);
    }

    private static void debugging() {
        System.out.println("maxR = " + maxR+" maxC = " + maxC);
        for(int i=0; i<100; i++){
            System.out.println(Arrays.toString(graph[i]));
        }
    }

    public static void sortR(){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int tmp = 0;
        for(int i=0; i<maxR; i++){
            hashMap.clear();
            pq.clear();
            for(int j=0; j<100; j++){
                if(graph[i][j]==0) continue;
                hashMap.put(graph[i][j], hashMap.getOrDefault(graph[i][j],0)+1);
            }
            for(Integer num : hashMap.keySet()){
                Node n = new Node(num, hashMap.get(num));
                pq.offer(n);
            }
            tmp = Math.max(tmp, pq.size()*2);
            Arrays.fill(graph[i], 0);
            for(int j=0; j<100; j+=2){
                if(pq.isEmpty()) break;
                Node n = pq.poll();
                graph[i][j] = n.num;
                graph[i][j+1] = n.count;
            }
        }
        maxC = Math.min(tmp,100);

    }
    public static void sortC(){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int tmp = 0;
        for(int j=0; j<maxC; j++){
            hashMap.clear();
            pq.clear();
            for(int i=0; i<100; i++){
                if(graph[i][j]==0) continue;
                hashMap.put(graph[i][j], hashMap.getOrDefault(graph[i][j],0)+1);
            }
            for(Integer num : hashMap.keySet()){
                Node n = new Node(num, hashMap.get(num));
                pq.offer(n);
            }
            tmp = Math.max(tmp, pq.size()*2);
            for(int i=0; i<100; i++){
                graph[i][j] = 0;
            }
            for(int i=0; i<100; i+=2){
                if(pq.isEmpty()) break;
                Node n = pq.poll();
                graph[i][j] = n.num;
                graph[i+1][j] = n.count;
            }
        }
        maxR = Math.min(tmp,100);
    }

    public static class Node implements Comparable<Node>{
        int num;
        int count;
        public Node(int num, int count){
            this.num = num;
            this.count = count;
        }

        public int compareTo(Node o){
            return this.count!=o.count?
                    Integer.compare(this.count, o.count) :
                    Integer.compare(this.num, o.num);
        }
    }
}
