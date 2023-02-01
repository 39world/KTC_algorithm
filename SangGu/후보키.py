from itertools import combinations
def solution(relation):
    answer = []
    attriLen = len(relation[0])
    tmpArray = []
    for i in range(attriLen):
        tmpArray.append(i)
    for i in range(1,attriLen+1): 
        combinationArray = combinations(tmpArray,i)
        for combi in combinationArray:
            
            if any(set(ans).issubset(combi) for ans in answer):  #부분집합인가요?
                continue
                
            target = [[] for i in range(len(relation))]      
            for j in range(attriLen):
                if j in combi:
                    for k in range(len(relation)):
                        target[k].append(relation[k][j])
            for k in range(len(relation)):
                target[k] = tuple(target[k])
                
            if len(target) == len(set(target)):                
                answer.append(combi)
                
    return len(answer)