import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 모그레브와 자그레브에서 한번씩 BFS를 돈다
	 * 각 문양에 따라 퍼지는 방향을 다르게 해준다
	 * visited로 걸러도됨
	 * 만약 퍼져야하는 방향에 '.'가 있다면
	 * 그 방향을 기준으로 숫자를 더해줌 (1,2,4,8)
	 * 그렇게 다 돈 후 숫자에 해당하는 인덱스를 선택해주면 된다.
	 * 
	 * 
	 * ----------
	 * 빈칸을 만났을때도 큐에 넣어줘야함
	 */
	
	static int R;
	static int C;
	static char[][] graph;
	static boolean[][] visited;
	
	static int[] Mos = new int[2]; //moscow
	static int[] Zag = new int[2];

	static char[] pipes = new char[16];
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int ans = 0;
	static int[] loc = new int[2];
	static int[] nums = {4,8,1,2};
	
	public static void main(String[] args) throws IOException{
		pipes[5] = '|';
		pipes[10] = '-';
		pipes[15] = '+';
		pipes[6] = '1';
		pipes[3] = '2';
		pipes[9] = '3';
		pipes[12] = '4';
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		visited = new boolean[R+1][C+1];
		graph = new char[R+1][C+1];
		
		for(int i=1; i<=R; i++) {
			String s = br.readLine();
			for(int j=1; j<=C; j++) {
				graph[i][j] = s.charAt(j-1);
				if(graph[i][j]=='M') {
					Mos[0] = i;
					Mos[1] = j;
				} else if (graph[i][j]=='Z') {
					Zag[0] = i;
					Zag[1] = j;
				}
			}
		}
		
		
		BFS();
		System.out.println(loc[0]+" "+loc[1]+" "+pipes[ans]);
		
		

	}
	
	public static void BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		visited[Mos[0]][Mos[1]] = true;
		visited[Zag[0]][Zag[1]] = true;
		
		q.offer(Mos);
		q.offer(Zag);
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			
			if (graph[y][x]=='|') {
				for(int i=0; i<4; i++) {
					if(i==1||i==3) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else if (graph[y][x]=='-') {
				for(int i=0; i<4; i++) {
					if(i==0||i==2) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
				
			} else if (graph[y][x]=='+') {
				for(int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else if (graph[y][x]=='1') {
				for(int i=0; i<4; i++) {
					if(i==0||i==3) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else if (graph[y][x]=='2') {
				for(int i=0; i<4; i++) {
					if(i==2||i==3) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else if (graph[y][x]=='3') {
				for(int i=0; i<4; i++) {
					if(i==1||i==2) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					if (visited[ny][nx]) continue;
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else if (graph[y][x]=='4') {
				for(int i=0; i<4; i++) {
					if(i==0||i==1) continue;
					int ny = y+dy[i];
					int nx = x+dx[i];
					
					if (visited[ny][nx]) continue;
					
					if (graph[ny][nx]=='.') {
						loc[0] = ny;
						loc[1] = nx;
						ans+=nums[i];
						q.offer(new int[] {ny, nx});
					}
					else {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
				
			} else {
				for(int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(ny<1||ny>R||nx<1||nx>C) continue;
					if(visited[ny][nx]) continue;
					if(graph[ny][nx]!='.') {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
			}
			
		}
		
		
	}
	
//	static void search(Queue<int[]> q, int y, int x) {
//		for(int i=0; i<4; i++) {
//			int ny = y+dy[i];
//			int nx = x+dx[i];
//			if (visited[ny][nx]) continue;
//			if (graph[ny][nx]=='.') ans+=nums[i];
//		}
//		
//		
//		
//	}
	
	

}