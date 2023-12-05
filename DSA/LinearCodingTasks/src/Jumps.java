import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Jumps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && numbers[i] >= numbers[stack.peek()]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[i] = result[stack.peek()] + 1;
            }
            stack.push(i);
        }
    }
}