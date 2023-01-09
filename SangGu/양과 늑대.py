
def makeMap(edges,n): ## 단방향 지도 만들기
    aniMap = [[] for i in range(n)] 
    for a,b in edges:
        aniMap[a].append(b)
    return aniMap

def gatcha(info,aniMap,sheep,wolf,aniIdx,nextStep): ##방문하지 않은 위치를 모두 방문하도록 리스트에 담아서 같이 보내기
    global answer
    
    if info[aniIdx] == 0:
        sheep += 1
        if answer < sheep :
            answer = sheep
    else:
        wolf += 1

    if wolf < sheep:   
        nextStep.extend(aniMap[aniIdx])

        for tempIdx in nextStep:
            gatcha(info,aniMap,sheep,wolf,tempIdx,[i for i in nextStep if i != tempIdx])

def solution(info, edges):
    global answer
    answer = 0
    sheep = 0
    wolf = 0
    nextStep = []
    aniMap = makeMap(edges,len(info)) 
    
    gatcha(info,aniMap,sheep,wolf,0,nextStep)
    return answer