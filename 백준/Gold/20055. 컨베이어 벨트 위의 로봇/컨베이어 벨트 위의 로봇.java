import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1부터 2N까지 컨베이어 벨트가 있고 각각 칸이 나뉘어져 있다.
1번은 올리는 위치 N번은 내리는 위치이다.
로봇은 내리는 위치에 도달하면 그 즉시 내린다.
로봇을 올리는 위치에 올리거나 로봇이 앞칸으로 이동하면 해당 칸의 내구도가 1감소한다,
작동 순서:
    1. 벨트가 각 칸위에 있는 로봇과 함께 한칸 회전 (앞으로 전진)
    2. 벨트위에 올라간 순서대로 로봇들이 앞으로 전진할 수 있다면 전진
        2-1.해당 칸에 로봇이 없거나 내구도가 1 이상이어야 함
    3. 올리는 위치에 있는 칸의 내구도가 0 이 아니면 로봇을 올린다.
    4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 아니면 1번으로 돌아간다.

종료되었을 때 몇번째 단계인지 구하라 (1번 단계부터 시작)
===============================
입력:
    첫째 줄에 N,K (1<=N<=100) (1<=K<=2N)
    2N개의 컨베이어 내구도 목록
================================
단순 구현 문제
배열을 회전하는 로직이 중요할 것 같다. 해당 함수가 머였지
 */
public class Main {

    static int N;
    static int K;
    static int[] belts;
    static boolean[] robots;
    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        robots = new boolean[N];
        belts = new int[2*N];
        int ans = 1;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++){
            belts[i] = Integer.parseInt(st.nextToken());
        }

        while(true){
            rotate();
            forwardRobot();
            setRobot();
            if(checkAnswer()) break;
            ans++;
        }
        System.out.println(ans);

    }

    //돌릴 때 로봇 위치도 같이 돌려야함;;
    public static void rotate(){
//        System.out.println("rotate =================== ");
//        System.out.println(Arrays.toString(belts));
        int temp = belts[2*N-1];
        for(int i=2*N-1; i>0; i--){
            belts[i] = belts[i-1];
        }
        belts[0] = temp;
        for(int i=N-1; i>0; i--){
            robots[i] = robots[i-1];
            if(i==N-1) robots[i] = false;
        }
        robots[0] = false;

//        System.out.println(Arrays.toString(belts));
    }

    public static void forwardRobot(){
        //로봇은 전진할때 배치 순서니까 그냥 역순으로 차례대로 땡기기, 내구도 검사도 해줘야함
//        System.out.println("forward robot ================= ");
//        System.out.println(Arrays.toString(belts));
//        System.out.println(Arrays.toString(robots));
        for(int i=N-2; i>=0; i--){
            //현재칸에 로봇이 없다면 패스
            if (robots[i] == false) continue;
            //앞칸 내구도 0이면 패스
            if(!checkEndurance(i+1)) continue;

            //앞칸에 로봇이 있으면 패스
            if (robots[i+1] == true) continue;

            belts[i+1] -= 1;

            //마지막칸이라면 회수, 아니라면 true로 위치 고정
            robots[i+1] = i+1==N-1? false : true;
            robots[i] = false;
        }
//        System.out.println(Arrays.toString(belts));
//        System.out.println(Arrays.toString(robots));
    }

    public static void setRobot(){
//        System.out.println("set robot ================= ");
//        System.out.println(Arrays.toString(belts));
//        System.out.println(Arrays.toString(robots));

        if(checkEndurance(0) && !robots[0]) {
            robots[0] = true;
            belts[0] -= 1;
        }
//        System.out.println(Arrays.toString(belts));
//        System.out.println(Arrays.toString(robots));
    }

    public static boolean checkEndurance(int idx){
        return belts[idx] >=1 ; //내구도 1이상이면 true 리턴
    }

    public static boolean checkAnswer(){
        int count = 0;
        for(int i=0; i<2*N; i++){
            if(belts[i] == 0) count++;
        }

        return count>=K;
    }

}