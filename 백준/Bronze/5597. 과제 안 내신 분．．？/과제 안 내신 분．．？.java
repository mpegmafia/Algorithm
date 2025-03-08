import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visited = new boolean[31];
        for(int i=0; i<28; i++){
            int a = Integer.parseInt(br.readLine());
            visited[a] = true;
        }
        for(int i=1; i<=30; i++){
            if(!visited[i]) System.out.println(i);
        }
    }
}