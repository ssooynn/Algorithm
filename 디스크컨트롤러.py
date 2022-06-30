import heapq
def solution(jobs):
    answer, nztime = 0, 0
    new = len(jobs)
    jobs.sort()

    while jobs:
        if nztime >= jobs[0][0]:
            tmp = sorted(list(filter(lambda x: x if x[0] <= nztime else False, jobs)),key=lambda x: x[1])
            tp = heapq.heappop(tmp)
            nztime += tp[1]
            answer += nztime-tp[0]
            jobs = tmp + jobs[len(tmp)+1:]
        else : nztime = jobs[0][0]    

    return int(answer/new)
