import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Integer> primeNums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeNums.add(i);
            }
        }
        System.out.println(primeNums.toString().replaceAll("[\\[\\],]", ""));
    }
}