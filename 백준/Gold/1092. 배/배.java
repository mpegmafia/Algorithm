import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
크레인 N대가 1분에 하나씩 박스를 동시에 배에 실는다.
각 크레인은 무게 제한이 있다. 이 무게 제한보다 무거운 박스는 움직일 수 없다.
모든 박스를 배로 옮기는데 드는 시간의 최솟값 구하기 (못 옮기면 -1 출력)
===========================
입력:
    N (1<=N<=50)
    각 크레인의 무게 제한 (백만 이하)
    M (1<=M<=10,000)
    각 박스의 무게
============================
먼저 박스와 크레인을 내림차순으로 정렬
첫 크레인이 첫 박스를 못옮기면 -1 출력
아니라면 다 옮길때까지 이중포문으로 하나씩 옮기기..?
============================
문제점은 크레인을 기준으로 이중포문을 돌은것.
만약 크레인이 10000, 1, 1 이런식으로 되어있고 박스가 10000 이 만개있다고 가정하면
크레인은 처음 박스 하나를 집고 나머지 49개의 크레인이 10000개의 루프를 똑같이 돌아야한다.
그렇다면 이 행위를 총 만번 집어서 반복해야하니까 50*10000*10000이 되어버림
O(N*M*M)

그래서 크레인 관점이 아닌 박스 관점으로 박스를 순회하며 가장 강한 크레인이
현재 박스를 들 수 있느냐 없느냐 확인하면 박스를 한번만 순회해도 된다.
즉 O(N*M)이 됨.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Integer[] cranes = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        Integer[] boxes = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(boxes, Collections.reverseOrder());
//        System.out.println(Arrays.toString(cranes));
//        System.out.println(Arrays.toString(boxes));
        int cnt = 0;
        int ans = 0;

        if(cranes[0] < boxes[0]){
            System.out.println(-1);
            return;
        }

        while(cnt<M){
            int idx = 0;
            for(int i=0; i<M; i++){
                if(boxes[i]==0) continue;
                if(idx==N) break;
                if(cranes[idx]>=boxes[i]){
                    idx++;
                    cnt++;
                    boxes[i] = 0;
                }
            }
            ans++;
        }
        System.out.println(ans);

    }
}