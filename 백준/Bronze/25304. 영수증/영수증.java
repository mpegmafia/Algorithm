import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int X = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int tmp = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            tmp += price*cnt;
        }
        System.out.println(X==tmp? "Yes" : "No");
    }
}