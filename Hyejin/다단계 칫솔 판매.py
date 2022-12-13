def solution(enroll, referral, seller, amount):
    answer = [0] * len(enroll)
    #인덱스 dic형태로 찍어두기
    enroll_idx = {enroll[i]: i for i in range(len(enroll))}
    for i in range(len(seller)):
        money = amount[i] * 100
        idx = enroll_idx[seller[i]]
        while money > 0:
            percent_10 = money // 10
            answer[idx] += money - percent_10
            money = percent_10
            if referral[idx] == "-":
                break
            idx = enroll_idx[referral[idx]]
    return answer