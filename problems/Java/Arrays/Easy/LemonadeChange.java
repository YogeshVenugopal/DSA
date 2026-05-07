package problems.Java.Arrays.Easy;

class Solution {
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;

        for(int bill : bills){
            if(bill == 5) five++;
            else if(bill == 10){
                if(five == 0) return false;
                else{
                    five--;
                    ten++;
                }
            }
            else{
                if(ten > 0 && five > 0){
                    ten--;
                    five--;
                }
                else if(five >= 3) five -= 3;
                else return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println("Can provide change: " + lemonadeChange(bills)); // Output: true
    }
}