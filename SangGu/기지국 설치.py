import math

def solution(n, stations, w):
    #1번 아파트부터 하나하나 체크시 시간초과
    answer = 0
    sigRange = 2*w+1 #기지국 전파 범위
    for stIdx in range(len(stations)):
        
        if stIdx == 0: #첫번째 기지국으로 1번 아파트 전파 도달 여부 확인
            noSig = (stations[stIdx]-w) -1
            if noSig <= 0:
                continue
            answer += math.ceil(noSig/sigRange)

        else: #기지국과 기지국 사이에 전파가 도달하지 않는 아파트 확인 후 설치
            noSig = (stations[stIdx]-w) - (stations[stIdx-1]+w) -1
            if noSig < 0:
                continue
            else: 
                answer += math.ceil(noSig/sigRange)
                
    if stations[-1] < n-w: #마지막 기지국과 마지막 아파트 전파 도달 여부 확인
        noSig = n - (stations[-1]+w)
        if noSig > 0:
            answer += math.ceil(noSig/sigRange)

    return answer