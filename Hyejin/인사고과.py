def solution(scores):
    answer = 1
    wanho = scores[0]
    wango_sum = sum(scores[0])
    scores.sort(key=lambda x: (-x[0], x[1]))
    before = 0
    for score in scores:
        #완호가 인센티브를 받지 못하는 경우
        if wanho[0] < score[0] and wanho[1] < score[1]:
            return -1
        #완호 외 인센티브 못 받는 사람 제외 필터
        if before <= score[1]:
            #완호보다 합산 점수가 높은 사람 세기
            if wango_sum < score[0] + score[1]:
                answer += 1
            before = score[1]
    return answer