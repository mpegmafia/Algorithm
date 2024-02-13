import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 입력:
	 * 	N, M(그래프의 크기, 살아남아야하는 치킨집 수)
	 * 	0은 빈칸, 1은 집, 2는 치킨집
	 * 
	 * 출력:
	 * 	폐업시키지 않은 치킨집을 최대 M개를 골랐을 때 도시의 치킨거리의 최솟값
	 *  --------
	 *  치킨집 최대 개수중 M개를 골라서 조합 돌리기
	 *  for문으로 입력받으면서 집 좌표도 구해놓기
	 *  조합에서 나온 좌표들을 대상으로 집까지 거리에서의 최솟값을 구해서 더해주기
	 *  homes에서 chickens.get(i)까지 거리중 최솟값 구하기
	 *  그 값들을 다시 최솟값인지 확인후 대입
	 */
	static class chicken {
		int y;
		int x;
		
		chicken(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static class home {
		int y;
		int x;
		
		home(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int N;
	static int M;
	static List<home> homes = new ArrayList<>();
	static List<chicken> chickens = new ArrayList<>();
	static int[] picked;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		picked = new int[M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==1) {
					homes.add(new home(i,j));
				}
				else if(temp==2) {
					chickens.add(new chicken(i,j));
				}
			}
		}
		combi(0,0);
		System.out.println(ans);
		
		
		

	}
	public static void combi(int depth, int start) {
		if(depth==M) {
			int temp = 0;
			for(home h : homes) {
				int dist = Integer.MAX_VALUE;
				for(int i=0; i<M; i++) {
					dist = Math.min(dist, range(h,chickens.get(picked[i])));
				}
				temp+=dist;
			}
			ans = Math.min(ans, temp);
			return;
			
		}
		for(int i=start; i<chickens.size(); i++) {
			picked[depth] = i;
			combi(depth+1, i+1);
		}
		
		
	}
	
	public static int range(home h, chicken e) {
		return Math.abs(h.y-e.y)+Math.abs(h.x-e.x);
	}
}