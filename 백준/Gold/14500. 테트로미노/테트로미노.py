"""
입력:
  N, M (행, 열)
  N줄동안 graph의 형태 입력

출력:
  테트리스가 놓인 칸의 최댓값
--------------------------
처음엔 모든 테트리스의 모양을 만든후 브루트포스를 돌려야 하는건가 싶었는데
그러면 코드가 너무너무길어져서 구글링을 해보니 
한 점을 기준으로 상하좌우 모두 DFS를 돌리면 ㅜ 모양의 테트리스 빼고 전부 접근이 가능하다는 것을 알아냈다(이건 어케알지)
즉 칸이 4개가 될때까지 상하좌우로 DFS를 돌린다면 모든 테트리스의 모양을 대입할수있다!
visited와 인덱스에 유의하자
"""

import sys
input = sys.stdin.readline

N, M = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(N)]
maxv = 0

dy = [0,1,0,-1]
dx = [1,0,-1,0]

visited = [[False]*M for _ in range(N)]

def tetris(y,x,cnt,sum):
  global maxv

  if cnt==4:
    maxv = max(maxv, sum)
    return
  
  for i in range(4):
    ny = y + dy[i]
    nx = x + dx[i]
    
    if 0<=ny<N and 0<=nx<M and visited[ny][nx] == False:
      cnt+=1
      sum+=graph[ny][nx]
      visited[ny][nx] = True
      tetris(ny,nx,cnt,sum)
      visited[ny][nx] = False
      cnt -=1
      sum -= graph[ny][nx]

def Fshape(y,x):
  global maxv
  for i in range(4):
    
    tmp = graph[y][x]
    
    for j in range(3):
      index = (i+j)%4
      ny = y + dy[index]
      nx = x + dx[index]
      if not 0<=ny<N or not 0<=nx<M:
        tmp = 0
        break
      tmp += graph[ny][nx]
    
    maxv = max(maxv, tmp)


for i in range(N):
  for j in range(M):
    visited[i][j] = True
    tetris(i,j,1,graph[i][j])
    visited[i][j] = False

    Fshape(i,j)

print(maxv)
