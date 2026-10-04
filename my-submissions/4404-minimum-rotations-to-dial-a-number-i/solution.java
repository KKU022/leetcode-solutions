class Solution {
    public int minRotations(String s) {
        int current = 0;
        int rotations = 0;

        for (char c : s.toCharArray()) {
            int next = c - '0';

            int diff = Math.abs(current - next);

            rotations += Math.min(diff, 10 - diff);

            current = next;
        }

        return rotations;
    }
}
