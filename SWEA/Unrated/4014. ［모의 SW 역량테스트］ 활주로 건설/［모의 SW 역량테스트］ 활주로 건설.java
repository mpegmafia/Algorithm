import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 경사로는 길이가 X, 높이는 1
	 * 높이가 동일한 구간에서 건설 가능함
	 * 범위를 벗어나선 안됨
	 * 2개를 겹쳐서 설치할 수 없음
	 * 여러개 설치가 가능함
	 * 행과 열을 하나하나씩 검사해봄
	 * 처음값들 들고 다음값과 비교해보기
	 * 같으면 계속 전진
	 * 다르다면? 대소비교후 현재값이 더작다면 왼쪽에 깔기, 더 크다면 오른쪽에 깔기
	 * 	만약 서로의 높이차이가 1을 초과한다면? 리턴
	 * 	만약 경사로 설치가 범위를 벗어난다면? 리턴
	 * 	이미 거기에 경사로가 깔려있다면? 리턴
	 * 	까는 곳에 높이가 서로 다르다면? 리턴
	 * 재귀로 들어갈 수 있는 경우의 수
	 * 	값이 계속 같을 때
	 * 	경사로를 깔고 들어갈 수 있을 때
	 * 그렇게 해서 N-1까지 들어왔다면? 경우의수 +1
	 * 
	 */
	
	static int N;
	static int X;
	static int[][] graph;
	static boolean[][] isBuilt;
	static int[] dy = {0,1};
	static int[] dx = {1,0};
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 0;
			for(int i=0; i<N; i++) {
				isBuilt = new boolean[N][N];
				DFS(i,0,0); //오른쪽으로 가보기
				isBuilt = new boolean[N][N];
				DFS(0,i,1); // 아래로 가보기
			}
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);
	}
	private static void DFS(int y,int x, int dir) { //현재 좌표, 현재 방향(우 혹은 하), 이전 value
		//기저 조건
		if((dir==0&&x==N-1)||(dir==1&&y==N-1)) {
			ans++;
			return;
		}
		
		//유도 파트
		int ny = y+dy[dir];
		int nx = x+dx[dir];
		if(graph[ny][nx]==graph[y][x]) { //서로 값이 같다면 그냥 들어감
			DFS(ny, nx, dir);
		} else { //서로 값이 다르다면 경사로 설치하기
			if(Math.abs(graph[ny][nx]-graph[y][x])>=2) return; //높이차이가 2이상이라면 리턴
			
			if(graph[ny][nx]>graph[y][x]) { //왼쪽 혹은 위쪽에 깔 때
				if(!canBuild(y, x, dir, -1)) return; //못깔면 리턴
				DFS(ny, nx, dir);

			} else { //오른쪽 혹은 아래쪽에 깔때
				if(!canBuild(ny, nx, dir, 1)) return; //못깔면 리턴
				DFS(ny, nx, dir);
			}
		}
		
	}
	static boolean canBuild(int y, int x, int dir, int isReverse) { // isReverse가 음수면 왼쪽, 위쪽으로 깐다는 뜻
		for(int i=0; i<X; i++) {
			int ny = y + (dy[dir]*i)*isReverse;
			int nx = x + (dx[dir]*i)*isReverse;
			if(!isInRange(ny ,nx)) return false; //범위밖이면 리턴 
			if(graph[y][x]!= graph[ny][nx]) return false; // 높이가 서로 다르면 리턴
			if(isBuilt[ny][nx]) return false; //이미 깔려있으면 리턴
			isBuilt[ny][nx] = true;
		}
		return true;
		
	}
	
	static boolean isInRange(int y, int x) {
		return y>=0&&y<N&&x>=0&&x<N;
	}

}