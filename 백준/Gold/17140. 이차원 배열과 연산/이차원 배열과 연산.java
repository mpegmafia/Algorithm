import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] A = new int[101][101];
    static int rowCount = 3, colCount = 3;

    // (숫자, 등장 횟수)를 저장하고 정렬하기 위한 클래스
    static class NumInfo implements Comparable<NumInfo> {
        int num;
        int count;

        NumInfo(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(NumInfo other) {
            if (this.count != other.count) {
                return Integer.compare(this.count, other.count); // 1. 횟수 오름차순
            }
            return Integer.compare(this.num, other.num);       // 2. 숫자 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            if (A[r][c] == k) {
                System.out.println(time);
                return;
            }

            if (rowCount >= colCount) {
                performR();
            } else {
                performC();
            }
            time++;
        }

        System.out.println(-1);
    }

    private static void performR() {
        int newColCount = 0;
        for (int i = 1; i <= rowCount; i++) {
            // 1. 숫자 카운팅 (숫자는 1~100 이므로 배열이 HashMap보다 빠름)
            int[] counts = new int[101];
            for (int j = 1; j <= colCount; j++) {
                if (A[i][j] != 0) {
                    counts[A[i][j]]++;
                }
            }

            // 2. 정렬을 위해 List에 담기
            List<NumInfo> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (counts[num] > 0) {
                    pairs.add(new NumInfo(num, counts[num]));
                }
            }
            Collections.sort(pairs);

            // 3. 정렬된 결과를 배열에 다시 쓰기
            int idx = 1;
            for (NumInfo pair : pairs) {
                if (idx > 100) break;
                A[i][idx++] = pair.num;
                if (idx > 100) break;
                A[i][idx++] = pair.count;
            }
            
            // 4. 나머지 뒷부분은 0으로 채우기
            for (int j = idx; j <= 100; j++) {
                A[i][j] = 0;
            }

            // 5. 최대 열 길이 갱신
            newColCount = Math.max(newColCount, idx - 1);
        }
        colCount = newColCount;
    }

    private static void performC() {
        int newRowCount = 0;
        for (int j = 1; j <= colCount; j++) {
            // 1. 숫자 카운팅
            int[] counts = new int[101];
            for (int i = 1; i <= rowCount; i++) {
                if (A[i][j] != 0) {
                    counts[A[i][j]]++;
                }
            }

            // 2. 정렬을 위해 List에 담기
            List<NumInfo> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (counts[num] > 0) {
                    pairs.add(new NumInfo(num, counts[num]));
                }
            }
            Collections.sort(pairs);

            // 3. 정렬된 결과를 배열에 다시 쓰기
            int idx = 1;
            for (NumInfo pair : pairs) {
                if (idx > 100) break;
                A[idx++][j] = pair.num;
                if (idx > 100) break;
                A[idx++][j] = pair.count;
            }
            
            // 4. 나머지 뒷부분은 0으로 채우기
            for (int i = idx; i <= 100; i++) {
                A[i][j] = 0;
            }

            // 5. 최대 행 길이 갱신
            newRowCount = Math.max(newRowCount, idx - 1);
        }
        rowCount = newRowCount;
    }
}