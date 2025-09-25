import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 1. 도로(간선) 정보를 저장할 클래스 (비용순 정렬을 위해 Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] parent; // Union-Find를 위한 부모 노드 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        long totalCost = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            totalCost += c;
            edges.add(new Edge(a, b, c));
        }

        // --- 크루스칼 알고리즘 시작 ---
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i; // 자기 자신을 부모로 초기화
        }

        // 2. 모든 도로를 비용순으로 오름차순 정렬
        Collections.sort(edges);

        long minCost = 0;
        int edgeCount = 0;

        // 3. 비용이 가장 낮은 도로(간선)부터 순회
        for (Edge edge : edges) {
            // 4. 두 건물이 아직 연결되지 않았다면 (사이클을 만들지 않는다면)
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to); // 두 건물을 연결
                minCost += edge.cost;
                edgeCount++;
            }
        }

        // 5. 모든 건물이 연결되었는지 확인 (선택된 간선이 N-1개인지)
        if (edgeCount == N - 1) {
            System.out.println(totalCost - minCost);
        } else {
            System.out.println(-1);
        }
    }

    // Union-Find: x의 루트(대표) 노드 찾기
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // 경로 압축: find를 하면서 만나는 모든 노드의 부모를 루트로 직접 연결
        return parent[x] = find(parent[x]);
    }

    // Union-Find: 두 그룹 합치기
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 더 작은 번호의 루트를 부모로 삼는 관례
            if (rootX < rootY) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
            }
        }
    }
}