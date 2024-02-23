import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//턴제게임
/*
 * N * M 격자판
 * N+1행에는 성이 있다.
 * 
 * 궁수 3명을 배치해야한다. 
 * 궁수는 성에 있는 칸에만 배치할 수 있고 한 칸당 궁수 하나만 있을 수 있다.
 * 
 * ■ 궁수는 거리가 D 이하인 적을 공격하고 적이 여럿이면 가장 왼쪽에 있는 적을 공격한다.
 * 같은 적이 여러 궁수에게 공격당할 수 있다.(이 상황이 최대한 발생하면 안된다.)
 * 
 * 궁수 배치를 조합으로 짜야한다. 
    
    시간복잡도: 배열 크기가 최대 15X15
    상황에 따라 백트래킹도 활용할 수 있을듯
 */

class CArchor
{
    static int shotRange;
    int posX;
    int posY;
    static Stack<int[]> targetList = new Stack<>();
    static final int dir[][] = {{0, -1},{-1,0},{0,1}};
    
    public CArchor(int range)
    {
        shotRange = range;
        this.posX = 0;
        this.posY = 0;
    }
    
    void SetPos(int x, int y)
    {
        posX = x;
        posY = y;
    }
    
    void FindEnemy(int arr[][])
    {        
        //유효한 사거리만큼 탐색을 수행한다.
        //★ 궁수는 [가장 가까운 적] 부터 먼저 공격한다.
        //★ 궁수는 [가장 왼쪽에 있는 적] 부터 공격한다. + 같은 적이 노려질 수 있다.
        int N = arr.length;
        int M = arr[0].length;
        
        boolean isVisited[][] = new boolean[N][M];        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {posX-1, posY, 1});//깊이
        isVisited[posX-1][posY] = true;
        
        while(!q.isEmpty())
        {
            int curPos[] = q.poll();
            
            if(arr[curPos[0]][curPos[1]] ==1)
            {
                targetList.add(new int[] {curPos[0], curPos[1]});
                break;
                //return new int[]{curPos[0], curPos[1]};        
            }
                
            
            for(int curDir[] : dir)
            {
                int x = curPos[0]+curDir[0];
                int y = curPos[1]+curDir[1];
                
                if(x < 0 || x >= N || y < 0 || y >= M)
                    continue;
                
                if(isVisited[x][y])
                    continue;
                
                if(curPos[2]+1 > shotRange)
                    continue;        
                
                q.add(new int[] {x, y, curPos[2]+1});
                isVisited[x][y] = true;    
            }
        }
    }
    
    int[] ShotEnemy()
    {
        if(!targetList.isEmpty())
            return targetList.pop(); 
        targetList.clear();
        return null;
    }
    
}

public class Main {
    
    static int N, M;
    static int answer = 0;
    static int arr[][];
    static CArchor archors[];
    static int input[] = new int[3]; //궁수의 가로 축 좌표
    
    //배열을 한 칸씩 밑으로 이동한다.
    static void NextWave(int arr[][])
    {    
        for(int i = N-1; i>0; i--)
            arr[i] = arr[i-1];
        
        arr[0] = new int[M];
    }
    
    static void comb (int dep, int cnt)
    {
        if(dep == 3)
        {
            //System.out.println(Arrays.toString(input));
            // 게임을 진행한다.
            GameStart();
            return;
        }
        
        for(int i = cnt; i<M; i++)
        {
            input[dep] = i; 
            comb(dep+1, i+1);        
        }
    }
    static void GameStart()
    {
        // 조합한 타일에 궁수를 배치한다.
        for(int i = 0; i<3; i++)
            archors[i].SetPos(N, input[i]);
        
//        boolean check = false;
//        if(input[0] == 1 && input[1] == 2 && input[2] == 4)
//        {
//            check = true;
//            System.out.println("디버그");
//        }
            
        //딥카피
        int copyArr[][] = new int[N][M];
        for(int i = 0; i<N; i++)
            for(int j = 0; j<M; j++)
                copyArr[i][j] = arr[i][j];
        
        int killEnemyCnt = 0;
        // N 턴이 진행된동안 잡은 enemy를 등록한다.
        for(int i = 0; i<N; i++)
        {
//        	for(int k=0; k<N; k++) {
//        		System.out.println(Arrays.toString(copyArr[k]));
//        	}
            for(int j = 0; j<3; j++)
            {
                archors[j].FindEnemy(copyArr); //사격한 적을 선택한다. 
            }
            for(int j = 0; j<3; j++)
            {
                int enemyPos[] = archors[j].ShotEnemy();
                if(enemyPos != null)
                {
//                    if(check)
//                        System.out.println(Arrays.toString(input)+"위치일때 킬: "+Arrays.toString(enemyPos));
                    
                    if (copyArr[enemyPos[0]][enemyPos[1]] == 1) {
                        //사격할 적을 지운다.
                        copyArr[enemyPos[0]][enemyPos[1]] = 0;
                        killEnemyCnt++;
                    }
                    
                }
            }
            
                
//            System.out.println(killEnemyCnt);
            //적이 밑으로 1칸 내려온다.
            NextWave(copyArr);
        }
        
//        if(killEnemyCnt == 15)
//            System.out.println("궁수가 이 좌표일 때 15가 나옴: "+Arrays.toString(input));
        
        answer = Math.max(killEnemyCnt, answer);
    }
    
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());        
        //
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); //사거리 1~10
        //이 사거리를 통해 두좌표 사이의 거리가 D이내인 적을 판별한다.
        arr = new int[N][M];
        
        for(int i = 0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        //궁수 3명을 생성한다.
        archors = new CArchor[3];
        for(int i=0; i<3; i++)
            archors[i] = new CArchor(D);
                
        //M개의 수 중에서 3개를 뽑는 조합식을 작성한다. (MC3)
        //기저조건에서 디펜스 게임을 시작한다.
        comb(0, 0);
        System.out.println(answer);        
    }
}
/*
반례

5 5 2
1 0 1 1 1
0 1 1 1 1
1 0 1 0 1
1 1 0 1 0
1 0 1 0 1

answer : 14

*/