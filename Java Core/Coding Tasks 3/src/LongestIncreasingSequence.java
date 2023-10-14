import java.util.Scanner;

public class LongestIncreasingSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int previousNum = Integer.parseInt(scanner.nextLine());
        int longestSequence = 1;
        int currentSequence = 1;
        for (int i = 0; i < n - 1; i++) {
            int currentNum = Integer.parseInt(scanner.nextLine());
            if (currentNum > previousNum) {
                currentSequence++;
            } else {
                currentSequence = 1;
            }
            if (currentSequence > longestSequence) {
                longestSequence = currentSequence;
            }
            previousNum = currentNum;
        }
        System.out.println(longestSequence);
    }
}
