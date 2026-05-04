package problems.Java.Arrays.Easy;

class SumOfUniqueElement {
    public static int sumOfUnique(int[] nums) {
        if(nums.length == 1) return nums[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for(int i : nums){
            if(map.containsKey(i) && map.get(i) == 1) {
                sum -= i;
                map.put(i, 0);
            }
            else if(!map.containsKey(i)){
                sum += i;
                map.put(i, 1);
            }
        }   
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 4, 5, 4};
        System.out.println("Sum of unique elements: " + sumOfUnique(nums)); // Output: 9
    }
}