import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
종이를 x=f에 맞춰서 접는다 (왼쪽이 위에 올라옴)
종이를 가로로 c+1개의 크기가 동일한 구간으로 나눈다, 그다음 위에서부터 차례대로 접는다
왼쪽아래가 x1,y1 오른쪽위가 x2,y2인 직사각형을 찾아서 페인트칠한다
이때 종이가 겹친곳은 모두 칠해진다,
종이를 핀 후 색칠되어있지 않은 면적을 구하라
======================================
입력:
    W,H,f,c,x1,y1,x2,y2
    (1<=W,H<=10억)
    (0<=f<=W)
    (0<=c<=1000)
====================================
W,H가 각각 10억인걸보니 그래프탐색으론 절대 못풀듯
페인트의 패턴을 찾아야 하는 것 같다. 그 후 WxH에서 페인트칠한걸 빼는건가
10억x10억이니까 long으로 계산해야하는거 유의
역순으로 생각해보자
먼저 세로로 c+1개의 크기로 나눴다는 것은 만들어진 직사각형에서 c+1개를 곱하면 됨
그렇다면 가로로 접는 것은
일단 x=f일때 겹치는 범위 구하는 법은
색칠 시작범위가 f안에 있다면 끝범위가 f를 벗어나는지 안벗어나는지 확인,
    벗어나면 f-시작범위, 안벗어나면 끝범위-시작범위에 * y크기 * c-1 해주면됨
다만이건 f가 W의 절반을 넘지 않았을때고 절반을 넘었다면
W-f범위가 겹치는 범위가 된다.

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W = Integer.parseInt(st.nextToken());
        long H = Integer.parseInt(st.nextToken());
        long f = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long x1 = Integer.parseInt(st.nextToken());
        long y1 = Integer.parseInt(st.nextToken());
        long x2 = Integer.parseInt(st.nextToken());
        long y2 = Integer.parseInt(st.nextToken());

        long ans = W*H;
        ans -= (x2 - x1) * (y2 - y1) * (c+1);
        long tmp;
        if(f<=W/2) tmp = f;
        else       tmp = W-f;

        if(x1<tmp){
            long range;
            if(x2<=tmp){
                range = (x2-x1)*(y2-y1);
            } else{
                range = (tmp-x1)*(y2-y1);
            }
            ans -= range*(c+1);
        }
        System.out.println(ans);

    }
}