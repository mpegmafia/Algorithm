class Solution {
    public long[] solution(long[] numbers) {
		long[] answer = new long[numbers.length];

		for(int i=0; i<numbers.length; i++) {
			long curnum = numbers[i];
			if(curnum%2==0) {
				answer[i] = curnum+1;

			} else {
				String s = Long.toBinaryString(curnum);
				int target = -1;
				for(int j=s.length()-2; j>=0; j--) { //역순으로 돌기
					if(s.charAt(j)=='0') {
						target = s.length()-j-2; //만약 0을 발견시 해당 자릿수의 2의 제곱을 더해줄 꺼니까 지수 계산해주기
						break;
					}
				}
				if(target==-1) {
					target = s.length()-1; // 다돌았는데도 없다면 맨 앞자리에 해당하는 숫자 더해주
				}
				answer[i] = curnum+(long)Math.pow(2, target);
			}
	
		}
		return answer;
		
    }
}