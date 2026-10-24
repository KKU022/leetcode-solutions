class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int aliceArrive = convertDate(arriveAlice, days);
        int aliceLeave = convertDate(leaveAlice, days);
        int bobArrive = convertDate(arriveBob, days);
        int bobLeave = convertDate(leaveBob, days);
        int start = Math.max(aliceArrive, bobArrive);
        int end = Math.min(aliceLeave, bobLeave);
        return Math.max(0, end - start + 1);
    }
    private int convertDate(String date , int[] days){
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3,5));
        int totalDays =0;
        for(int i =0;i<month-1;i++){
            totalDays += days[i]; 
        }
        return totalDays + day;
    }
}
