def solution(storey):
    answer = 0
    
    while storey:
        
        endPoint = storey % 10
        
        if endPoint > 5:  #위로 ? 아래로 ? 
            answer += 10-endPoint     
            storey += 10
            
        elif endPoint < 5:
            answer += endPoint
        
        else: #같을 땐 다음 숫자 확인
            if (storey//10) % 10 > 4: 
                storey += 10
            answer += 5
            
        storey = storey//10      
        
    return answer