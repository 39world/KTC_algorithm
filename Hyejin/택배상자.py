#시간 초과 70/100
# def solution(order):
#     answer = 0
#     container=[]
#     for i in range(1,len(order)+1):
#         container.append(i)
#         while container and order[0] == container[-1]:
#             answer+=1
#             container.pop()
#             order.pop(0)
#     return answer

from collections import deque

def solution(order):
    order=deque(order)
    answer = 0
    container=[]
    for i in range(1,len(order)+1):
        container.append(i)
        while container and order[0] == container[-1]:
            answer+=1
            container.pop()
            order.popleft()
    return answer