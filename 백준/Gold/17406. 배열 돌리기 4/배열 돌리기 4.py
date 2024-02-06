"""
입력:
  배열 A의 크기 NxM 과 회전 연산의 개수 K
  N개의 줄에 걸쳐 배열 A
  K개의 줄에 걸쳐 회전 연산 정보 r,c,s

출력:
  배열 A에서 행의 최솟값
------------------------
기본적으로 브루트포스같음 (N,M이 50x50 이 맥시멈이라)
회전하는 함수 rotate을 구현하고
K개의 연산의 순열을 구현하자(어떤 순서가 더 빠를지 모르므로 모두 다 돌려봐야함)
그다음 최대값을 뱉어내면 된다
"""

import sys
#input = sys.stdin.readline

N,M,K = map(int, input().split())

A = [list(map(int, input().split())) for _ in range(N)]

listK = [list(map(int,input().split())) for _ in range(K)]

def permutations(arr, r):
  for i in range(len(arr)):
    if r==1:
      yield [arr[i]]
    else:
      for next in permutations(arr[:i]+arr[i+1:], r-1):
        yield [arr[i]] + next



def rotate(Ac, ops):
  r,c,s= ops
  r-=1
  c-=1

  mininum = sys.maxsize
  for i in range(1,s+1):
    temp = Ac[r-i][c+i]
    for j in range(i, -i, -1):
      Ac[r-i][c+j] = Ac[r-i][c+j-1]
    for j in range(-i, i, 1):
      Ac[r+j][c-i] = Ac[r+j+1][c-i]
    for j in range(-i, i, 1):
      Ac[r+i][c+j]= Ac[r+i][c+j+1]
    for j in range(i, -i, -1):
      Ac[r+j][c+i] = Ac[r+j-1][c+i]
    Ac[r-i+1][c+i] = temp

  return Ac

min_value = sys.maxsize
for outerop in permutations(listK, K):
  Acopy = [x[:] for x in A]
  for innerop in outerop:
     Acopy = rotate(Acopy, innerop)
  
  for i in range(N):
     min_value = min(min_value, sum(Acopy[i][:]))

print(min_value)
