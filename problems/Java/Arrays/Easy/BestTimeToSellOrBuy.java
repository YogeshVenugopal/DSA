package problems.Java.Arrays;

public class BestTimeToSellOrBuy {

    public static int maxProfit(int[] stocks) {
        int currentSum = Integer.MAX_VALUE;
        int maxSum = 0;

        for (int i : stocks) {
            currentSum = Math.min(i, currentSum);
            maxSum = Math.max(maxSum, i - currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] stocks = {2, 4, 1};
        System.out.println(maxProfit(stocks));
    }
}
