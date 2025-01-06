import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
각조에는 적어도 1명 있어야 하며
같은 조에 속한 원생들은 서로 인접해 있어야함
조별로 인원수가 같을 필요는 없음
티셔츠 비용은 조에서 가장큰 애 - 가장 작은 애
최대한 비용을 아껴서 K개 조의 티셔츠 비용을 계산하자
=============================
입력:
    N, K (1<=N<=300,000) (1<=K<=N)
    오름차순으로 정렬된 키 정보 (자연수, 10억을 넘지 않음)
=============================
N이 30만인거보니까 그리디 같았는데 조 K를 어떻게 맞추는지 모르겠음
브루트포스를 어떻게 돌려야하는지도 잘 모르겠음
기본적으로 조에 혼자 있으면 티셔츠 비용이 들지 않는다.
=============================
알고보니 그리디는 맞았는데 K개의 조를 어떤 관점으로 생각하느냐가 이문제 핵심
조를 묶을 때 키 차이가 가장 많이 나는 곳을 서로 다른 조로 묶어놔야 그 키차이가 비용에서 제외
이걸 이용해서 키차이가 나는 순서대로 조를 묶어야한다.
이때 조를 묶는 기준이 아닌 나누는 기준으로 생각해야하므로 K개의 조가 있다면 K-1번 나눠야 함
즉 키가 아닌 키 차이들을 저장해놓고 그 키차이에서 제일 높은 K-1를 제외한 나머지 키차이를 더해준다
나머지 키차이는 어차피 1 3 4 라고 가정했을때 4-1이나 3-1 + 4-3 이나 같기 때문에 상관x

 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;
        int[] diff = new int[N-1];
        st = new StringTokenizer(br.readLine());

        int pre = Integer.parseInt(st.nextToken());
        for(int i=0; i<N-1; i++) {
            int cur = Integer.parseInt(st.nextToken());
            diff[i] = cur - pre;
            pre = cur;
        }
        Arrays.sort(diff);

        for(int i=0; i<(N-1)-(K-1); i++)
            ans += diff[i];

        System.out.println(ans);


    }
}