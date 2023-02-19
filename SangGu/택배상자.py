def solution(order):
    answer = 0
    stack = []
    orderIdx = 0
    container = [i for i in range(1,len(order)+1)]

    for box in container:
        
        stack.append(box) #컨테이너 상자 하나 바구니에 넣기
        
        while stack and stack[-1] == order[orderIdx]: #바구니가 비거나 내가 원하는 상자가 아닐때까지
            orderIdx += 1
            stack.pop()
            answer += 1
            
    return answer