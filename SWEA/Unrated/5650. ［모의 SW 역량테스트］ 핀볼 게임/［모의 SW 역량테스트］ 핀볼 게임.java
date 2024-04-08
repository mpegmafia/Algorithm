import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 100x100x100x100 = 1억?
	 * 자기가 출발한 좌표이거나 블랙홀일 때 끝남
	 * 0,1,3,2로 봤을 때(상우하좌)
	 * 벽이나 5에 부딪히면 3의 보수
	 * 1번블럭이면 0, 1일때 3의 보수, 2일때 0, 3일때 1
	 * 2번블럭이면 1번, 3번일때 3의 보수, 0번일때 1번, 2번일때 3번 
	 * 3번블럭이면 2번, 3번일때 3의 보수, 0번일때 2번, 1번일때 3번
	 * 4번블럭이면 0번, 2번일때 3의 보수, 1번일때 0번, 3번일때 2번
	 * 방향으로 튕기기
	 * 6~10 이라면 웜홀, 똑같은 번호의 웜홀로 넘어감
	 * 
	 * 
	 * 첫좌표부터 끝좌표까지 4방 탐색 다해보기
	 * 좌표에 도착한 후 벽인지 아닌지 탐색하기
	 * 처음엔 자기 방향, 좌표만 넣어줌
	 * 먼저 방향에 따라 한칸 움직임
	 *  ny, nx로 경계밖을 벗어나면방향 다시 바꾼후 y,x기준으로 한칸가기 (3-dir로 방향 바꿔주고 한칸 움직이기 그다음 cnt+1)
	 * 만약 그 좌표가 벽이라면 방향과 벽 모양에 따라 다음 좌표로 가기
	 *  ny, nx기 만약 벽이라면 방향을 바꾼후 ny, nx에서 한칸 더가기
	 *  
	 *  웜홀 체크
	 *  웜홀은 반드시 한쌍이 더주어짐
	 *  
	 * 자기자신의 좌표라면 그때까지 벽 튕긴 횟수를 리턴
	 * 블랙홀을 만나면 그때까지 벽 튕긴 횟수를 리턴
	 * 이렇게 전부 다 탐색?(DFS?)
	 */
	static int N;
	static int[][] graph;
	
	static int[] dy = {-1,0,0,1};
	static int[] dx = {0,1,-1,0};
	
	static int[][] warmholes;
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			graph = new int[N][N];
			warmholes = new int[11][4];
			for(int i=6; i<=10; i++) {
				Arrays.fill(warmholes[i], -1);
			}
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j]=Integer.parseInt(st.nextToken());
					if(graph[i][j]>=6 && graph[i][j]<=10) {
						if(warmholes[graph[i][j]][0]==-1&&warmholes[graph[i][j]][1]==-1) {
							warmholes[graph[i][j]][0]=i; //y먼저 넣기
							warmholes[graph[i][j]][1]=j; 
						} else {
							warmholes[graph[i][j]][2]=i;
							warmholes[graph[i][j]][3]=j;
						}
					}
				}
			}
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(graph[i][j]==0) {
						for(int k=0; k<4; k++) {
							DFS(i+dy[k], j+dx[k], k, i, j, 0);
						}
					}
				}
			}
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}

	private static void DFS(int y, int x, int dir, int originY, int originX, int val) {
		int ny = y;
		int nx = x;
		while(true) {
			if(!isInRange(ny, nx)) {
				dir = 3-dir;
				ny = ny+dy[dir];
				nx = nx+dx[dir];
				val++;
				continue;
			} 
			
			if (graph[ny][nx]==-1) {
				ans = Math.max(ans, val);
				return;
			} else if (ny==originY&&nx==originX) {
				ans = Math.max(ans, val);
				return;
			} else if (graph[ny][nx]==0) {
				ny = ny+dy[dir];
				nx = nx+dx[dir];
				continue;
			} else if (graph[ny][nx]>=6 && graph[ny][nx]<=10) {
				int cur = graph[ny][nx];
				if(warmholes[graph[ny][nx]][0]==ny&&warmholes[graph[ny][nx]][1]==nx) {
					ny = warmholes[cur][2]+dy[dir];
					nx = warmholes[cur][3]+dx[dir];
					continue;
				} else {
					ny = warmholes[cur][0]+dy[dir];
					nx = warmholes[cur][1]+dx[dir];
					continue;
				}
			} else {
				if(graph[ny][nx]==1) {
					if(dir==0||dir==1) {
						dir = 3-dir;
					} else {
						dir = dir-2;
					}
					
				} else if (graph[ny][nx]==2) {
					if(dir==1||dir==3) {
						dir = 3-dir;
					}else {
						dir = 1+dir;
					}
					
				}else if (graph[ny][nx]==3) {
					if(dir==2|dir==3) {
						dir = 3-dir;
					} else {
						dir = 2+dir;
					}
					
				}else if (graph[ny][nx]==4) {
					if(dir==0||dir==2) {
						dir = 3-dir;
					} else {
						dir = dir-1;
					}
					
				}else if (graph[ny][nx]==5) {
					dir = 3-dir;
				}
				ny = ny+dy[dir];
				nx = nx+dx[dir];
				val++;
				continue;
				
			}
			
		}
		
		
	}
	static boolean isInRange(int y, int x) {
		return x>=0&&x<N&&y>=0&&y<N;
	}
}