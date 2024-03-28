import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * NxN 정사각형
	 * 각 칸에는 벌통에 있는 꿀의 양이 있음
	 * 최대한 많은 수익 얻기
	 * bee만큼의 벌꿀이 M만큼 벌통을 채취할 수 있을 때 
	 * 서로 겹치면안되고 가로로 배치해야한다
	 * 
	 * ------------
	 * 
	 * 그래프에서 조합으로 M크기만큼 좌표 위치 구하기
	 * i좌표 + j좌표 한다음 +M을 해준후 다시 /N 을하면 i좌표, 나머지는 j좌표
	 * 	만약 더한값의 +M-1을 한 좌표의 i값이 M만더한값의 i좌표와 다르다면 +1해줘서 i좌표가 서로 같을때까지 ++
	 * 이렇게 나온 좌표값들을 a파트와 b파트로 나눔 
	 * a파트에서 C가 안넘을때중 최선을 선택한 후 그 각각 좌표들을의 제곱값을 더한 값을 리턴함
	 * 
	 * C가 안넘는 경우중 최댓값?
	 * 부분집합으로 다 확인하기?
	 * 	부분집합을 구현했을 때 거기서의 임시 최댓값을 저장해놓은 후 그거 이하면 리턴	
	 * 
	 */

	static int N; //그래프 크기
	static int M; //선택할 수 있는 벌통의 개수
	static int C; // 채취할 수 있는 최대 양 C
	static int[][] graph;
	
	static int locA;
	static int locB;
	
	static int tempA;
	static int tempB;
	
	
	static boolean[] selected;
	
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1 ; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			ans = 0;
			selected = new boolean[M]; // M개중에서 부분조합으로 선택하기
			graph = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			combi(0,0);
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb);

	}
	
	static void combi(int depth, int start) {
		if(depth==2) { //2개 선택했을시에
			
			tempA = 0;
			tempB = 0;
			
			powerSet(0, locA, 0);
			powerSet(0, locB, 1);
			
			ans = Math.max(ans, tempA+tempB);
			
			return;
			
		}
		
		for(int i=start; i<=N*N-M; i++) {
			
			if(!isInOneRow(i)) continue; //만약 가로방향으로 배치할 수 없을 시 컨티뉴
			
			if(depth==0) { //첫좌표일시
				
				locA =  i;
				combi(depth+1, i+M);
				
			} else { // 두번째 좌표일시
				
				locB = i;
				combi(depth+1, i+M);
				
			}
			
			
		}
		
		
	}
	
	static void powerSet(int depth, int loc, int which) {
		if(depth==M) { // M개 다 선택했을때
			int temp = 0;
			int y = loc/N;
			int x = loc%N;
			for(int i=0; i<M; i++) {
				if(!selected[i]) continue; // 선택안됐을시 컨티뉴
				temp+=graph[y][x+i];
			}
			if(temp>C||temp==0) return; //C 초과시 리턴, 0일시에도 리턴
			int honeys = 0;
			for(int i=0; i<M; i++) {
				if(!selected[i]) continue;
				honeys +=  graph[y][x+i]*graph[y][x+i];
			}
			if(which==0) {
				tempA = Math.max(tempA, honeys);
				
			} else {
				tempB = Math.max(tempB, honeys);
			}
			return;
			
		}
		
		
		selected[depth]=true;
		
		powerSet(depth+1, loc, which);
		
		selected[depth]=false;
		
		powerSet(depth+1, loc, which);
		
		
	}
	
	static boolean isInOneRow(int loc) {
		int firstrow = loc/N;
		int secondrow = (loc+M-1)/N;
		return firstrow==secondrow;
		
		
	}
	
	

}