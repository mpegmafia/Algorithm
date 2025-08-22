import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
스카이라인이 보일 때 건물이 최소 몇 개 있을까

----------------------------
n (1<=n<=50,000)
n줄동안 좌표 x, y 가 주어짐 (1<=x<=1,000,000)  (0<=y<=500,000)
첫번째 지점의 x좌표는 항상 1
----------------------------
건물이 추가되는 상황은 무엇이 있을까
y축이 증가할때는 무조건 추가 (건물은 직사각형이므로)
y축이 하락할때는?
지나왔던 x축에 똑같은 높이의 숫자가 있다면 해당 빌딩의 스카이라인임
만약 없다면 새로운 빌딩 추가

y축이 상승할 때 상승 전 높이를 스택에 가지고 있음.
y축이 하락하기 시작하면 스택에서 하나씩 값을 뽑는다.
만약 스택에 있는 값보다 높다면 빌딩 하나 추가
스택에 있는 값이랑 같다면 아무것도 안함
스택에 있는 값보다 낮다면 스택이 빌때까지, 값이 같거나 낮아질때까지 뽑는다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2]; // x, y순서
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i=0; i<n; i++){
            int curH = arr[i][1];

            while(curH<stack.peek()){
                stack.pop();

            }
            if(curH>stack.peek()){
                ans++;
                stack.push(curH);
            }
        }
        System.out.println(ans);



    }
}
