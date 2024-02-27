"""
입력:
    정점의 개수 V, 간선의 개수 E
    시작 정점의 번호 K
    E개의 줄에 걸쳐 각 간선을 나타내는 u,v,w(시작, 끝, 비용)
출력:
    i번째 줄에 K에서 i번 정점으로 가는 최단경로의 비용
    만약 자기자신일시 0, 존재하지 않는경우 INF 출력
"""
import sys
import heapq
input= sys.stdin.readline


V, E = map(int, input().split())
K = int(input())
edges = [[] for _ in range(V+1)] #한점 기준이니까 모든 경로값을 다 넣어준다

for _ in range(E):
    u, v, w = map(int, input().split())
    edges[u].append([w,v]) # 힙에 들어갈 것이므로 w먼저 넣어주는게 뽀인트

dist = [sys.maxsize]*(V+1) # K를 기준으로한 거리값 배열

dist[K] = 0  # 자기자신은 0 으로 초기화

heap = [[0,K]] #한세트니까 두번묶어줘야함

while heap:
    cw, cv = heapq.heappop(heap)
    for nw, nv in edges[cv]:
        if dist[nv] > nw+cw: #그냥 nw가 아닌 nw+cw 가 뽀인트다 왜냐하면 계속 힙을 타고 들어가기 때문에
            dist[nv] = nw+cw #그냥 nw만 해주면 값이 더 작게나옴(n번거치는데 마지막 거치는 비용만 들어가기떄문)
            heapq.heappush(heap, [dist[nv],nv]) #dist[nv]가 바뀌었으므로 그 값을 비용으로 heap에 넣어줘야함

for i in range(1,V+1):
    if dist[i] == sys.maxsize:
        print("INF")
    else:
        print(dist[i])
