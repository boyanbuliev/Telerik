import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BigNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arrLengths = scanner.nextLine().split(" ");
        int arr1Length = Integer.parseInt(arrLengths[0]);
        int arr2Length = Integer.parseInt(arrLengths[1]);

        int[][] matrix = new int[2][];

        for (int i = 0; i < 2; i++) {
            String[] currentArrStr = scanner.nextLine().split(" ");
            int[] currArr = new int[currentArrStr.length];
            for (int j = 0; j < currentArrStr.length; j++) {
                currArr[j] = Integer.parseInt(currentArrStr[j]);
            }
            matrix[i] = currArr;
        }

        List<Integer> res = new ArrayList<>();
        int remainder = 0;
        for (int i = 0; i < Math.min(arr1Length, arr2Length); i++) {
            int currSum = matrix[0][i] + matrix[1][i] + remainder;
            remainder = 0;
            if (currSum >= 10) {
                res.add(currSum % 10);
                remainder++;
            } else {
                res.add(currSum);
            }
        }
        if (arr1Length < arr2Length) {
            for (int i = arr1Length; i < arr2Length; i++) {
                int currSum = matrix[1][i] + remainder;
                remainder = 0;
                if (currSum >= 10) {
                    res.add(currSum % 10);
                    remainder++;
                } else {
                    res.add(currSum);
                }
            }
        } else if (arr1Length > arr2Length) {
            for (int i = arr2Length; i < arr1Length; i++) {
                int currSum = matrix[0][i] + remainder;
                remainder = 0;
                if (currSum >= 10) {
                    res.add(currSum % 10);
                    remainder++;
                } else {
                    res.add(currSum);
                }
            }
        }
        if (remainder > 0) {
            res.add(remainder);
        }
        System.out.println(res.toString().replaceAll("[\\[\\],]", ""));

    }
}
//3 6
//8 5 5
//6 4 4 9 9 9