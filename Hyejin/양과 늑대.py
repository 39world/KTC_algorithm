def solution(info, edges):
    visited = [0] * len(info)
    answer = []

    ##양이 늑대보다 많을 때마다 answer리스트에 저장함
    def dfs(sheep, wolf):
        if sheep > wolf:
            answer.append(sheep)
        else:
            return

        for parent, child in edges:
            #부모노드는 방문했지만 자식노드는 방문전일때
            if visited[parent] and not visited[child]:
                visited[child] = 1
                if info[child] == 0:
                    dfs(sheep+1, wolf)
                else:
                    dfs(sheep, wolf+1)
                visited[child] = 0

    visited[0] = 1
    dfs(1, 0)

    return max(answer)