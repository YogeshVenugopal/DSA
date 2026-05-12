package problems.Java.Arrays.Easy;

class IntersectionOfTwoArray {
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> first = new HashSet<>();
        HashSet<Integer> dup = new HashSet<>();

        for(int i : nums1) first.add(i);

        for(int j : nums2) 
            if(first.contains(j)) dup.add(j);

        int[] nums = new int[dup.size()];

        int n = 0;
        for(int i: dup){
            nums[n] = i;
            n++;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = intersection(nums1, nums2);
        System.out.print("Intersection: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}