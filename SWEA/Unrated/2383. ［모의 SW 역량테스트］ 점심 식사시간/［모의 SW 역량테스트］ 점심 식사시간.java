import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 계단을 어디로 보낼건지 정해야함
	 * 부분집합으로 팀을 나누어서 true는 1번계단, false는 2번 계단으로 보내기
	 * 계단을 내려가기까지 시간 구하기
	 * 입력:
	 * 	테케 T
	 * 	정사각형 그래프의 크기 NxN
	 * 	N줄동안 그래프 상태 (1은 사람, 2이상부터는 계단)
	 * -------------
	 * 먼저 부분집합으로 팀을 나눈다
	 * 입력받을 때 1인 것들의 좌표를 리스트에 다 담아두기
	 * 그다음 리스트의 크기만큼 부분집합을 돈다
	 * 팀1은 계단1, 팀2는 계단2에 지정하고 각자 계단까지의 거리를 구한다
	 * 만약 계단과의 거리가 0이라면 그 계단의 큐를 확인한다. 3이 안넘었다면 큐에 진입하고 아니라면 대기한다
	 * 	계단을 내려갈 때 인원이 빈다면 바로 들어가줘야 한다.
	 * 	그말은 즉 먼저 큐를 비우고 그다음 움직임을 처리해줘야한다는 의미
	 * 계단을 내려가는 것은 K분이 걸린다. K분 후에, 즉 남아있는 숫자가 0이 되면 빼준다
	 * 그렇게 다 빠지게 되면 시간을 리턴하고 그 시간이 현재 시간보다 작다면 갱신
	 */

	static int N;
	static int[][] graph;
	
	static int[] stair1;
	static int[] stair2;
	static boolean[] picked;
	
	static List<int[]> list;
	static int ans;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			stair1 = null;
			stair2 = null;
			list = new ArrayList<>();
			ans = Integer.MAX_VALUE;
			graph = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] =Integer.parseInt(st.nextToken());
					if (graph[i][j]==1) { //사람일 때
						list.add(new int[] {i, j});
					} else if(graph[i][j]>=2) { // 계단일 때
						if(stair1==null) stair1 = new int[] {i,j};
						else stair2 = new int[] {i,j};
					}
					
				}
			}
			picked = new boolean[list.size()];
			powerSet(0); // 부분집합 ㄱ
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb);
	}
	
	public static void powerSet(int depth) {
		if(depth==list.size()) {
			makeTeam(); // 팀 가르기
			return;
			
		}
		
		picked[depth] = true;
		powerSet(depth+1);
		picked[depth] = false;
		powerSet(depth+1);
	}
	public static void makeTeam() {
		
		int[] gostair1 = new int[picked.length];
		int[] gostair2 = new int[picked.length];
		Arrays.fill(gostair1, -1);
		Arrays.fill(gostair2, -1);
		
		for(int i=0; i<picked.length; i++) {
			if(picked[i]) { //선택됐다면 계단1로 보내기
				gostair1[i] = distance(list.get(i)[0], list.get(i)[1], stair1[0], stair1[1]);
			} else { //아니라면 계단2로 보내기
				gostair2[i] = distance(list.get(i)[0], list.get(i)[1], stair2[0], stair2[1]);
			}
		}
		
		goDown(gostair1, gostair2);
		
	}
	
	public static void goDown(int[] team1, int[] team2) {
		Queue<Integer> pq1 = new ArrayDeque<>();
		Queue<Integer> pq2 = new ArrayDeque<>();
		int cnt = 0; // 내려간 인원
		int time = 1;
		
		while(true) {
//			System.out.println(Arrays.toString(team1));
//			System.out.println(Arrays.toString(team2));
//			System.out.println(pq1);
//			System.out.println(pq2);
//			System.out.println();
			if(time>=ans) return;
			
			int qsize1 = pq1.size();
			int qsize2 = pq2.size();
			
			for(int i=0; i<qsize1; i++){ // 먼저 내려보냄
				if(pq1.peek()==0) {
					pq1.poll();
					cnt++;
				}
				else pq1.offer(pq1.poll()-1);
			}
			
			for(int i=0; i<qsize2; i++){
				if(pq2.peek()==0) {
					pq2.poll();
					cnt++;
				}
				else pq2.offer(pq2.poll()-1);
			}
			
			if(cnt==list.size()) {
				ans = Math.min(ans, time);
				return;
			}
			
			
			
			for(int i=0; i<team1.length; i++) {
				if (team1[i]<=-1) continue; //-1이라면 처리할 것 없음
				if (team1[i]==0) { // 도착했다면? 
					if(pq1.size()>=3) continue;
					else {
						pq1.offer(graph[stair1[0]][stair1[1]]-1);
						team1[i]--;
					}
				} else {
					team1[i]--; //도착 안했다면 하나씩 감소해주기
				}
				
			}
			for(int i=0; i<team2.length; i++) {
				if (team2[i]<=-1) continue; //-1이라면 처리할 것 없음
				if (team2[i]==0) { // 도착했다면? 
					if(pq2.size()>=3) continue;
					else {
						pq2.offer(graph[stair2[0]][stair2[1]]-1);
						team2[i]--;
					}
				} else {
					team2[i]--; //도착 안했다면 하나씩 감소해주기
				}
				
			}
			time++;
		}
		
		
		
		
	}
	
	
	public static int distance(int y, int x, int sy, int sx) {
		return Math.abs(y-sy)+Math.abs(x-sx);
	}

}