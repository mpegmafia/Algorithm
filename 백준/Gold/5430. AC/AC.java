import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
뒤집기 R, 버리기 D
배열에 있는 수의 순서 뒤집기, 첫번째 수 버리기(배열이 비어있을때 사용시 에러)
===================
입력:
    테케 T (최대 100)
    함수 p (1<=p<=100,000)
    배열에 들어있는 수의 갯수 n (0<=n<=100,000)
    배열 arr
    전체 테케에서 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
==================
브루트포스로 하나하나 차례대로 진행시 10만*10만*100 = 걍터짐
인덱스 하나를 잡아서 실행?
삭제 함수가 문제인데.. 배열에서 첫 문자를 10만번 삭제시 위에 브루트 포스랑 똑같아짐
삭제되는 인덱스를 저장해놓는다면?
뒤집거나 삭제되거나 둘 중 하나
차라리 투포인터로 해서 뒤집히면 왼쪽, 안뒤집히면 오른쪽을 삭제하면 되는것 아닌지?
그 후 삭제되는 인덱스 저장후 마지막 R값이 홀짝이냐에 따라 배열 뒤집거나 냅두기
왼쪽끝,오른쪽끝에 포인터 하나씩 놓고 R이 실행될때마다 flag변수 껐다켰다 해주기
D가 나오면 플래그변수에 따라 왼쪽++ 혹은 오른쪽--
삭제는 left와 right 사이 배열들로만 이루어지게끔 나머지를 삭제해주면됨
마지막 R값 홀짝체크 후 배열 뒤집거나 냅두기
에러체크 :
    left가 right보다 큰데 D명령어가 들어올 때

String으로 하면 두자리수 이상을 처리 못한다..
그렇다고 char[]로 하면 또 두자리수 이상을 처리 못함
그냥 String[]이나 int[]로 처리 ㄱㄱ
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        OuterLoop:
        for(int i=0; i<T; i++){
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] chars= br.readLine().replace("[", "").replace("]", "").split(",");
//            System.out.println(Arrays.toString(chars));
            int left = 0;
            int right = n-1;
            //true면 정방향, false면 뒤집힌 방향
            boolean flag = true;
            for(int j=0; j<p.length(); j++){
                if(p.charAt(j)=='R'){
                    flag = !flag;
                } else{
                    if(left>right) {
                        sb.append("error").append("\n");
                        continue OuterLoop;
                    }
                    if(flag){
                        left++;
                    } else{
                        right--;
                    }
                }
            }
            sb.append("[");
            if(flag){
                for(int k=left; k<=right; k++){
                    sb.append(chars[k]).append(",");
                }
            } else{
                for(int k=right; k>=left; k--){
                    sb.append(chars[k]).append(",");
                }

            }
            //만약 빈배열일 경우 [가 삭제된다,,,
            if(sb.charAt(sb.length()-1)==',') sb.deleteCharAt(sb.length()-1);
            sb.append("]").append("\n");
        }
        System.out.println(sb);


    }
}