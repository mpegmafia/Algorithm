import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * N이닝동안 진행 경기 시작 전 타순을 정하고 시작
	 * 경기중에는 타순을 변경할 수 없고 다음 이닝이 되더라도 순서가 초기화되지 않음 
	 * 1번타자는 무조건 4번타자로 고정함 나머지 멤버들을 순열로 돌려서 최고점을 낼때를 구한다
	 * 1,2,3,4,0 = 1루,2루,3루,홈런,아웃
	 * 주자 상태는 배열로 관리? boolean runner[3] 으로 관리
	 */
	
	static int N;
	static int[][] graph; // 칠 수 있는 최대 타수
	static int[] order;
	static int[] picked;
	static boolean[] isSelected;
	static int ans;
	
	static int debug;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][9];
		order = new int[9];
		picked = new int[8];
		isSelected = new boolean[9]; // 8명만 순서 구하면 된다.
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permu(0);
		System.out.println(ans);
		
		
		

	}
	
	public static void playgame() {
		int idx = 0;
		int score = 0;
		
		//order = {1,2,3,0,4,5,6,7,8}; -> graph[i][order[idx++]]
		
		for(int i=0; i<N; i++) {//N이닝동안 진행
			
			int out = 0;
			boolean[] runner = new boolean[3];
			while(true) {
				
				int hit = graph[i][order[idx++]];
//				System.out.println(hit);
				if (idx==9) idx = 0; // 10번이되면 1번으로 넘어감
				
				if (hit==0) {//아웃이라면
					out++;
//					System.out.println("out : " +out+ " N : "+i);
					if(out>=3) break; //이닝종료
				} 
				else if (hit==1) {
					if(runner[2])score++;
					runner[2] = runner[1];
					runner[1] = runner[0];
					runner[0] = true;
				}
				else  if (hit==2) {
					if(runner[2]) score++;
					if(runner[1]) score++;
					runner[2] = runner[0];
					runner[1] = true;
					runner[0] = false;
				}
				else  if (hit==3) {
					if(runner[2]) score++;
					if(runner[1]) score++;
					if(runner[0]) score++;
					runner[2] = true;
					runner[1] = false;
					runner[0] = false;
					
				}
				else  if (hit==4) {
					score++;
					if(runner[2]) score++;
					if(runner[1]) score++;
					if(runner[0]) score++;
					runner[2] = false;
					runner[1] = false;
					runner[0] = false;
					
				}
			}
			
		}
//		if(ans<score) {
//			System.out.println(Arrays.toString(order));
//		}
		ans = Math.max(ans, score);
		
	}
	
	public static void permu(int depth) {
		if(depth==8) {
			int idx = 0;
			for(int i=0; i<9; i++) {
				if(i==3) {//4번타자일때
					order[i] = 0;
					continue;
				}
				order[i] = picked[idx++];
			}
//			debug++;
//			if(debug<=20) {
//				System.out.println(Arrays.toString(order));
//				
//			}
			playgame();
		
			return;
		}
		
		
		for(int i=1; i<9; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			picked[depth] = i;
			permu(depth+1);
			isSelected[i] = false;			
		}
		
		
		
	}

}