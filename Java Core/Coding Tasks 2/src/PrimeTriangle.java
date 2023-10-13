import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            boolean isPrime = true;
            if (i == 1 || i == 2) {
                nums.add(1);
                printNums(nums);
                continue;
            }
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    nums.add(0);
                    break;
                }
            }
            if (isPrime) {
                nums.add(1);
                printNums(nums);
            }
        }
    }

    static void printNums(List<Integer> nums) {
        System.out.println(nums.toString().replaceAll("[\\[\\], ]", ""));
    }
}
