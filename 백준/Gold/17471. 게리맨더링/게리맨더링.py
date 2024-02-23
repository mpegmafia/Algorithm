"""
입력:
  구역의 개수 N
  구역의 인구가 1번부터 N번까지 주어짐(한줄)
  3째줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어짐 (첫번째 정수는 그 구역과 인접한 구역의수, 이후 인접한 구역의 정보)

출력:
  도시를 두 구역으로 나눴을 때 두 선거구의 인구차이의 최솟값, 만약 나눌 수 없는 경우 -1을 출력
----------------------------------------
조합을 이용해 N개의 도시를 2개로 나눠 N//2 +1만큼 선택해준다
그 후 나눈 구역 두개를 BFS를 돌면서 이어져있는지 확인해준다
만약 이어져있다면 그 두개의 구역의 인구수의 차이를 return
----------------------------------------
구역의 개수 N
구역의 인구 리스트 population
0부터 N-1까지 각 도시별 인접리스트

조합으로 N//2+1개의 도시를 선택했을때 나머지 선택안된 도시리스트
BFS를 돌 때 방문여부를 체크하는 visited 리스트
"""
import sys
from collections import deque
input = sys.stdin.readline

N = int(input())

population = list(map(int, input().split()))

graph = [[] for _ in range(N)]


for i in range(N):
  city = list(map(int, input().split()))
  for j in city[1:]:
    graph[i].append(j-1)

def combinations(arr, r):
  for i in range(len(arr)):
    if r==1:
      yield [arr[i]]
    else:
      for next in combinations(arr[i+1:], r-1):
        yield [arr[i]] + next

def BFS(cities): #연결되어있는지 여부를 체크
  visited = [0]*N
  q = deque()
  q.append(cities[0])
  visited[cities[0]] = 1
  while q:
    c = q.popleft()
    connected = graph[c]
    for city in connected:
      if visited[city]==0 and city in cities: #여기를 visited[city] 만 체크하니까 오류가 났던것임, cities안에도 있는지 확인해줘야했음
        visited[city]=1
        q.append(city)
  for check in cities:
    if visited[check] == 0:
      return False
  return True


difference = sys.maxsize

for i in range(1, N//2+1): #여기를 이렇게해줘야 하나씩 선택해주는게 처음부터 끝까지감(예전 combinations(range(N),N//2+1)은 코드가 이상하게 돌아갔음)
    selected_list = combinations(range(N),i)
    for selected in selected_list:
      rest = [x for x in range(N) if x not in selected] #이거좀 중요한 코드일수도 ㅋ
      Asum=0
      Bsum=0
      if BFS(selected) and BFS(rest): #만약 둘다 TRUE(연결되어있음)을 리턴할때
        for A in selected:
          Asum += population[A]
          
        for B in rest:
          Bsum += population[B]
        difference = min(difference, abs(Asum-Bsum))
        
        #print(selected, rest, difference)
      

if difference == sys.maxsize:
  print(-1)
else: print(difference)
