import java.io.*; 
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int num = Integer.parseInt(br.readLine());
        int ans = 0;
        for(int i=0; i<N; i++){
            if(arr[i]==num) ans++;
        }
        System.out.println(ans);
    }
}