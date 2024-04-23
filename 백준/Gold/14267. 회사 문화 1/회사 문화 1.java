import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 직속상사와 직속부하
	 * 배열 N개만있으면됨
	 * n,m은 최대 10만개
	 * 그냥 포문을 돌면 절대 못함
	 * 일단 m개의 명령어를 처음에 배열에 집어넣기
	 * 그다음 처음무터 순차적으로 값이 0이 아닌애들을 찾기
	 * 0이 아니라면 그것에 해당하는 직속부하의 값을 더하기
	 * 이렇게 순차적으로만 돌면 최대 20만번?정도에 끝남
	 * -> 직속 부하가 아닌 직속상사가 주어진다는 것은 직속부하가 여러명일수도 있다는 뜻이다.
	 * -> 직속 부하를 담은 리스트와 칭찬받은 값의 배열을 동시에 돌기 
	 */

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		for(int i=2; i<=N; i++) { //첫 값은 무조건 -1 이므로 if문보단 시간을 위해 2부터 돌기
			int a = Integer.parseInt(st.nextToken()); //i의 직속상사는 a라는뜻
			list[a].add(i); //a-1 의 직속부하는 i라는 뜻
		}
		
		int[] compl = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int emp = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			compl[emp] += w;
		}
		
		int[] ans = new int[N+1];
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(compl[i]==0) {
			}
			else {
				ans[i]+=compl[i];
				for(int emp : list[i]) {
					compl[emp] += compl[i];
				}
			}
			sb.append(compl[i]+" ");
			
		}
		System.out.println(sb);
		
	}

}