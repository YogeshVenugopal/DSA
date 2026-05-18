package problems.Java.Arrays.Easy;

class MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreq = Collections.max(map.values());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int[] nums = {3, 2, 3};
        System.out.println(majorityElement.majorityElement(nums)); // Output: 3

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement.majorityElement(nums)); // Output: 2
    }
}