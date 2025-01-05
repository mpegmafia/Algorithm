import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
    하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫글자가 단축키로 지정되어 있지
않으면 그 알파벳을 단축키로 지정한다
    만약 모든 단어의 첫글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 "알파벳"을
보면서 단축키로 지정안된 것을 단축키로 지정한다.
    어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.
위의 규칙을 첫번째 옵션부터 N번째 옵션까지 차례대로 적용한다.
======================
입력:
    옵션 개수 N (1<=N<=30)
    N줄 동안 5개 이하의 단어(10문자 이하)로 이루어진 옵션들이 나온다.
출력:
    단축키로 지정된 것에 [] 괄호 붙여서 출력
======================
시간복잡도는 신경쓰지 않아도 될 것 같은 문제
하나의 문장 => 여러개의 단어 => 여러개의 알파벳들 순서로 있고
    그 순서대로 탐색하는 로직 필요: List<String[]> 형식으로 담고 순차 탐색
        문장에서 단어 첫문자가 단축키로 지정되어있는지 확인하는 로직필요
        :해쉬맵에 지정되어있는 단축키들을 저장해놓자. 혹은 그냥 알파벳이니까 배열?
        없을시 단축키 지정이 된 걸 저장하는 로직 필요
        :마찬가지로 해쉬맵에 저장
        있을시 하위 순서로 넘어가서 다시 탐색하는 로직 필요
        :만약 String 배열의 끝까지 다 돌았다면 String 자체를 탐색
    이때 대소문자 구분은 없어야함 (다 소문자로 저장하기?)
    :lowercase 어쩌구 함수 ㄱㄱ
    마지막으로 단축키가 지정됐다면 해당 문자열 앞 뒤에 꺽쇠로 감싸주는 로직 필요
    :StringBuilder로 답을 저장해놓을 때 해당 스트링의 인덱스의 앞 뒤에 [ ]를 넣자
=

*/
public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[] isUsed = new boolean[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<String[]> phrase = new ArrayList<>();

        for(int i=0; i<N; i++){
            //String[] 으로 바로 만들어주는 String 내장함수 사용하기
            String[] str = br.readLine().split(" ");
            phrase.add(str);
        }

        //Label 붙여서 상위단계 continue

        for(String[] array : phrase){

            //단어 앞글자 단축키가 다 있을시
            if(wordChk(array)) {
                //알파벳 단축키가 다 있을시
                if(alphabetChk(array)){
                    //괄호를 달면 안되니까 인덱스에 -1 줘서 if문에 안걸리게 하기
                    makeAnswer(array, -1, -1);
                }
            }
        }
        System.out.println(sb);

    }

    //단어 단축키 확인후 없으면 false 리턴후 단축키 등록 있다면 true 리턴
    public static boolean wordChk(String[] arr){

        for(int i=0; i<arr.length; i++){
            String str = arr[i];
            char c = Character.toLowerCase(str.charAt(0));
            if(isUsed[c-'a'] == false){
                isUsed[c-'a'] = true;
                makeAnswer(arr, i, 0);
                return false;
            }
        }
        return true;
    }

    //알파벳 단축키 확인후 없으면 false 리턴, 있으면 단축키 등록 후 true 리턴
    public static boolean alphabetChk(String[] arr){

        for(int i=0; i<arr.length; i++){
            String str = arr[i];
            for(int j=1; j<str.length(); j++){
                char c = Character.toLowerCase(str.charAt(j));
                if(isUsed[c-'a']== false){
                    isUsed[c-'a'] = true;
                    makeAnswer(arr,i,j);
                    return false;
                }
            }
        }
        return true;
    }

    //정답 문자열 만들기
    public static void makeAnswer(String[] arr, int arrIdx, int alpIdx){

        for(int i=0; i<arr.length; i++){
            String str = arr[i];

            //괄호 달아야 하는 문자열일때
            if(i==arrIdx){
                for(int j=0; j<str.length(); j++){
                    //해당 알파벳 인덱스에 괄호 달아야 할때
                    if(j==alpIdx){
                        sb.append("[").append(str.charAt(j)).append("]");
                    }
                    else{
                        sb.append(str.charAt(j));
                    }
                }
                sb.append(" ");
            }
            else{
                sb.append(str).append(" ");
            }
        }
        sb.append("\n");
    }
}