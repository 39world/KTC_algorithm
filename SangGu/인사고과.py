def solution(scores):
    answer = 0
    target = sum(scores[0])
    targetList = []
    targetList.append(target)
    
    if any(score[0] > scores[0][0] and score[1] > scores[0][1] for score in scores): #완호점수 확인
        return -1
    scores.sort(key=lambda x:(-x[0],x[1]))
    
    lowScore = 0 #못받는 사람 제외해
    for score in scores:
        if lowScore <= score[1]: 
            lowScore = score[1]
            if sum(score) > target:
                answer += 1     
        
    return answer+1 