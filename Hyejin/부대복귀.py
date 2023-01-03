def solution(n, roads, sources, destination):
    matrix = [[] for _ in range(n+1)]
    ##매트릭스 생성
    for a, b in roads:
        matrix[a].append(b)
        matrix[b].append(a)
    print(matrix)
    ##처음은 경로 없음으로 세팅, destination은 0으로 세팅
    road_map = [-1] * (n+1)
    road_map[destination] = 0
    queue = [(destination, 0)]

    while queue:
        q = queue.pop(0)
        for i in matrix[q[0]]:
            print(i)
            if road_map[i] == -1:
                queue.append((i, q[1]+1))
                road_map[i] = q[1]+1
    return [road_map[j] for j in sources]