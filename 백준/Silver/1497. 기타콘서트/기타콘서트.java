import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 기타의 개수 N
	 * 곡의 개수 M
	 * 레벨별 조합?
	 * 1개씩 뽑기
	 * 2개씩 뽑기
	 * ... N개 뽑기
	 * 조합을 뽑은 후 모든 곡을 연주 할 수 있는지 체크
	 * 만약 연주할 수 있다면 바로 출력후 리턴
	 */

	static int N;
	static int M;
	static List<String> songs;
	static int[] isSelected;
	static int ans = -1;
	static int anssong = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		songs = new ArrayList<>(N); //i번째 기타가 연주할 수 있는 노래 모음
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			songs.add(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			isSelected = new int[i]; //i개만큼 고르기
			combi(0, 0, i);
		}
		System.out.println(ans);
		
	}
	private static void combi(int depth, int start, int max) {
		if(depth==max) { //갯수만큼 다 골랐다면? 
			chk(max);
			return;
		}
		
		for(int i=start; i<N; i++) { // 기타 고르기
			isSelected[depth] = i;
			combi(depth+1, i+1, max);
		}
		
		
		
		
	}
	
	static void chk(int max){
		int cnt = 0;
		boolean [] visited = new boolean[M];
		for(int song : isSelected) {
			String s =songs.get(song);
			for(int i=0; i<M; i++) {
				if(visited[i]||s.charAt(i)=='N') continue;
				visited[i] = true;
				cnt++;
			}
		}
		if(cnt>anssong) { //max개의 기타로 cnt개의 송을 연주 가능함
			anssong = cnt;
			ans = max;
		}
		if (cnt==M) {
			System.out.println(max);
			System.exit(0);
		}
		
		
	}

}