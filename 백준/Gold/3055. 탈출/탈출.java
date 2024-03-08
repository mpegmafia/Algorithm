import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * R행 C열 
	 * 비어있는 곳은 '.' 
	 * 물이차있는 지역은 '*'
	 * 비버의 굴은 'D'  (목표물) 
	 * 고슴도치의 위치 'S'
	 * 매 분마다 상하좌우로 움직일 수 있음 물, 고슴도치는 돌 통과 불가능, 물도 매 분마다 차게됨 물은 비버의 소굴로 이동 불가
	 * 고슴도치가 안전하게 이동하기 위해 필요한 최소시간 출력
	 * 고슴도치는 물이 찰 예정인 칸에 이동 불가능 
	 * --------------------------
	 * 입력:
	 * 	R 행  C 열 
	 * 	R행동안 graph (string으로)
	 * 먼저 물을 뿌린다 BFS로
	 * 	고슴도치위치라면 뿌리기
	 * 	돌이라면 continue
	 * 	물이라면 continue
	 * 그다음 고슴도치를 BFS로 갈긴다
	 * 	범위벗어나면 continu
	 * 	돌이라면 continue
	 * 	물이라면 continue
	 * 그렇게해서 굴 D를 만나면 시간출력 , 못만나면 KAKTUS출력
	 */
	
	static int R, C;
	static char[][] graph;
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static Queue<int[]> sq = new ArrayDeque<>();
	static Queue<int[]> wq = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		for(int i=0; i<R; i++) {
			String s= br.readLine();
			for(int j=0; j<C; j++) {
				graph[i][j] = s.charAt(j);
				if(graph[i][j]=='*') wq.offer(new int[] {i, j});
				if(graph[i][j]=='S') sq.offer(new int[] {i, j});
			}
		}
		
		BFS();

	}
	
	public static void BFS() {
		int cnt=1;
		
		while(!sq.isEmpty()) {
			int wsize = wq.size();
			
			while(wsize-->0) {
				int wy = wq.peek()[0];
				int wx = wq.peek()[1];
				wq.poll();
				for(int i=0; i<4; i++) {
					int nwy = wy+dy[i];
					int nwx = wx+dx[i];
					
					if(!isInRange(nwy, nwx)) continue;
					if(graph[nwy][nwx]=='X') continue;
					if(graph[nwy][nwx]=='*') continue;
					if(graph[nwy][nwx]=='D') continue;
					graph[nwy][nwx] = '*';
					wq.offer(new int[] {nwy, nwx});
				}
			}
			int ssize = sq.size();
			
			while(ssize-->0) {
				int sy = sq.peek()[0];
				int sx = sq.peek()[1];
				sq.poll();
				for(int i=0; i<4; i++) {
					int nsy = sy+dy[i];
					int nsx = sx+dx[i];
					
					if(!isInRange(nsy, nsx)) continue;
					if(graph[nsy][nsx]=='X') continue;
					if(graph[nsy][nsx]=='*') continue;
					if(graph[nsy][nsx]=='S') continue;
					if(graph[nsy][nsx]=='D') {
						System.out.println(cnt);
						return;
					}
					graph[nsy][nsx]='S';
					sq.offer(new int[] {nsy, nsx});
					
				}
			}
			
			cnt++;
			
		}
		System.out.println("KAKTUS");
		return;
		
		
		
	}
	public static boolean isInRange(int y, int x) {
		return y>=0&&y<R&&x>=0&&x<C;
	}

}