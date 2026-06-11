package problems.Java.Arrays;
import java.util.HashSet;

public class SingleNumber {

    public static int SingleValue(int[] arr){
        HashSet<Integer> dup = new HashSet<>();

        for(int i: arr) {
            if(dup.contains(i)) dup.remove(i);
            else dup.add(i);
        }

        for(Integer i : dup) return i;
        return -1;
    }

    public static void main(String[] args){

        int[] arr = {1, 2, 3, 3, 2, 4, 1, 4};
        System.out.print(SingleValue(arr));
    }

}
