import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int length = jobs.length;
        int tmpIndex = 0; // 배열로 들어오는 요청들의 index 입니다.
        int tmpTime = 0; // 현재 수행시간 입니다.
        
        // 요청이 들어오는 길이가 짧은배열이 우선순위를 갖습니다.
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] != o2[1] ? o1[1]-o2[1]:o1[0]-o2[0];
            }
        }); 
        // 시간 순으로 배열을 정렬합니다. 시간이 같다면 길이 순으로 정렬합니다.
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
            }
        });
        
        while(tmpIndex < length){
            // 현재시간보다 요청시간이 먼저라면, 컨트롤러에 대기시킵니다.
            while(tmpIndex < length && tmpTime >= jobs[tmpIndex][0]){
                queue.offer(new int[] {jobs[tmpIndex][0], jobs[tmpIndex][1]});
                tmpIndex++;
            }
            // queue가 비어져 있지 않다면, 가장 우선순위를 갖는 하나의 작업을 poll()합니다.
            // 이 때, 한꺼번에 queue를 비운다면 이 전의 tmpTime보다 나중이고, 
            // while(!queue.isEmpty())를 도는 동안 증가된 tmpTime 사이의 작업은 추가되지 못합니다.
            if(!queue.isEmpty()){
                int[] arr = queue.poll();
                tmpTime += arr[1];
                answer += tmpTime - arr[0];
            }
            else{
                tmpTime++;
            }
        }
        
        // 남아있는 작업들을 poll() 해줍니다.
        while(!queue.isEmpty()){             
            int[] arr = queue.poll();
            tmpTime += arr[1];
            answer += tmpTime - arr[0];
        }
        
        return answer/length;
    }
}
