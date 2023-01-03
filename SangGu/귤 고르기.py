from collections import Counter

def solution(k, tangerine):
    
    gyulDic = Counter(tangerine)  #귤 크기에 따라 귤 분리
    gyulValues = list(gyulDic.values()) #귤 개수 값만 가져오기
    gyulValues.sort(reverse=True) #거꾸로 줄세우기
    gyulCnt = 0
    while k > 0: #원하는 귤 양이 될 때까지 많은 수부터 카운트
        k -= gyulValues[gyulCnt]
        gyulCnt += 1
        
    answer = gyulCnt
    return answer