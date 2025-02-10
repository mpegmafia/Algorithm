import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
문자열에 폭발문자열이 있으면 모든 폭발문자열이 동시에 폭파, 남은 문자열이 합쳐짐
남은 문자열에서도 폭발문자열이 있을 수 있고 안남을때까지 계속 폭발 반복
==============================
입력:
    문자열 S (1<=S<=1,000,000)
    폭발문자열 B (1<B<=36)
=============================
StringBuilder를 이용해 그냥 완탐?
문자 S중 첫문자가 B랑 같은문자가있으면 2중포문으로 탐색
만약 끝까지 똑같을 경우 B의 길이 -1 만큼 인덱스 스킵(for문에서 ++해주니까)
만약 같지 않을 경우 스트링빌더에 넣어놓기
그 후 이 과정을 폭발문자열이 사라질 때까지 반복
==========================
변수 s에 계속 새로운 스트링을 할당해서 메모리 초과가 났다.
스택자료구조를 직접적으로 사용하거나 StringBuilder를 스택처럼 활용해서 문제를 풀어야함
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String b = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(char c: s.toCharArray()){
            sb.append(c);

            if(sb.length()>=b.length() && sb.substring(sb.length()-b.length(), sb.length()).equals(b))
                sb.setLength(sb.length()-b.length());
        }


        System.out.println(sb.length()==0?"FRULA" : sb.toString());
    }
}