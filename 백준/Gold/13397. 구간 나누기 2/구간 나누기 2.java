import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N개의 수, M개의 구간
    하나의 구간은 하나 이상의 연속된 수들로 이루어져 있다.
    배열의 각 수는 모두 하나의 구간에 포함되어 있어야 한다.
구간의 점수는 최댓값과 최솟값의 차이.
이 점수를 최소로 하는 구간으로 나누어 보자.
---------------------
N M (1<=N<=5,000) (1<=M<=N)
배열 (각 원소의 크기는 1이상, 10,000이하)
---------------------
최솟값을 구하는게 아닌 정해놓고 그 값이 되는지 확인해보자.
최소는 0이고 최대는 배열의 최댓값-최솟값으로 이분탐색.
중간값을 정한 후 그 값에 대해서 배열이 M개 이하로 나오는지 체크
이하로 나온다면 더 작은 값으로, 아니라면 더 큰값으로 옮기기

이분탐색 로직
    left <= right
    left = mid+1;
    right = mid-1
정답체크 로직
    위에 나온 mid값으로 M값이 구해는지 그리디하게 탐색
    한 구간에서 최소, 최대 값이 mid값 이하라면 계속 원소 담기
    아니라면 해당원소전까지만 담고 구간갯수 카운팅 +1한다음 다음원소부터 담기
    만약 구간 갯수가 M이 넘어가면 return false
    M이하라면 return true;

 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        int curMax = 0;
        int curMin = 10001;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            curMax = Math.max(curMax, arr[i]);
            curMin = Math.min(curMin, arr[i]);
        }
        int min = 0;
        int max = curMax-curMin;
        int mid = 0;
        int ans = max;
        while(min<=max){
            mid = (max+min)/2;
            if(check(arr, M, mid)){
                max = mid-1;
                ans = Math.min(ans, mid); //정답은 유효한것일때만 체크해줘야함.
            } else{
                min = mid+1;
            }

        }
        System.out.println(ans);
    }
    public static boolean check(int[] arr, int M, int mid){
        int cnt = 0;

        OuterLoop:
        for(int i=0; i<arr.length; i++){
            cnt++;
            int curMin = arr[i];
            int curMax = arr[i];
            for(int j=i+1; j<arr.length; j++){
                curMax = Math.max(curMax, arr[j]);
                curMin = Math.min(curMin, arr[j]);
                if(curMax-curMin>mid){
                    i = j-1; // i가++되므로 j-1로 세팅
                    break;
                }
                if(j==arr.length-1){
                    break OuterLoop;
                }
            }
            if(cnt>M) return false;
        }
        return cnt<=M;
    }
}
