"""
입력:
  총 10x10에 종이의 상태들이 출력됨

출력:
  모든 1을 덮는데 필요한 색종이의 최소 개수
-------------------------------------
그리디로 풀기엔 반례가 있어서 DP로 풀어야 하는 문제

종이를 담아놓을 graph[10][10]
남아있는 색종이 갯수 paper[5] = [5,5,5,5,5]

1을 발견했을때 거기에 크기에 맞는 모든 종이들을 다 넣는다고 가정하자
(즉, 5x5크기의 1이면 처음 [0,0]에 크기1을 놓았을때와 크기5를 놓았을때까지 전부 재귀적으로 탐사)

이 함수의 종료조건은 모든 그래프를 다돌았을때(다 돌았다는 건 1이 하나도 없다는 의미를 뜻함)
재귀적으로 들어갈때 조건은 해당크기의 남은 색종이가 있을때, 10x10크기 안에서 인덱스가 존재할때
함수의 매개변수는 (y,x,cnt)
"""

import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

graph = [list(map(int, input().split())) for _ in range(10)]

paper = [0 for _ in range(5)] #현재 쓴 색종이

result = 10000
def DFS(y, x, cnt):
  global result
  if y>=10:
    result = min(result, cnt)
    return
  elif x>=10: #if가 아닌 elif로 해줘야 밑에 graph[y][x] 에서 인덱스가 안터짐, 그리고 x>=10 이 넘어갈때 y+1 해주는거 잊지말기(안그럼 1행만 계속반복된다)
    DFS(y+1,0,cnt)

  elif graph[y][x]==1:

    for i in range(5): #최대 색종이크기가 5이므로 5까지 반복
      if y+i>=10 or x+i>=10: #인덱스가 터지면 넘어감
        continue
      if paper[i]>=5: #이미 쓴 색종이 갯수가 5개가 넘어가면 넘어감
        continue
      
      flag = 0 # 플래그 변수를 세워서 플래그가 변하는 순간 최대크기를 넘어간 것이므로 더 안들어감
      for j in range(i+1):  #1의 크기가 몇인지 일단 검사
        for k in range(i+1):
          if graph[y+j][x+k]==0:
            flag = 1
            break
        if flag ==1:break 
      
      if flag == 0:
        for j in range(i+1):
          for k in range(i+1):
            graph[y+j][x+k] = 0 #더 깊이 탐색할거니까 0으로 만들어줌
        paper[i]+=1 #색종이 한장 쓰고 들어감
        DFS(y, x+k, cnt+1) #y+j가 아닌 y임을 명심하자(y는 그대로여야 그 행의 남은x들을 탐색함)
        paper[i] -=1 #재귀에서 빠져나오는 순간 색종이 한장을 다시 빼주고
        
        for j in range(i+1):
          for k in range(i+1):
            graph[y+j][x+k] = 1 #그래프도 원상복구 시켜줌
  else:
    DFS(y,x+1,cnt)

DFS(0,0,0)
print(-1 if result==10000 else result)




