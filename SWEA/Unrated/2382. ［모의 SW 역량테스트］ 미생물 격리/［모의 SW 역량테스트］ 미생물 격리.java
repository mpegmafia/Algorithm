import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * K개의 미생물 NxN 그래프
	 * 가장자리는 약품이 칠해져있음 (0 or N-1)
	 * 가장자리 도착시 군집내 미생물의 절반이 죽고 이동방향이 반대로 바뀐다
	 * 홀수라면 소수점이하를 버린다
	 * 만약 남아있는 미생물수가 0이 된다면 죽음
	 * 미생물들은 합쳐질때 가장 큰거 기준으로 방향이 정해짐
	 * M시간동안 움직인후 남아있는 미생물수의 총합을 구하기
	 * ------------------------------
	 * 3의 보수 0,1,3,2 방향
	 * 리스트가 비어있기 전까지 M번 움직이기
	 * 리스트 사이즈만큼만 움직여야함
	 * 미생물을 리스트에 담아서 매번 정렬함
	 * list.remove(0)을 하면 그 객체가 반환되면서 사라진다.
	 * 한번 움직일때마다 그래프를 새로 생성
	 * 움직인 후 거기가 경계인지 아닌지 체크
	 * 경계라면 방향 반대, 군집수 /2
	 * 만약 군집수가 0보다 크다면 그 좌표에 객체 집어넣기
	 * 큰거기준으로 움직이니 만약 움직이려는 좌표에 미생물이 원래있다면 걔방향그대로 합쳐지기만 하면 됨
	 * 
	 * 미생물 클래스 만들기
	 * 현재 방향대로 움직이기
	 * 움직일때 테두리처리 해주고 만약 남은게 0이상이면 그래프에 넣고 리스트에도 다시 넣기
	 * 합쳐질때는 기존객체에 현재 움직이는 객체를 넣어서 추가해주기
	 * 
	 */

	static int[] dy = {-1,0,0,1};
	static int[] dx = {0,1,-1,0};
	static int ans;
	static int N, M, K; // 그래프 크기 N, 격리시간 M, 미생물 군집 개수 K
	static List<Microbe> list; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			
			for(int i=0; i<K; i++) {
				st =new StringTokenizer(br.readLine());
				int r= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				int micro= Integer.parseInt(st.nextToken());
				int dir= Integer.parseInt(st.nextToken());
				if(dir==1) {
					dir=0;
				}
				else if(dir==2) {
					dir=3;
				}
				else if(dir==3) {
					dir=2;
				}
				else if(dir==4) {
					dir=1;
				}
				list.add(new Microbe(r, c, micro, dir));
			}
			
			for(int i=0; i<M&&list.size()>0; i++) {
				int size = list.size();
				Collections.sort(list);
				Microbe[][] map = new Microbe[N][N];
				
				for(int j=0; j<size; j++) {
					Microbe m = list.remove(0);
					if(!m.move()) continue; //사라진다면 패스
					
					if(map[m.r][m.c]==null) { //만약 그래프가 비어있다면 
						map[m.r][m.c] = m;
						list.add(m); //처음놈이니까 리스트에 추가
					} else {
						map[m.r][m.c].merge(m);//합병
					}
				}
			}
			for(Microbe m: list) {
				ans+=m.micro;
			}
			sb.append("#"+tc+" "+ans+"\n");
			
		}
		System.out.println(sb);
		

	}
	
	static class Microbe implements Comparable<Microbe>{
		int r, c, micro, dir;

		public Microbe(int r, int c, int micro, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.micro = micro;
			this.dir = dir;
		}
		
		public boolean move() {
			int nr = this.r+dy[dir];
			int nc = this.c+dx[dir];
			if(nr==0||nr==N-1||nc==0||nc==N-1) {
				this.micro /= 2;
				this.dir = 3-this.dir;
			}
			this.r = nr;
			this.c = nc;
			if(this.micro>0) return true;
			else return false;
			
		}
		
		public void merge(Microbe o) {
			this.micro += o.micro;
		}

		@Override
		public int compareTo(Microbe o) {
			return Integer.compare(o.micro, this.micro);
		}

		@Override
		public String toString() {
			return "Microbe [r=" + r + ", c=" + c + ", micro=" + micro + ", dir=" + dir + "]";
		}
		
		
	}
	
	

}