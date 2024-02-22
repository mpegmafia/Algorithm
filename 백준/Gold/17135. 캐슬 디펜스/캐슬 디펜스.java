import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * NxM의 격자판 격자판의 맨아래, N+1번행에는 성이 있고 거기 위에는 궁수가 있다.
	 * 성에 궁수를 3명 배치한다, 하나의 칸에 최대 1명의 궁수만 있을 수 있다 궁수는 적 하나를 공격할 수 있고 동시에 공격한다
	 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고 그러한 적이 여럿일경우 가장 왼쪽에 있는 적 공격
	 * 여러 궁수가 같은 적을 공격할 수 있다. 공격받은 적은 삭제된다
	 * 궁수의 공격이 끝나면 적이 아래로 한칸 이동한다
	 * 성이 있는 칸까지 이동한다면 게임에서 제외되고 모든 적이 격자판에서 제외되면 게임이 종료된다.
	 * 궁수의 위치가 중요한 게임이다. 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산하라
	 * 거리 D는 직선거리
	 * 입력:
	 * 	N, M
	 *  N줄동안 그래프
	 *  0은 빈칸, 1은 적
	 * ---------------
	 *  일단 궁수를 배치할 수 있는 모든경우의수 5c3을 구현
	 *  그다음 게임을 진행한다
	 *    궁수가 동시에 적을 공격한다. 일단 공격할 타겟을 선택
	 *    거리 D이하인 적중 가장 왼쪽에 있는 적을 선택한다.
	 *    모든 적을 선택했다면 동시에 제거하기
	 *    그다음 남아있는 적은 한칸씩 아래로 전진
	 *   위의 과정을 모든 적들이 사라질 때 까지 반복
	 */
	static int[] dy = {0,-1,0};
	static int[] dx = {-1,0,1}; //아래는 가볼필요가없음 왼쪽부터 탐색해서 가장 먼저 발견하는놈셀렉

	static int N;
	static int M;
	static int D;
	static int[][] realgraph;
	static int[][] graph;
	
	static int[][] selected = new int[3][2];
	static int[] archer = new int[3];
	
	static Queue<int[]> q = new ArrayDeque<>();
	
	static int ans;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		realgraph = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				realgraph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(0, 0);
		System.out.println(ans);

	}
	
	public static void combi(int depth, int start) {
		if (depth==3) {
//			System.out.println(1);
			for(int i=0; i<N; i++) {
				System.arraycopy(realgraph[i], 0, graph[i], 0, M);
			}
			playgame();
			return;
		}
		
		for(int i=start; i<M; i++) {
			archer[depth] = i;
			combi(depth+1, i+1);
		}
	}
	
	public static void playgame() {
		int killCnt = 0;
		
		while(chk()) {
//			System.out.println("11");
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
			for(int i=0; i<3; i++) {
				selectEnemy(N, archer[i], i); //적 선택 좌표
			}
			killCnt += killEnemy();
			moveEnemy();
//			System.out.println(killCnt);
			
		}
		
		ans = Math.max(ans, killCnt);
		
		
	}
	
	public static int killEnemy() {
		int temp = 0;
//		for(int j=0; j<3; j++) {
//			System.out.println("selected : ");
//			System.out.println(Arrays.toString(selected[j]));
//			
//		}
		for(int i=0; i<3; i++) {
			int y = selected[i][0];
			int x = selected[i][1];
			if(y == -1 || graph[y][x]==0) continue;
			else {
				graph[y][x] =0;
				temp++;
			}
			
		}
		
		return temp;
	}
	
	public static void moveEnemy() {
		
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(i==N-1&&graph[i][j]==1) graph[i][j] = 0;
				else if(graph[i][j] == 1) {
					graph[i][j] = 0;
					graph[i+1][j] = 1;
				}
			}
		}
	}
	
	
	
	public static void selectEnemy(int y, int x, int num) {
		boolean[][] visited = new boolean[N][M];
		q.offer(new int[] {y,x});
		while(!q.isEmpty()) {
//			System.out.println("22");
			int cy = q.peek()[0];
			int cx = q.peek()[1];
//			System.out.println("cy : "+ cy + " cx : " + cx);
			q.poll();
			for(int i=0; i<3; i++) {
				int ny = cy+dy[i];
				int nx = cx+dx[i];
				
				if(!isInRange(ny,nx)) continue;
				if(!isInDistance(ny, nx, x)) continue;
				if(graph[ny][nx]==1) {
//					System.out.println("ny : " + ny +" nx : " + nx);
					selected[num][0] = ny;
					selected[num][1] = nx;
					q.clear();
					return;
				}else if (graph[ny][nx] == 0&&visited[ny][nx] == false){
					visited[ny][nx] = true;
					q.offer(new int[] {ny, nx});
				}
			}
		}
		//적 발견 못했을시
		selected[num][0] = -1;
		selected[num][1] = -1;
		
	}
	
	
	public static boolean chk() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (graph[i][j]==1) return true;
			}
		}
		return false;
	}
	
	public static boolean isInRange(int y, int x) { //그래프를 벗어날때
		return y>=0&&y<N&&x>=0&&x<M;
	}
	public static boolean isInDistance(int y, int x, int arcx) { //적과 현재 아처와의 거리
		int arcy = N;
		return (Math.abs(y-arcy)+Math.abs(x-arcx))<=D; //거리내에 있는지 체크
	}

}