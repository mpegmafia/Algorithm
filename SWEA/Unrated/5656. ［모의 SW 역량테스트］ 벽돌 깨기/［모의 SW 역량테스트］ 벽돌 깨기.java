import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 구슬을 쏴서 벽돌을 깨트리기
	 * 벽돌에 표시된 숫자만큼 상하좌우가 제거됨
	 * 상하좌우 제거시에 다른 벽돌들도 "동시에" 그 숫자만큼 터짐
	 * 빈 공간이 있다면 벽돌은 밑으로 떨어지게 된다
	 * N개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거시 남은 벽돌의 개수를 구하라
	 * -------------------------------------------------
	 * N=4
	 * W=12
	 * H=15
	 * WxH = 180
	 * 구슬을 어디다 쏴야 하는가 -> 12중복순열4 = 20000? 
	 * 최대시간복잡도 20000*180 =4000000x4 = 1600만?
	 * ---------------------------------
	 * 구슬을 쏠 위치를 구하기 -> 중복순열
	 * 처음만난 벽돌을 부수기
	 * 벽돌이 부숴질때 연쇄작용 구현하기
	 * ->DFS? 로 하나씩 다 부수기?
	 * 다 부숴진 후 남은 벽돌들을 바닥에 붙이기
	 * 바닥부터 한 열을 검사해서 투포인터로 구현?
	 * 
	 * 	i를 0으로, j를i+1로 초기화 
	 * 	i에 값이 없을 때
	 * 		j에 값이 있을 때 : i에 j를 넣고 j를 0으로 세팅 후 i+1, j+1
	 * 		j에 값이 없을 때 : j+1
	 * 	i에 값이 있을 때
	 * 		j에 값이 있을 때 : i+1
	 * 		j에 값이 없을 때 : i+1  -> j는 0을 만나면 무조건 멈춤 
	 * 	i<=j 일 경우거나 j<높이일때까지만 반복
	 * 이렇게 N번까지 벽돌 부수기 반복후 남은 벽돌갯수 리턴
	 *  
	 */
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};

	static int N; //쏘는 횟수
	static int W; //가로
	static int H; //높이
	static int[][] graph;
	static int[] shotOrder; //쏘는 순서
	static int ans;//최종 정답
	static int[][] playGraph;
	
	static int remains; //각각 중복순열마다 도출되는 벽돌개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			graph = new int[H][W];
			ans = Integer.MAX_VALUE;
			playGraph = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			shotOrder = new int[N];
			
			permu(0);
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}
	
	public static void permu(int depth) {
		if(depth==N) { // N개 다 뽑았을 때
			for(int i=0; i<H; i++) 	System.arraycopy(graph[i], 0, playGraph[i], 0, W); //어레이카피
			play();
			return;
		}
		
		for(int i=0; i<W; i++) {
			shotOrder[depth]= i;
			permu(depth+1);
		}
		
	}
	
	public static void play() {
		remains = 0;
		for(int i=0; i<N; i++) 	shoot(shotOrder[i]); //순서대로 쏘기
		remains = getBlocks();
		ans = Math.min(ans, remains);
	}
	
	public static void  shoot(int loc) {
		
		for(int i=0; i<H; i++) {
			if(playGraph[i][loc] != 0 ) {
				DFS(i, loc, playGraph[i][loc]);
				afterShoot();
				return; //한번 만나면 리턴해주기;;
			}
		}
	}

	public static void DFS(int y, int x, int range) {
		playGraph[y][x] = 0; // 일단 현재값 0으로 변경
		for(int i=1; i<range; i++) {
			for(int j=0; j<4; j++) {
				int ny = y+dy[j]*i;
				int nx = x+dx[j]*i;
				if(!isInRange(ny,nx)) continue;
				if(playGraph[ny][nx]==1||playGraph[ny][nx]==0) playGraph[ny][nx] = 0; // 1이면 걍 안들어가기
				else if (playGraph[ny][nx]>=2) DFS(ny, nx, playGraph[ny][nx]);
			}
		}
		
	}
	
	public static void afterShoot() {
		for(int col=0; col<W; col++) {
			int i=H-1;
			int j=H-2; // 바닥부터 세팅
			while(i>=j&&j>=0) {
				if(playGraph[i][col] == 0) { //만약 i값이 없을 때
					if(playGraph[j][col]==0) {  //j값도 없다면
						j--; //j는 수를 찾아 간다
					} else { //j가 있다면
						playGraph[i][col] = playGraph[j][col];
						playGraph[j][col] = 0;
						i--; //i는 j값이 되고 i는 한칸 앞으로 전진
					}
					
				}
				else if(playGraph[i][col] != 0) { //i값이 있을 때
					if(playGraph[j][col]==0) { //j가 0이라면
						i--;  //i는 0을 찾아 전진
					} else { //j가 0이 아니라면  j는 0을 찾아, i도 0을 찾아 전진
						i--;
						j--;
					}
					
				}
				
			}
			
		}
		

	}
	public static int getBlocks() {
		int temp = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(playGraph[i][j]>0) temp++;
			}
		}
		return temp;
	}
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<H&&x>=0&&x<W;
	}
}