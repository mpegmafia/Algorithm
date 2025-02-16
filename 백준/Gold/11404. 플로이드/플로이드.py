"""
입력:
    도시의 개수 N
    버스의 개수 M
    M개의 줄동안 a,b,c(출발도시, 도착도시, 비용)
출력:
    N개의 줄 출력
    i번째 줄에 출력하는 j번째 숫자는 i에서 j로가는데 최소비용, 만약 갈 수 없을 경우 0출력
--------------
모든점에서 모든점 : 플로이드 알고리즘
플로이드 알고리즘은 j, i, k순서대로(경유, 출발, 도착)

"""
import sys
input = sys.stdin.readline

N = int(input())
M = int(input())

edges = [[10000000]*(N+1) for _ in range(N+1)] #먼저 거리값들을 최소값으로 받기위해 초기값을 1000000으로 설정해줌

for _ in range(M):
    a,b,c = map(int, input().split())
    edges[a][b] = min(edges[a][b], c)

for i in range(1,N+1):
    edges[i][i] = 0

for j in range(1,N+1): #교차지점부터 순회
    for i in range(1,N+1):
        for k in range(1,N+1):
            if edges[i][k] > edges[i][j]+edges[j][k]:
                edges[i][k] = edges[i][j]+edges[j][k]

for i in range(1,N+1): #인덱스 항상 유의하기(1부터 시작하는지 어케하는지 이런거)
    for j in range(1,N+1):
        print(0 if edges[i][j] == 10000000 else edges[i][j], end= ' ')
    print()



