import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
-10억~-1 까지 알칼리
1~10억까지 산성
두개의 서로다른 용액을 합하여 0에 가까운 용액 만들기
같은 종류의 용액을 합해서 답이 나오는 경우도 있다.
============================================
입력:
    N (2<= N <= 100,000)
    N개의 정수가 오름차순으로 입력
============================================
오름차순 정렬이 핵심같다.
10만개니까 완탐은 못하고
투포인터로 0에 가장 근접하는 조합을 찾으면 될 것 같다.
만약 두 개 합이 0이면 바로 답을 출력.
오름차순이니 만약 두 개 더한값이 0보다 작으면 오른쪽 포인터를 -1하고
0보다 크면 왼쪽 포인터를 +1 하자
 */
public class Main {
    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = N-1;
        int[] ans = new int[2];
        int sum = Integer.MAX_VALUE;

        while(left<right){
            int temp = arr[left]+arr[right];
//            System.out.println("left:"+left+" right:"+right+" temp:"+temp);
            if(Math.abs(temp)< Math.abs(sum)){
                ans[0] = arr[left];
                ans[1] = arr[right];
                sum = temp;
                if(temp==0) break;

            }
            if (temp>0){
                right--;
            } else{
                left++;
            }
        }
        System.out.println(ans[0]+" "+ans[1]);




    }
}