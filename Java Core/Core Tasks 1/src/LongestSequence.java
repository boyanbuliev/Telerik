import java.util.Scanner;

public class LongestSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int maxSequence = 1;
        int currSequence = 1;
        int previousNum = 0;
        for (int i = 0; i < n; i++) {
            int currentNum = Integer.parseInt(scanner.nextLine());
            if (currentNum == previousNum) {
                currSequence++;
                if (currSequence > maxSequence) {
                    maxSequence = currSequence;
                }
            } else {
                previousNum = currentNum;
                currSequence = 1;

            }
        }
        System.out.println(maxSequence);
    }
}