import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 행 D, 열 W
	 * 약품을 최소로 쳐서 안전검사 통과하기
	 * 부분집합, 백트래킹?
	 * 재귀에 매개변수로 약품친 횟수를 같이 넘기기
	 * 각각의 행마다 -1,0,1을 선택할 수 있음
	 * 0은 A, 1은 B  선택안했으면 -1
	 * 
	 */

	static int R;
	static int C;
	static int K;
	static int[][] graph;
	static int[] medical;
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			graph = new int[R][C];
			medical = new int[R];
			ans = Integer.MAX_VALUE;
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			DFS(0, 0);
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb);
	}
	
	public static void DFS(int depth, int cnt) {
		if(ans<=cnt) return;
		
		if(depth==R) {
			if (chk()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		medical[depth] = -1;
		DFS(depth+1, cnt);
		
		medical[depth] = 0;
		DFS(depth+1, cnt+1);
		
		medical[depth] = 1;
		DFS(depth+1, cnt+1);
		
	}
	
	//열을 0부터 하나씩 검사, 어떤 것이든 K개만 연속되어있으면 됨
	//처음에 temp가 0으로 시작, 인덱스 0일때 초기값 세팅, 만약 값이 다른게 나오면 temp 1로 초기화 후에 임시값 변경, 그후temp가 K개 넘었는지 확인
	//넘었으면 continue 안넘었으면 false 리턴
	//열 체크할때 먼저 medical 배열이 -1인지 0인지 1인지 체크 
	public static boolean chk() {
		
	A:	for(int j=0; j<C; j++) {
			
			int temp = 1;
			int val = -2;
			for(int i=0; i<R; i++) {
				int tempval = -2;
				if(medical[i]==-1) { //약품 안쳤을때
					tempval = graph[i][j];
				} else { //약품을 친 경우
					tempval = medical[i];
				}
				if(i==0) {
					val = tempval;
					continue;
				}
				if(val == tempval) {
					temp++;
					if(temp>=K) continue A;
				} else {
					if(temp>=K) continue A;
					temp = 1;
					val = tempval;
				}
				if(i==R-1) return false;
			}
		}
		return true;
		
	}

}