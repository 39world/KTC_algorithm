def solution(n, m, x, y, r, c, k):
    answer = ''
    x_diff = x-r
    y_diff = y-c

    #최소거리가 k보다 큰 경우
    if abs(x_diff) + abs(y_diff) > k:
        return 'impossible'
    else:
        while abs(x-r) + abs(y-c) < k:
            if x < n:
                answer += 'd'
                x += 1
            elif y > 1:
                answer += 'l'
                y -= 1
            else:
                answer += 'r'
                y += 1
            k -= 1
        answer += 'd' * (r-x) + 'l' * (y-c) + 'r' * (c-y) + 'u' * (x-r)

        return answer