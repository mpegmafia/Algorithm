"""
입력:
  1번톱니바퀴 (N극은 0, S극은 1)
  2번톱니바퀴
  3번톱니바퀴
  4번톱니바퀴 
  회전횟수 K
  K개의 줄동안 회전시킨 톱니바퀴의 번호와 방향이 주어짐(1은 시계방향, -1은 반시계방향)
출력:
  다 회전시킨후 톱니바퀴 접수의 합
  N극일 경우 0점, S극일 경우 1번부터 1점, 2점, 4점, 8점
----------------------------------------------------
각 톱니의 인덱스는 0부터 7까지, i번의 인덱스2와 i+1번의 인덱스6은 연결되어있음
DFS로 들어갈때 +1과 -1 방향으로 나눈뒤 방향에 따라 인덱스 2와 6을 넘겨준뒤 회전체크를 하면될듯?
"""

import sys
input = sys.stdin.readline

def rotate(arr,dir):
  if dir == 1:
    arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7] = arr[7], arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]
    return arr
  else:
    arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7] = arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[0]
    return arr


def DFS(depth, dir):
  visited[depth] = True
  if depth+1 <=3 and not visited[depth+1]:
    if a[depth][2] != a[depth+1][6]:
      if dir==1:
        DFS(depth+1,-1)
      else:
        DFS(depth+1,1)
  if depth-1 >=0 and not visited[depth-1]:
    if a[depth][6] != a[depth-1][2]:
      if dir==1:
        DFS(depth-1,-1)
      else:
        DFS(depth-1,1)
  a[depth] = rotate(a[depth],dir)
  return

a=[]
for i in range(4):
  a.append([x for x in input()]) #일단 각각 원소를 a란 리스트에 다 때려박음
  
K = int(input())

order = [list(map(int, input().split())) for _ in range(K)]


for num, dir in order: #명령 인자들을 순회하면서 하나씩 실행해줌
  visited = [False]*4  #각 명령들을 돌때마다 visited는 초기화
  DFS(num-1, dir)

ans = int(a[0][0]) * 1 + int(a[1][0]) * 2 + int(a[2][0]) * 4 + int(a[3][0]) * 8



print(ans)