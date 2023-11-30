import java.util.Arrays;
import java.util.Scanner;

public class Jumps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        int largest = 0;
        int current;
        int[] jumps = new int[n];

        for (int i = 0; i < numbers.length; i++) {
            current = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] > current) {
                    current = numbers[j];
                    count++;
                }
            }
            jumps[i] = count;
            if (largest < count)
                largest = count;
            count = 0;
        }
        System.out.println(largest);
        for (int jump : jumps) {
            System.out.print(jump + " ");
        }
    }
}
