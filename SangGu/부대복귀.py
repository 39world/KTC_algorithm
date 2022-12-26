
from collections import deque

def makeMap(roads, n):  #지도 그리기
    roadMap = [[] for i in range(n+1)]    
    for start, finish in roads:
        roadMap[start].append(finish)
        roadMap[finish].append(start)    
    return roadMap

def findRoute(roadMap,start,shortMap): ## 도착 지점에서의 최단 거리 계산
    shortMap[start] = 0
    que = deque([start])
    while que:
        start = que.popleft()
        step = shortMap[start]+1
        for nextPoint in roadMap[start]:
            if shortMap[nextPoint] > step:
                shortMap[nextPoint] = step
                que.append(nextPoint)    
    return shortMap

def solution(n, roads, sources, destination):
    answer = []
    roadMap = makeMap(roads,n)        
    shortMap = [100001] * (n+1)
    
    shortMap = findRoute(roadMap,destination,shortMap)
    for start in sources:
        if shortMap[start] == 100001:
            answer.append(-1)
        else:
            answer.append(shortMap[start])
    return answer
