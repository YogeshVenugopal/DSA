package problems.Java.Arrays.Easy;

class SeparateDigitInArray {
    public static int[] separateDigits(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();

        for(int num : nums){
            if(num < 10) arr.add(num);

            else{
                String numStr = Integer.toString(num);

                for(int i = 0 ; i < numStr.length(); i++){
                    arr.add(Character.getNumericValue(numStr.charAt(i)));
                }
            }
        }

        return arr.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] nums = {13, 25, 83, 77};
        int[] result = separateDigits(nums);
        System.out.print("Separated digits: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}