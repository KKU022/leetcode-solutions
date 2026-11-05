class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            int idx = Arrays.binarySearch(arr2, x);
            
            // If exact match is found, distance is 0 which is <= d
            if (idx >= 0) {
                continue;
            }
            
            // binarySearch returns -(insertion point) - 1 if not found
            int insertionPoint = -idx - 1;
            boolean isValid = true;
            
            // Check the element just greater than x
            if (insertionPoint < arr2.length && Math.abs(arr2[insertionPoint] - x) <= d) {
                isValid = false;
            }
            
            // Check the element just smaller than x
            if (insertionPoint > 0 && Math.abs(arr2[insertionPoint - 1] - x) <= d) {
                isValid = false;
            }
            
            if (isValid) {
                distanceValue++;
            }
        }

        return distanceValue;
    }
}
