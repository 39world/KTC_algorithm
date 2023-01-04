import math
def solution(n, stations, w):
    answer = 0
    #앞과 뒤를 챙기기 위해..
    stations = [-w] + stations + [n+w+1]
    reach_range = 2*w+1
    for i in range(1,len(stations)):
        #전파범위밖 사이 빈 공간의 수를 구하기 위함
        blank = stations[i] - stations[i-1] - 1 - (2*w)
        # [-1, 4, 11, 13]
        # 4-(-1)-1-(2*1) = 4-2 = 2
        # 11-4-1-(2*1) = 6-2 = 4
        # 13-11-1-(2*1) = -1
        if blank <= 0:
            continue
        blank /= reach_range
        answer += math.ceil(blank)

    return answer