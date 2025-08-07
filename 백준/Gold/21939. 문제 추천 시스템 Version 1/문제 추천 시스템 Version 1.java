import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
문제번호, 난이도로 정리해놓은 코테문제들
명령어는 총 3가지
recommend x
    x가 1인경우 가장 어려운 문제 (같으면 번호가 큰거), -1인 경우 가장 쉬운 문제(같으면 번호가 작은거)
add P L
    난이도가 L인 문제번호 P를 추천 문제 리스트에 추가한다.
    같은 문제번호가 다른 난이도로 들어올 수 있다.
solved P
    추천문제 리스트에서 문제번호 P를 제거한다. (없는 경우는 없다.)
-------------
N :문제의 개수 (1<=N<=100,000)
N줄동안 P L (1<=P<=100,000) , (1<=L<=100)
M :명령문의 개수 (1<=M<=10,000)
M줄동안 명령문
-------------
단순 구현문제인 것 같다.
다만 문제는 추천문제리스트를 어떻게 정렬하냐는 것.
단순 PQ로 구현하면 바로 터지지 않나..?
차라리 리스트로 담아놓고 한번에 정렬을 한다면?
그래도 최대 만번을 정렬할 수가 있다.
NlogN * 10000 = 10억이 걍 넘어감..
일단 해볼까
--------------
문제 클래스를 만들어서 Comparable 구현
먼저 리스트에 목록을 다 담아놓고 오름차순과 내림차순 pq 두개를 구현한다.
add
    같은 문제번호가 다른 난이도로 들어올 수 있다는게 핵심
    hashmap 구조를 사용하여 중복값이 있으면 난이도를 조정하자
recommend
    -1이면 오름차순에서 poll(), 1이면 내림차순에서 poll()
    poll()하기전에 먼저 해시맵에서 해당 문제번호의 난이도가 이게 맞는지확인 (또한 없는지도 확인)
    맞다면 위 로직 그대로 poll
    만약 존재하는데 난이도가 다르다면 그걸 poll한 후 다시 새 객체를 offer한후 다시 poll
    만약 없다면 아예 다시 poll
    poll한걸 StringBuilder에 담아놓기.
solved P
    hashmap에서 제거
 */

class Problem implements Comparable<Problem>{
    int P, L;
    Problem(int P, int L) {
        this.P = P;
        this.L = L;
    }
    @Override
    public int compareTo(Problem o) {
        if(this.L != o.L) return Integer.compare(this.L, o.L);
        else return this.P - o.P;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "P=" + P +
                ", L=" + L +
                '}';
    }
}


public class Main {

    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Problem> asc;
    static PriorityQueue<Problem> desc;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Problem> problems = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            problems.add(new Problem(P, L));
            map.put(P, L);
        }
        asc = new PriorityQueue<>(problems);
        desc = new PriorityQueue<>(Collections.reverseOrder());
        desc.addAll(problems);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("add")){
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                map.put(P, L);
                asc.offer(new Problem(P, L));
                desc.offer(new Problem(P, L));
            } else if(order.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                recommend(x);
            } else{
                map.remove(Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(sb);
    }

    public static void recommend(int x){
        PriorityQueue<Problem> pq = null;
        if(x==-1) pq=asc;
        if(x==1) pq=desc;

        while(!pq.isEmpty()){
            Problem p = pq.peek();
            if(map.containsKey(p.P)) break;
            else pq.poll();
        }

        while(!pq.isEmpty()){
            Problem p = pq.peek();
            if(map.get(p.P) == p.L) {
                sb.append(p.P).append("\n");
                break;
            } else{
                pq.poll();
                pq.offer(new Problem(p.P, map.get(p.P)));
            }
        }

    }
}
