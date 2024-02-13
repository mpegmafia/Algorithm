

import sys
input = sys.stdin.readline

N = int(input())

graph = [list(map(int, input().split())) for _ in range(N)]


def combinations (arr, r):
  for i in range(len(arr)):
    if r==1:
      yield [arr[i]]
    
    else:
      for next in combinations(arr[i+1:], r-1):
        yield [arr[i]] + next

teams = [i for i in range(N)]

ans = sys.maxsize
for i in combinations(teams,N//2): #콤비네이션으로 일단 두팀으로 나누기 (중복이 있긴할거임)
  team1 = i
  team2 = [x for x in range(N) if x not in team1]

  team1_score = 0
  team2_score = 0
  for j in range(len(team1)-1):
    for k in range(j+1,len(team1)):
      team1_score += graph[team1[j]][team1[k]]
      team1_score += graph[team1[k]][team1[j]]
  
  for j in range(len(team2)-1):
    for k in range(j+1,len(team2)):
      team2_score += graph[team2[j]][team2[k]]
      team2_score += graph[team2[k]][team2[j]]
  ans = min(ans, abs(team1_score - team2_score))

print(ans)
  
    
