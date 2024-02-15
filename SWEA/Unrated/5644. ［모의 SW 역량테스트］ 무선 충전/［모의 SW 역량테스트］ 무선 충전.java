import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 10x10의 그래프
	 * 사용자 2명은 M의 시간동안 움직임
	 * 사용자 A는 0,0 B는 9,9가 고정시작위치
	 * 두 지점간의 거리는 유클리드 거리
	 * 만약 충전범위 두곳 안에 모두 속하면 하나를 고를 수 있음
	 * 만약 한 충전기에 2명이 동시접속하면 균등하게 분배됨
	 * 충전된 양의 합의 최댓값을 구해야하는 것이 문제
	 * 사용자는 초기위치부터 충전이 가능하고 동시에 같은 위치로 이동할 수 있음
	 * 
	 * 입력:
	 * 	테케 T
	 * 	이동시간 M, BC의 개수 A
	 * 	그다음 2개의 줄에서 A의 이동정보, B의 이동정보
	 * 	0은 이동하지않고 1은 상 2는 우 3은 하 4는 좌
	 * 	A개의 줄동안 충전기의 정보 
	 * 	좌표(X,Y), 충전범위(C), 충전량(P)
	 * 출력:
	 * 	최대충전량
	 * ---------
	 * 일단 현재 위치를 확인
	 * 충전할 수 있는 경우 :
	 * 	한명이 한곳에서만 할 수 있다 -> 그곳에서 충전
	 * 	두명이 한곳에서만 할 수 있다 -> 그곳이 같은곳인지 체크후 같다면 뿜빠이 아니면 따로 더해주기
	 * 	한명은 두곳에서, 한명은 한곳에서 할 수 있다 -> 
	 * 	두명이 두곳이상에서 할 수 있다 -> 제일 높은 2개의 배터리를 하나씩 가져감
	 * 	두개를 동시에 돌면서 만약 true를 만나면  거기 배열 다 false로 하고 나머지에서 하나 돌기
	 * 충전할 수 없는 경우 :
	 * 	그냥 지나감
	 */
	static int[] Amove;
	static int[] Bmove;
	static int[][] BCs;
	static boolean[] Av;
	static boolean[] Bv;
	
	static int[]dy = {0,-1,0,1,0};
	static int[]dx = {0,0,1,0,-1};
	static StringBuilder sb = new StringBuilder();
	static int sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());//이동시간
			int A = Integer.parseInt(st.nextToken());//충전기 갯수
			sum = 0;
			Amove = new int[M];
			Bmove = new int[M];
			BCs = new int[A][4]; 
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				Amove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				Bmove[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				BCs[i][1] = Integer.parseInt(st.nextToken())-1; //X좌표부터 나오므로 반대로
				BCs[i][0] = Integer.parseInt(st.nextToken())-1; //1부터 10까지 하니까 -1해줌
				BCs[i][2] = Integer.parseInt(st.nextToken());
				BCs[i][3] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(BCs, (a,b)->b[3]-a[3]);
//			System.out.println(Arrays.deepToString(BCs));
			
			int Ay = 0;
			int Ax = 0;
			int By = 9;
			int Bx = 9;
			
			//먼저 체크
			Av = new boolean[A];
			Bv = new boolean[A];
			chk(Ay,Ax,0);
			chk(By,Bx,1);
			for(int j=0; j<A; j++) {
				if(Av[j]) {
//					System.out.println("i :"+ -1+" A파워량 : " +BCs[j][3]);
					sum+=BCs[j][3];
					Bv[j] = false;
					for(int k=0; k<A; k++) {
						Av[k] = false;
					}
				}else if(Bv[j]) {
//					System.out.println("i :"+ -1+" B파워량 : " +BCs[j][3]);
					sum+=BCs[j][3];
					Av[j] = false;
					for(int k=0; k<A; k++) {
						Bv[k] = false;
					}
				}
			}
			
			
			for(int i=0; i<M; i++) {
				Ay = Ay+dy[Amove[i]];
				Ax = Ax+dx[Amove[i]];
				By = By+dy[Bmove[i]];
				Bx = Bx+dx[Bmove[i]];
				Av = new boolean[A];
				Bv = new boolean[A];
				
				//그후 체크
				chk(Ay, Ax, 0);
				chk(By, Bx, 1);
//				if(i==10) System.out.println(Arrays.toString(Av));
//				if(i==10) System.out.println(Arrays.toString(Bv));
				int cnt =0;
				for(int j=0; j<A; j++) {
					if(cnt==2) break;
					if(Av[j]&&Bv[j]) {
						sum+=BCs[j][3];
						Av[j] = false;
						Bv[j] = false;
						cnt++;
					}
					else if(Av[j]) {
//						System.out.println("i :"+ i+" A파워량 : " +BCs[j][3]);
						sum+=BCs[j][3];
						Bv[j] = false;
						cnt++;
						for(int k=0; k<A; k++) {
							Av[k] = false;
						}
					}else if(Bv[j]) {
//						System.out.println("i :"+ i+" B파워량 : " +BCs[j][3]);
						sum+=BCs[j][3];
						Av[j] = false;
						cnt++;
						for(int k=0; k<A; k++) {
							Bv[k] = false;
						}
					}
				}
			}
			sb.append("#"+tc+" "+sum+"\n");
				
		}
		System.out.println(sb);
		
		
	}
	static void chk (int y, int x, int AB) { // 거리체크
			for(int i=0; i<BCs.length; i++) {
				if(distance(y,x,BCs[i][0],BCs[i][1],BCs[i][2])) {
					if(AB==0) Av[i] = true;//A일때
					else Bv[i] = true;
				}
			}
		
	}
	
	static boolean distance(int y, int x, int ty, int tx, int c) {
		int distance = Math.abs(y-ty) + Math.abs(x-tx);
		return distance<=c;
	}

}