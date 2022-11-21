from collections import Counter
def solution(topping):
    answer = 0
    A = set()
    B = Counter(topping)
    for i in topping:
        B[i] -= 1
        A.add(i)
        if B[i] == 0:
            B.pop(i)
        if len(B) == len(A):
            answer += 1
    return answer