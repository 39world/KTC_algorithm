## 프로그래머스 롤케이크 자르기

def solution(topping):
    answer = 0          
    topList = []
    rvsTopList = []
    
    nmCnt = 0  
    nmList = set(topping)
    for i in range(len(topping)):
        if topping[i] in nmList:
            nmList.remove(topping[i])
            nmCnt+=1
        topList.append(nmCnt)
        
    nmCnt = 0 
    nmList = set(topping)
    for i in range(1,len(topping)+1):
        if topping[-i] in nmList:
            nmList.remove(topping[-i]) 
            nmCnt+=1
        rvsTopList.append(nmCnt)   
        
    for i in range(len(topping)-1):
        if topList[i] == rvsTopList[-i-2]:
            answer+=1
    
    return answer