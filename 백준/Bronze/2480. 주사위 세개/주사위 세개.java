import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int ans = 0;
        if(a==b&&a==c){
            ans += a*1000 + 10000;
        }
        else if (a==c){
            ans += a*100 + 1000;
        } else if (a==b){
            ans += a*100 + 1000;
        } else if (b==c){
            ans += b*100 + 1000;
        } else{
            ans = Math.max(Math.max(a,b),c)*100;
        }
        System.out.println(ans);
    }
}