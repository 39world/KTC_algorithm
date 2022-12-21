from itertools import combinations

def solution(info, query):
    
    answer = []
    testResult = {}
    
    for userInfo in info: #해당하는 모든 케이스를 생성해서 테스트 결과값 딕셔너리로 정리
        userInfoSplit = userInfo.split()

        allCases = []
    
        for i in range(5):
            checkCombi = combinations([0,1,2,3],i) #콤비네이션 활용
            for combination in checkCombi:
                case = ""
                
                for j in range(4):
                    if j in combination:
                        case += userInfoSplit[j]
                    else : 
                        case += "-"
                allCases.append(case)  

        for case in allCases:
            if case not in testResult.keys():
                testResult[case] = [int(userInfoSplit[4])]
            else :
                testResult[case].append(int(userInfoSplit[4]))
    
    for key in testResult.keys(): #결과 확인을 위해 정렬
        testResult[key].sort()
                                                  
    for queryInfo in query: #타겟 확인
        querySplit = queryInfo.split()
        case = querySplit[0] + querySplit[2] +querySplit[4] + querySplit[6];
        
        if case in testResult.keys():  #타겟이 있다면 이진탐색 효율성을 위해 
            targetUser = testResult[case]

            minNow = 0
            maxNow = len(targetUser)
            
            while minNow < maxNow:
                posNow = (minNow + maxNow) // 2
                if targetUser[posNow] >= int(querySplit[7]):
                    maxNow = posNow
                else: 
                    minNow = posNow +1
                    
            answer.append(len(targetUser) - maxNow) 
        else :
            answer.append(0)
    
    return answer