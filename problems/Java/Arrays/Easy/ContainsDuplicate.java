package problems.Java.Arrays.Easy;

class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int i : nums){
            if(set.contains(i)) return true;
            set.add(i);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums)); // Output: true

        nums = new int[]{1, 2, 3, 4};
        System.out.println(containsDuplicate(nums)); // Output: false
    }
}