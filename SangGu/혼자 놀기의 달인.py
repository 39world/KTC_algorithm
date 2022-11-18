## 프로그래머스 LV.2 혼자 놀기의 달인
def solution(cards):
    answer = 0    
    for i in range(len(cards)):
        boxNum = i
        cnt = 0
        openList = [0]*(len(cards))
        while 1:
            if openList[boxNum] == 1:
                firstPoint = cnt     
                break
            else: 
                openList[boxNum] = 1
                boxNum = cards[boxNum]-1
                cnt += 1           
        for j in range(len(cards)):            
            secondOpenList = openList
            boxNum = j
            cnt = 0
            while 1:                                                  
                if secondOpenList[boxNum] == 1:
                    score = firstPoint * cnt
                    if answer < score:
                        answer = score
                    break
                else:
                    secondOpenList[boxNum] = 1
                    boxNum = cards[boxNum]-1
                    cnt += 1 
    return answer