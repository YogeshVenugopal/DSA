package problems.Java.Arrays.Easy;

class MinimumCost {
    public static int minimumCost(int[] cost) {
         Arrays.sort(cost);

        int ans = 0;
        int count = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            count++;

            if (count % 3 == 0) {
                continue;
            }

            ans += cost[i];
        }

        return ans;
    }   

    public static void main(String[] args) {
        int[] cost = {1, 2, 3};
        System.out.println("Minimum cost: " + minimumCost(cost)); // Output: 5
    }

}