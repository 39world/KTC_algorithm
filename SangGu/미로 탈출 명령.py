def whereIsExit(position,command):
    global answer, limitCnt, limitX, limitY, moving, target
    
    for dx,dy,commandInput in moving:
    
        if len(command) == limitCnt:            
            if position == target:
                answer.append(command)
            return
        
        x = position[0]+dx
        y = position[1]+dy            
        
        
        if x < 1 or x > limitX:
            return
        
        if y < 1 or y > limitY:
            return
        
        whereIsExit((x,y),command+commandInput)
    

def solution(n, m, x, y, r, c, k):
    global answer, limitCnt, limitX, limitY, moving, target
    limitCnt = k
    limitX = n
    limitY = m
    target = (r,c)
    answer = []
    command = ''
    
    
    position = (x,y)
    moving = ((1,0,'d'),(-1,0,'u'),(0,1,'r'),(0,-1,'l'))
    
    whereIsExit(position,command)
    
    if len(answer) == 0:
        return 'impossible'
    answer.sort()
    return answer[0]