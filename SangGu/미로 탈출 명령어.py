def solution(n, m, x, y, r, c, k):
    global answer, limitCnt, limitX, limitY, moving, target

    target = [r,c]
    answer = 'impossible'
    moving = [[-1,0,'u'],[0,1,'r'],[0,-1,'l'],[1,0,'d']]
    stack = [[x,y,'']]

    while stack:
        positionX,positionY,command = stack.pop()
        if len(command) == k and [positionX,positionY] == target :
                answer = command
                break

        resource = k-len(command)
        shortCut = abs(positionX-r)+abs(positionY-c)

        if resource < shortCut or resource %2 != shortCut%2:
            continue

        for dx,dy,commandInput in moving:
            nextX = positionX+dx
            nextY = positionY+dy

            if nextX < 1 or nextX > n:
                continue
            if nextY < 1 or nextY > m:
                continue

            stack.append([nextX,nextY,command+commandInput])    

    return answer