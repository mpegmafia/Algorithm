import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 각 군집들은 1시간마다 이동방향에 있는 다음셀로 이동
	 * 이동방향은 1,2,3,4 순으로 상하좌우 
	 * 만약 테두리에 도착한다면 미생물이 반으로줄고(소수점 이하는 버림) 방향이 반대로 바뀜
	 * 이동 후 두개이상이 군집이 한셀에 모이는 경우 군집들이 합쳐짐, 최고로 큰양을 가진 미생물의 방향을 따라가며 양이 전부 합쳐짐
	 * ------
	 * 각 군집을 1시간마다 이동시킴 이때 경계선에 들어간다면 경계선 체크해줌
	 * 또한 맵을 2개를 써서 기존맵에서 새로운 맵으로 이동시키고 만약 새로운 맵에 이동완료된 또다른 군집이 존재한다면
	 * 그 아이랑 합친다. 합칠 때 임시 맥시멈값을 저장해주고 만약 4개가 동시에 합쳐지는 상황일 경우 임시 맥시멈값을 계속 갱신시켜줘서 최대값을 비교하자
	 */
	static class Micro{
		int amount, dir;
		int tempMax;
		public Micro(int amount, int dir) {
			super();
			this.amount = amount;
			this.dir = dir;
			this.tempMax = amount;
		}
		
		public void edge() {
			if(this.dir==1) this.dir = 2;
			else if (this.dir==2) this.dir=1;
			else if (this.dir==3) this.dir=4;
			else if (this.dir==4) this.dir=3;
			this.amount = this.amount/2;
			this.tempMax = this.tempMax/2;
		}
		
		Micro Merge(Micro o) {
			if(o==null) return this;
			
			if(this.tempMax<o.tempMax) {
				this.dir = o.dir;
				this.tempMax = o.tempMax;
			}
			this.amount += o.amount;
			return this;
		}
		
		void resetMax() {
			this.tempMax = this.amount;
		}
		
	}
	
	static int[] dy = {0,-1,1,0,0};
	static int[] dx = {0,0,0,-1,1};
	static int N;
	static int M;
	static int K;
	
	
	static Micro[][] map;
	static Micro[][] tempmap;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new Micro[N][N];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[y][x] = new Micro(amount, dir);
			}
			for(int t=0; t<M; t++) {
				
				tempmap = new Micro[N][N];
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j]==null) continue;
						
						Micro now = map[i][j];
						now.resetMax();
						int ny = i+dy[now.dir];
						int nx = j+dx[now.dir];
						
						if(ny==0||ny==N-1||nx==0||nx==N-1)	now.edge();
						
						tempmap[ny][nx] = now.Merge(tempmap[ny][nx]);
						
					}
				}
				
				map = tempmap;
			}
			int ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!=null) ans+= map[i][j].amount;
				}
			}
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);

	}

}