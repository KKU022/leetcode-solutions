import java.util.Stack;
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> st = new Stack<>();
        for(int i =0; i < s.length();i++){
            char current =s.charAt(i);
            if(st.isEmpty()){
                st.push(current);
            }
            else if (st.peek() == current){
                st.pop();
            }
            else {
                st.push(current);
            } 
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}
