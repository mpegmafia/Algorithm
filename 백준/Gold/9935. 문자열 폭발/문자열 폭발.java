import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String b = br.readLine();
        int bLen = b.length();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append(c);

            // sb의 끝부분이 폭발 문자열과 같은지 확인
            if (sb.length() >= bLen && sb.substring(sb.length() - bLen).equals(b)) {
                sb.setLength(sb.length() - bLen); // 폭발 문자열 제거
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}