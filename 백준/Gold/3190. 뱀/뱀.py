"""
입력:
  N (graph의 크기 NxN)
  사과의 갯수 K
  K줄동안 사과의 위치(행, 열)
  뱀의 방향전환 횟수 L
  L줄동안 정수 X와 문자 C (L은 왼쪽 D는 오른쪽)

출력:
  게임이 몇초에 끝나는지 출력
--------------------------------
그래프 탐색 문제같음
먼저 뱀이 위치하는 곳을 그래프에서 1로 만들고 사과를 먹을시 1을 늘어나게 해줌
게임이 끝나는 조건은 움직일때 벽의 끝에 도달했거나(인덱스가 넘거나) 자기 몸을 만났을때(1을 만났을때)
L에서 정수와 문자가 동시에 나오는건 어떻게 처리할 것인지 -->> 람다함수로 해결완료 ㅋ
회전 메커니즘은 어떻게 할 것인지에 대한 고민 필요
"""
import sys
from collections import deque
input = sys.stdin.readline

N = int(input())
K = int(input())
graph = [[0]*N for _ in range(N)]
for _ in range(K):
  r, c = map(int,input().split())
  graph[r-1][c-1] = 2  #사과는 2
L = int(input())
XC = [list(map(lambda x:int(x) if x.isdigit() else x, input().split())) for _ in range(L)]


sec = 0

dir = [[0,1],[1,0],[0,-1],[-1,0]] #오른쪽으로 움직이는건 +1 왼쪽으로 움직이는건 -1
curdir = 0 
q = deque()       #뱀의 몸통 좌표 상황

graph[0][0] = 1 # [0][1]에서 바로 사과를 먹을수도 있으므로 1로 초기화해줘야함
q.append((0,0))

ci, cj = 0, 0
while True :
  sec+=1
  ci += dir[curdir][0]
  cj += dir[curdir][1]
  if not 0<=ci<N or not 0<=cj<N:
    break
  if graph[ci][cj] == 0:
    graph[ci][cj] = 1
    q.append((ci,cj))
    bi, bj = q.popleft()
    graph[bi][bj] = 0
  
  elif graph[ci][cj] == 2:
    graph[ci][cj] = 1
    q.append((ci,cj))
  
  else: break
  
  for x in XC:
    if sec in x:
      if x[1]=='D':
        curdir = (curdir+1) %4
      elif x[1] =='L':
        curdir = (curdir-1) %4
  
print(sec)