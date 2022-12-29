def solution(cards):
    answer = []
    for i in range(0,len(cards)):
        total = []
        while cards[i] not in total:
            total.append(cards[i])
            i = cards[i] - 1          #리스트와는 다르게 1번부터 시작이기 때문에
        if sorted(total) not in answer:
            answer.append(sorted(total))
        else:
            answer.append([])        #1번상자만 있고 2번상자는 없을 경우
    answer.sort(key=lambda x : len(x))
    return len(answer[-1]) * len(answer[-2])