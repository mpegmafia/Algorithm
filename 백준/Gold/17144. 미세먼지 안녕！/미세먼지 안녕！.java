import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 공기청정기의 위치 1,1 과 2,1 (두칸)
	 * 미세먼지는 인접한 네방향으로 확산
	 * 	공기청정기가 있다면 미확산, 범위 밖이라도 미확산
	 * 본 미세먼지의 확산되는 양은 소숫점을 버린 나누기 5의 값
	 * 본 미세먼지의 남아있는 양은 원래남아있는양 - 확산되는양*확산되는 방향의 갯수
	 * 공기청정기가 작동한다
	 * 공기청정기에서는 바람이 나온다
	 * 위쪽 공기청정기의 바람은 반시계방향으로 순환하고 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
	 * 바람이 불면 미세먼지가 바람의 방향대로 모두 한칸씩 이동한다
	 * ----------------------
	 * 확산 메커니즘
	 * 	원래 값이 있고 그걸 확산시켜야함
	 *  다른 미세먼지로 부터 추가되는 양은 확산시키는 양에 영향을 주면 안됨
	 *  클래스배열생성? 원래 가지고 있는양을 가지고 확산시킴 (모체만 확산시키기)
	 *  다른 좌표에 클래스가 있다면 그 좌표의 임시값에 더해주기
	 *  없다면 새로운 객체를 거따가 생성
	 *  다 확산시킨후 가지고있는 임시값을 다 더해주기 (다 모체로 세팅)
	 *  
	 *  확산할때 BFS
	 *  확산시키는 양은 tempDust에다가 더하기
	 *  확산 못시키는경우? -> 5보다 작을 때
	 *  5보다 작을때는 그냥 null처리 해주기
	 *  아니라면 방향 확산시킬때마다 cnt++해주고
	 *  자기자신처리 그후에 해주기
	 *  
	 * 회전 메커니즘
	 * 	청소기의 위쪽좌표부터 시계방향으로 돌리기
	 * 	 만약 받는 좌표가 청소기좌표라면 그건 0 처리
	 *   while문을 돌면서 경계밖으로 나가거나 청소기 좌표보다 아래면 dir++
	 *   땡기려는 좌표가 -1이면 브레이크
	 *  청소기의 아래쪽좌표부터 반시계방향으로 돌기
	 *   만약 받는 좌표가 청소기 좌표라면 그건 0 처리
	 *   while문을 돌면서 경계밖으로 나가거나 청소기 좌표보다 위라면  dir--
	 *   땡기려는 좌표가 -1이면 브레이크 
	 */

	
	static class Dust{
		int r, c, isMother, curDust, tempDust;
		

		public Dust(int r, int c, int isMother, int curDust, int tempDust) {
			super();
			this.r = r;
			this.c = c;
			this.isMother = isMother;
			this.curDust = curDust;
			this.tempDust = tempDust;
		}
		
		public void tempPlus() {
			this.curDust += tempDust;
			this.tempDust = 0;
			this.isMother = 1;
			
		}
		
		
	}
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	static int R;
	static int C;
	static Dust[][] graph;
	
	static int dysonMin;
	static int dysonMax;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		graph = new Dust[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph[i][j] = new Dust(i, j, 1, num, 0);
				if(num==-1&&dysonMin==0) {
					dysonMin = i;
					dysonMax = i+1; // 청소기 좌표
				}
			}
		}
		
		
		for(int i=0; i<T; i++) {//T번 돌리기	
			spread();
			rotate();
		}
		
		chk();

	}
	
	static void chk() {
		int temp = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(graph[i][j]==null||graph[i][j].curDust==-1) continue;
				temp+=graph[i][j].curDust;
			}
		}
		System.out.println(temp);
		
	}
	
	static void spread() {
		
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(graph[i][j]==null||graph[i][j].isMother==0) continue; //모체가 아니거나 널이면 컨티뉴
				
				if(graph[i][j].curDust!=-1 && graph[i][j].curDust/5==0) { //5미만이면 삭제하는게 아니었따리
					continue;
				}
				
				int cnt = 0;
				for(int dir =0; dir<4; dir++) {
					int ny = i+dy[dir];
					int nx = j+dx[dir];
					if(!isInRange(ny, nx)) continue;
					
					if(graph[ny][nx]!=null) {
						if(graph[ny][nx].curDust==-1) {
							continue;
						}
						cnt++;
						graph[ny][nx].tempDust+=graph[i][j].curDust/5;
						
					} else {
						cnt++;
						graph[ny][nx] = new Dust(i,j,0,0,graph[i][j].curDust/5);
					}
				}
				graph[i][j].curDust = graph[i][j].curDust - (graph[i][j].curDust/5)*cnt;
				
			}
		}
//		for(int k=0; k<R; k++) {
//			for(int j=0; j<C; j++) {
//				if(graph[k][j]==null)System.out.print(0+" ");
//				else System.out.print(graph[k][j].curDust+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(graph[i][j]==null||graph[i][j].curDust==-1) continue;
				graph[i][j].tempPlus();
			}
		}
//		for(int k=0; k<R; k++) {
//			for(int j=0; j<C; j++) {
//				if(graph[k][j]==null)System.out.print(0+" ");
//				else System.out.print(graph[k][j].curDust+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
	
	public static void rotate() {
		
		int y = dysonMin-1;
		int x = 0;
		int dir =0;
		
		while(true) {
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			if(!isInRange(ny, nx)||(ny==dysonMin+1&&nx==C-1)) {
				dir++;
				continue;
			}
			graph[y][x] = graph[ny][nx];
			if(ny==dysonMin&&nx==1) break;
			y = ny;
			x = nx;
		}
		
		graph[dysonMin][1] = null;
		
		y = dysonMax+1;
		x = 0;
		dir = 2;
		
		while(true) {
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			if(!isInRange(ny, nx)||(ny==dysonMax-1&&nx==C-1)) {
				dir--;
				if(dir==-1) dir=3;
				continue;
			}
			graph[y][x] = graph[ny][nx];
			if(ny==dysonMax&&nx==1) break;
			y = ny;
			x = nx;
		}
		graph[dysonMax][1] = null;
		
	}
	
	public static boolean isInRange(int y, int x) {
		return x>=0&&x<C&&y>=0&&y<R;
	}

}