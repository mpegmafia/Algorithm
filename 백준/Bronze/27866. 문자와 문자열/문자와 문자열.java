import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int idx = Integer.parseInt(br.readLine());
        System.out.println(s.charAt(idx-1));
    }
}