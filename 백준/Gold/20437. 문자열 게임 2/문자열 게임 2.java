import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
소문자로 이루어진 문자열 W
양의 정수 K
어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이 구하기
어떤 문자를 정확히 K개를 포함하고 문자열 첫번째, 마지막 글자가
    해당문자로 같으면서 가장 긴 문자열 길이
==================
입력:
    T (1<=T<=100)
    W 문자열 (1<=K<=W<=10,000)
    K 갯수
==================
K==1이면 답은 1,1 예외처리
K개 포함하는 가장 짧은 문자열
투포인터? 브루트포스?
문자가 K개이상 겹칠때까지 오른쪽 포인터를 옮김
만약 겹치는것이 나오면 왼쪽포인터를 계속 옮겨서 최솟값 나올때까지 옮겨줌
만약 오른쪽포인터가 끝까지 갔을 때 답이 갱신된적이 없는 경우 -1
----
문자열 첫번째, 마지막 글자가 해당문자로 같으면서 가장 긴 문자열의 길이
브루트포스
문자열의 모든 알파벳 갯수를 구함
K개가 넘는 알파벳만 2중포문으로 돌림
해당 값중 가장 큰 값 리턴
 */
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test=0; test<T; test++){
            char[] chars = br.readLine().toCharArray();
            int[] cnt = new int[26];
            for(int i=0; i<chars.length; i++){
                cnt[chars[i]-'a'] += 1;
            }
            int K = Integer.parseInt(br.readLine());
            boolean flag = true;
            for(int i=0; i<26; i++){
                if(cnt[i]>=K) flag = false;
            }
            if(flag) {
                sb.append("-1").append("\n");
                continue;
            }
            int min = Integer.MAX_VALUE;
            int max = 0;
            for(int i=0; i<26; i++){
                if(cnt[i]>=K){
                    for(int j=0; j<chars.length; j++){
                        if(chars[j]-'a' == i){
                            int tmp = 0;
                            for(int k=j; k<chars.length; k++){
                                if(chars[k]-'a'==i){
                                    tmp++;
                                    if(tmp ==K){
                                        min = Math.min(min, k-j+1);
                                        max = Math.max(max, k-j+1);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sb.append(min).append(" ").append(max).append("\n");



        }
        System.out.println(sb);
    }
}