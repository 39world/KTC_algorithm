## 프로그래머스 Lv.3 다단계 칫솔 판매
def solution(enroll, referral, seller, amount):
    answer = []
    bookDic = {} #장부
    referralDic = dict(zip(enroll,referral)) #String 비교를 위해
    
    for name in enroll:
        bookDic[name] = 0
    
    for name, money in zip(seller, amount):
        bookDic = payMoney(name,money*100,referralDic,bookDic)
    return list(bookDic.values())

def payMoney(name, money, referralDic, bookDic):
    if money >= 10:
        if int(money*0.1) != money*0.1:
            profit = money-int(money*0.1)
        else:
            profit = money*0.9
        bookDic[name] = bookDic[name]+profit
        if referralDic[name] == "-":
            return bookDic
        return payMoney(referralDic[name],money-profit,referralDic, bookDic)
    else:
        bookDic[name] = bookDic[name]+money
        return bookDic