import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 정사각형 그래프
	 * 1은 나무, 0은 평지
	 * 출발위치에서 도착위치로 이동하면 놀이가 끝난다
	 * BBB에서 EEE로 이동해야함
	 * 상하좌우로 한칸 이동하거나, 회전을 할 수 있음
	 * 대각선으로 놓을 수 없음 (90도 회전만 가능)
	 * 기차의 길이는 항상3, 중심점이 있으므로 그 기준 3x3구역에 단 하나의 나무도 없어야 회전o
	 * 최소 횟수로 BBB에서 EEE로 움직이는 방법은?
	 * ----------------------------------
	 * BFS? 처음 BBB좌표와 EEE좌표를 일단 얻어놓음
	 * 그다음 BFS를 돌릴때 3개 좌표를 동시에 체크함
	 * visited배열도 가로방향일때 하나, 세로방향일때 하나 생성
	 * 그다음 상하좌우일때 거기 1이 있는지 체크,
	 * 만약 가로방향일때 가로로 움직인다면 제일 왼쪽, 오른쪽만 체크하면됨
	 * 	왼쪽으로 갈때 왼쪽에서 -1, 오른쪽으로 갈때 오른쪽에서 +1
	 * 	세로방향이러면 위쪽으로갈때 위쪽-1, 아래로갈때 아래+1
	 * 만약 위와 반대상황이면 전부 다 체크해줘야함
	 * 처음에 입력받을때 먼저 방향체크를 해줌
	 * 그다음 BFS를 돌 때 4방향 전부 움직일수 있다면 움직이고 회전할수 있다면 회전함
	 * ----------------------
	 * 큐에서 poll함
	 * 먼저 방향 체크
	 * 그좌표가 B,B,B좌표인지 확인
	 * 맞다면 리턴, 아니라면 계속진행
	 * 그 다음 4방향으로 움직임
	 * 만약 가로방향일때 가로로 움직인다면 제일 왼쪽, 오른쪽만 체크하면됨
	 * 	왼쪽으로 갈때 왼쪽에서 -1, 오른쪽으로 갈때 오른쪽에서 +1
	 * 	세로방향이러면 위쪽으로갈때 위쪽-1, 아래로갈때 아래+1
	 * 만약 위와 반대상황이면 전부 다 체크해줘야함
	 * 경계체크 먼저 해줌
	 * 그 후 1이 있는지 체크
	 * 그다음 그 좌표에 똑같은 방향으로 visited처리되어있는지 확인
	 * 없다면 visited 처리하고 큐에 넣기
	 * 그 후 그 자리에서 한번 회전시켜봄
	 * 먼저 중심점 기준으로 9방향의 경계 체크, 1체크
	 * 그 후 돌려야할 좌표 visited 체크(3개 전부 체크)
	 * 만약 하나라도 false라면 돌려서 큐에 넣기 그 후 방문처리
	 */
	
	static int N;
	static int[][] graph;
	static boolean[][][] visited;	
	static int [][] start = new int[3][2]; //y, x 좌표
	static int [][] end = new int[3][2];
	static int dir;  //0은 가로, 1은 세로
	static int ans;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx= {0,1,0,-1};
	
	static int[][] delta = {
			{-1, 0},
			{-1, 1},
			{0, 1},
			{1, 1},
			{1, 0},
			{1, -1},
			{0, -1},
			{-1, -1}
	};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new boolean[2][N][N]; //0은 가로방향, 1은 세로방향
		int idx = 0;
		int idx2 = 0;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				if(s.charAt(j)=='B') {
					start[idx][0] = i;
					start[idx++][1] = j;
				} else if(s.charAt(j)=='E') {
					end[idx2][0] = i;
					end[idx2++][1] = j;
				} else {
					graph[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
				}
			}
		}
		if (start[0][1]+1 == start[1][1]) dir = 0;
		else dir = 1;
		BFS();
		System.out.println(ans);
		
	}
	
	static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		for(int i=0; i<3; i++) {
			int y = start[i][0];
			int x = start[i][1];
			q.offer(new int[] {y, x, dir});
			visited[dir][y][x] = true;
		}

		int temp = 0;

		while(!q.isEmpty()) {
			int size = q.size()/3;
			
			while(size-->0) {
				int[][] cur = new int[3][2];
				int dir = q.peek()[2];
				int cnt = 0;
				for(int i=0; i<3; i++) {
					int y = q.peek()[0];
					int x = q.peek()[1];
					q.poll();
					cur[i][0] = y;
					cur[i][1] = x;
					if(y==end[i][0]&&x==end[i][1]) {
						cnt++;
					}
				}
				if(cnt==3) {
					ans = temp;
					return;
				}
				
				if (dir==0) { // 가로방향일 때 
				A:	for(int i=0; i<4; i++) {
						int [][] newcur = new int[3][2];
						for(int j=0; j<3; j++) {
							newcur[j][0] = cur[j][0]+dy[i];
							newcur[j][1] = cur[j][1]+dx[i];
							if(!isInRange(newcur[j][0],newcur[j][1])) continue A;
							if(graph[newcur[j][0]][newcur[j][1]]==1) continue A;
						}
						if(visited[dir][newcur[0][0]][newcur[0][1]]&&visited[dir][newcur[1][0]][newcur[1][1]]&&visited[dir][newcur[2][0]][newcur[2][1]]) {
							continue;
						}
						visited[dir][newcur[0][0]][newcur[0][1]]=visited[dir][newcur[1][0]][newcur[1][1]]=visited[dir][newcur[2][0]][newcur[2][1]]=true;
						for(int k=0; k<3; k++) {
							q.offer(new int[] {newcur[k][0], newcur[k][1], dir});
						}
					}
					if(chk(cur[1][0], cur[1][1])) {
						if(visited[1][cur[1][0]-1][cur[1][1]]&&visited[1][cur[1][0]][cur[1][1]]&&visited[1][cur[1][0]+1][cur[1][1]]) continue;
						visited[1][cur[1][0]-1][cur[1][1]]=visited[1][cur[1][0]][cur[1][1]]=visited[1][cur[1][0]+1][cur[1][1]] = true;
						q.offer(new int[] {cur[1][0]-1, cur[1][1], 1});
						q.offer(new int[] {cur[1][0], cur[1][1], 1});
						q.offer(new int[] {cur[1][0]+1, cur[1][1], 1});
					}

				} else { // 세로방향일 때 
				B:	for(int i=0; i<4; i++) {
						int [][] newcur = new int[3][2];
						for(int j=0; j<3; j++) {
							newcur[j][0] = cur[j][0]+dy[i];
							newcur[j][1] = cur[j][1]+dx[i];
							if(!isInRange(newcur[j][0],newcur[j][1])) continue B;
							if(graph[newcur[j][0]][newcur[j][1]]==1) continue B;
						}

						if(visited[dir][newcur[0][0]][newcur[0][1]]&&visited[dir][newcur[1][0]][newcur[1][1]]&&visited[dir][newcur[2][0]][newcur[2][1]]) {
							continue;
						}
						visited[dir][newcur[0][0]][newcur[0][1]]=visited[dir][newcur[1][0]][newcur[1][1]]=visited[dir][newcur[2][0]][newcur[2][1]]=true;
						for(int k=0; k<3; k++) {
							q.offer(new int[] {newcur[k][0], newcur[k][1], dir});
						}

					}

				if(chk(cur[1][0], cur[1][1])) {
					if(visited[0][cur[1][0]][cur[1][1]-1]&&visited[0][cur[1][0]][cur[1][1]]&&visited[0][cur[1][0]][cur[1][1]+1]) continue;
					visited[0][cur[1][0]][cur[1][1]-1]=visited[0][cur[1][0]][cur[1][1]]=visited[0][cur[1][0]][cur[1][1]+1] = true;
					q.offer(new int[] {cur[1][0], cur[1][1]-1, 0});
					q.offer(new int[] {cur[1][0], cur[1][1], 0});
					q.offer(new int[] {cur[1][0], cur[1][1]+1, 0});
				}

				}
			}
			temp++;

		}
		
	}
	
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}
	static boolean chk(int y, int x) {
		for(int i=0; i<8; i++) {
			int ny = y+delta[i][0];
			int nx = x+delta[i][1];
			if(!isInRange(ny, nx)||graph[ny][nx]==1) return false;
		}
		return true;
	}

}