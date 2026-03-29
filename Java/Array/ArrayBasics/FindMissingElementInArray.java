public class FindMissingElementInArray {
    public static void main(String[] args){
        int[] arr = {0, 3, 2};

        int max = 0;
        int sum = 0;

        for(int i : arr){
            if(i > max) max = i;
            sum += i;
        }

        int expected = (max*(max+1))/2;
        System.out.print(expected - sum);
    }
}
