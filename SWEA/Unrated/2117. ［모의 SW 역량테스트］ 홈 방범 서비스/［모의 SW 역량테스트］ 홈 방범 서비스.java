import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * NxN크기의 정사각형 도시
	 * 감시영역은 마름모 영역
	 * K가 커질수록 운영비용은 커짐 K = K*K + (K-1)*(K-1)
	 * 마름모꼴이 그래프 밖을 나갈 수 있지만 비용은 변하지 않는다.
	 * 하나의 집이 M이라는 비용을 지불할 수 있을 때 보안회사가 손해를 보지 않는 선에서 최대한 많은 집에
	 * 방범서비스를 제공하는 경우와 그때  최대 집의 수를 구하라
	 * ----
	 * 입력:
	 * 	테케 T
	 * 	N은 5이상 20이하의 정수 M은 1이상 10이하의 정수 //맵의크기와 비용
	 * 	N줄동안 도시의 상태 (1은 집, 나머지는 0)
	 * -------
	 * 마름모꼴은 BFS로 거리를 구해서 체크하면된다.
	 * 시간복잡도 한 테케에서 연산 600만, 만약 20x20에서 모든 그래프가 1일때 400 x15000까지만 가능함 
	 */
	
	static class Home{
		int y, x;

		public Home(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static boolean[][] graph;
	static int[] homeD;
	static List<Home> list;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 집당 내는 비용
			graph = new boolean[N][N];
			list = new ArrayList<>();
			ans = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a==1) {
						graph[i][j] = true;
						list.add(new Home(i, j));
						
					} else {
						graph[i][j] = false;
					}
					
				}
			}
			
			homeD = new int[list.size()];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					getDistances(i, j);
				}
			}
			sb.append("#"+tc+" "+ans+"\n");
			
			
		}
		System.out.println(sb);
		

	}
	
	public static void getDistances(int y, int x) {
		int K = -1;
		for(int i=0; i<list.size(); i++) {
			homeD[i] = distance(y, x, list.get(i).y, list.get(i).x);
			if (homeD[i]>K) K = homeD[i]+1;
		}
		Arrays.sort(homeD);//각 리스트별 거리갱신
		
		boolean flag = false;
		for(int i=homeD.length-1; i>=0; i--) {
			int length = homeD[i];
			int cost = (length+1)*(length+1)+length*length;
			if(cost<=(i+1)*M) { // 비용이 남을때
				flag = true;
				ans = Math.max(ans, i+1);
			} else { // 비용이 없을 때 
				if(flag) break;//만약 최댓값을 한번 갱신했다면 그냥 break
				continue; // 아니라면 계속진행 
			}
			
		}
		
	}
	
	public static int distance(int y, int x, int hy, int hx) {
		return Math.abs(y-hy)+Math.abs(x-hx);
	}
	

}