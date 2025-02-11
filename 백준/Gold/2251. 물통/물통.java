import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
A,B,C중 C만 가득차있는 상태
물을 옮길때 한통이 비거나 가득찰때까지 물을 부을 수 있다.
A안에 물이 없을때 c안에 담길 수 있는 물의 양을 모두 구해내는 프로그램을 구하라
=======================
입력:
    A,B,C (1<=A,B,C,<=200)
=======================
memoization처럼 모든 경우의수 돌면서 방문했던 경우의수는 스킵하고
A==0일때 C값 리턴해서 오름차순으로 뱉기
 */
public class Main {
    static int[] limits;
    static boolean[][][] visited;
    static ArrayList<Integer> ans = new ArrayList<>();
    static int[] from = {0,0,1,1,2,2};
    static int[] to = {1,2,0,2,0,1,};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        limits = new int[3];
        for(int i=0; i<3; i++){
            limits[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[limits[0]+1][limits[1]+1][limits[2]+1];

        BFS();
        Collections.sort(ans);
        for(int a : ans){
            System.out.print(a+" ");
        }

    }
    public static void BFS(){
        Queue<int[]> q = new ArrayDeque<>();
        visited[0][0][limits[2]] = true;
        q.offer(new int[]{0, 0, limits[2]});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];
            if(a==0) ans.add(cur[2]);
                for(int i=0; i<6; i++){
                    int[] tmp = new int[]{a,b,c};
                    tmp[to[i]] += tmp[from[i]];
                    tmp[from[i]] = 0;
                    if(tmp[to[i]] > limits[to[i]]){
                        tmp[from[i]] = tmp[to[i]] - limits[to[i]];
                        tmp[to[i]] = limits[to[i]];
                    }
                    if(!visited[tmp[0]][tmp[1]][tmp[2]]){
                        visited[tmp[0]][tmp[1]][tmp[2]] = true;
                        q.offer(tmp);
                    }
                }
        }
    }
}