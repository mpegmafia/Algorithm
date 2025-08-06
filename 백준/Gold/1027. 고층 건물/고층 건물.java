import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
N개의 빌딩, 각각의 높이
고층 빌딩 A에서 B를 보려면 두 지붕을 잇는 선분이 다른 빌딩은 지나거나 접하지 않아야한다.
가장 많은 빌딩을 볼 수 있을 때의 빌딩 갯수는?
---------------
N (1<=N<=50)
빌딩의 높이
---------------
두 지붕을 잇는다는 개념이 정확히 뭔지 모르겠는데.. 예시를 보면 두개의 빌딩의 평균보다 그 가운데있는 건물들이 높아야한다는 뜻같다.
그리디인가?

알고보니 말그대로 "기울기"를 구해서 브루트포스 하는 것 이었다 ㅎ;
기울기 공식은 tangent니까 높이/밑변이다.

하나 더 생각해야할 것은 특정 지점에서 왼쪽으로 갈때와 오른쪽으로 갈때의 보이는 기준을 다르게 잡아야 한다는 것.
왼쪽으로 갈수록 기울기는 더 낮아야 보이고
오른쪽으로 갈수록 기울기는 더 커야 보인다.

for을 처음+1부터 N-2까지 다돈다. (맨 끝보다 그 한칸 앞뒤가 더 많이 볼 수 있다)
처음에 왼쪽의 기울기와 오른쪽 기울기의 max값을 미리 세팅해주고 cnt을 2로 시작한다.
그후 왼쪽으로 갈때는 높이차이/i-j로, 오른쪽으로 갈때는 높이차이/j-i로 해서
둘다 기울기가 더 낮은놈이 나올때마다 갱신하고 cnt+1을 해줌.
마지막에 ans값이랑 Math.max 연산으로 처리

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for(int i=0; i<N; i++){
            int cnt = 0;
            double Angle = Double.MAX_VALUE;
            for(int j=i-1; j>=0; j--){
                double tmp = (double)(arr[i]-arr[j]) / (i-j);
                if(tmp<Angle){
                    Angle = tmp;
                    //System.out.println(Angle+" j : "+j+" i : "+i);
                    cnt++;
                }
            }
            Angle = Double.MAX_VALUE;
            for(int j=i+1; j<N; j++){
                double tmp = (double)(arr[i]-arr[j]) / (j-i);
                if(tmp<Angle){
                    Angle = tmp;
                    //System.out.println(Angle+" j : "+j+" i : "+i);
                    cnt++;
                }
            }
            ans = Math.max(cnt, ans);
        }

        System.out.println(ans);



    }
}
