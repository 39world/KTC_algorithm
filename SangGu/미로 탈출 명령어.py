## 메모리 문제가 있는 것 같다.
def whereIsExit(position,command):
    global answer, limitCnt, limitX, limitY, moving, target
    
    for dx,dy,commandInput in moving:
        
        if len(answer) != 0:
            return
        
        if len(command) == limitCnt:  
            if position == target:
                answer.append(command)
            return
        
        resource = limitCnt-len(command)
        shortCut = abs(position[0]-target[0])+abs(position[1]-target[1])
        
        if resource < shortCut :
            return
        
        x = position[0]+dx
        y = position[1]+dy                    
        
        if x < 1 or x > limitX:
            continue
        if y < 1 or y > limitY:
            continue
        
        whereIsExit([x,y],command+commandInput)
    return   

def solution(n, m, x, y, r, c, k):
    global answer, limitCnt, limitX, limitY, moving, target
        
    limitCnt = k
    limitX = n
    limitY = m
    target = [r,c]
    answer = []
    position = [x,y]
    moving = [[1,0,'d'],[0,-1,'l'],[0,1,'r'],[-1,0,'u']]
    command = ''
    whereIsExit(position,command)

    if len(answer) == 0:
        return 'impossible'
    
    return answer[0]

