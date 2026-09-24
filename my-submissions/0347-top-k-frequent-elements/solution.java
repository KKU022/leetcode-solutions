class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );
        for (int num : map.keySet()) {
            pq.offer(new int[]{num, map.get(num)});
        }
        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll()[0];
        }
        return answer;
    }
}
