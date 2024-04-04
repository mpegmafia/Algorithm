import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int ans = 0;
	static int[][] result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
	A:	for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			ans = 0;
			result = new int[6][3]; // 승, 무, 패 순
			
			for(int j=0; j<6; j++) {
				result[j][0] = Integer.parseInt(st.nextToken());
				result[j][1] = Integer.parseInt(st.nextToken());
				result[j][2] = Integer.parseInt(st.nextToken());
				if(result[j][0]+result[j][1]+result[j][2]!=5) {
					sb.append(0+" ");
					continue A;
				}
			}
			int[][] temp = new int[6][3];
			DFS(0, 0, 1, temp);
			
			sb.append(ans+" ");
		}
		System.out.println(sb);
		
		

	}
	
	public static void DFS(int game, int team1,int team2, int[][] map) {
		if(ans==1) return;
		if(game==15) {
			ans = 1;
			return;
		}
		
		map[team1][0]++;
		map[team2][2]++;
//		System.out.println("team 1 : "+team1 + " team 2 : "+team2);
		if(map[team1][0]<=result[team1][0] && map[team2][2]<=result[team2][2]) {
			if(team2+1==6) {
				DFS(game+1, team1+1, team1+2, map);
			} else {
				DFS(game+1, team1, team2+1, map);
			}
		}
		
		map[team1][0]--;
		map[team2][2]--;
		
		map[team1][1]++;
		map[team2][1]++;
		
		if(map[team1][1]<=result[team1][1] && map[team2][1]<=result[team2][1]) {
			if(team2+1==6) {
				DFS(game+1, team1+1, team1+2, map);
			} else {
				DFS(game+1, team1, team2+1, map);
			}
		}
		
		map[team1][1]--;
		map[team2][1]--;
		
		map[team1][2]++;
		map[team2][0]++;
		if(map[team1][2]<=result[team1][2] && map[team2][0]<=result[team2][0]) {
			if(team2+1==6) {
				DFS(game+1, team1+1, team1+2, map);
			} else {
				DFS(game+1, team1, team2+1, map);
			}
		}
		
		map[team1][2]--;
		map[team2][0]--;
		
		
		
	}

}