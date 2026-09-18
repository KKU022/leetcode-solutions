class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(String word : strs){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(map.containsKey(key)){
                map.get(key).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(key,list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
