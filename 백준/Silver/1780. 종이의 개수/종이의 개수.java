import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] ans = new int[3]; // 0, 1, -1 순서(인덱스 맞추기위해)
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int tmp = graph[0][0];
        int flag = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (graph[i][j] != tmp) flag=1;
            }
        }
        if (flag==0) {
            if(graph[0][0]==-1) ans[2]++;
            else ans[graph[0][0]]++;
            System.out.println(ans[2]);
            System.out.println(ans[0]);
            System.out.println(ans[1]);
            return;
        }

        DFS(n, 0, 0);
        System.out.println(ans[2]);
        System.out.println(ans[0]);
        System.out.println(ans[1]); //지금 문제는 첨부터 다 같은숫자일때 처리가 안된다는 것


    }
    public static void DFS(int n, int y, int x){
        //System.out.println("n : "+n+" y : "+y+" x: "+x);
        if (n==3) { //n이 3x3까지 넘어와서 다 다른수일때
            for(int i=y; i<y+3; i++){
                for(int j=x; j<x+3; j++){
                    if (graph[i][j]==-1) ans[2]++;
                    else ans[graph[i][j]]++;
                }
            }
        return;
        }



        for(int i=y; i<y+n; i+=n/3){
       A:    for(int j=x; j<x+n; j+=n/3){ //다같은거면 한번만 체크하면안됨..!!!!!

             int temp = graph[i][j];
             for(int r=i; r<n/3+i; r++){
                    for(int c=j; c<n/3+j; c++){
                        if(graph[r][c] != temp){
                            DFS(n/3, i, j);
                            continue A;
                        }

                    }
             }
            if(graph[i][j]==-1) ans[2]++;
            else ans[graph[i][j]]++;

            }
        }
        return;


    }
}