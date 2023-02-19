def solution(storey):
    answer = 0

    while storey:
        x = storey % 10
        # x가 5이고 십의 자리가 5보다 작을경우 내리는게 지름길
        if x < 5 or (x == 5 and storey % 100 < 50):
            answer += x
        else:
            answer += 10 - x
            storey += 10
        storey //= 10

    return answer