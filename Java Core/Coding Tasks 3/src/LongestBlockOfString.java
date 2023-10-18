import java.util.Scanner;

public class LongestBlockOfString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split("");
        String largestBlock = arr[0];
        int largestLength = 1;
        String currBlock = arr[0];
        int currLength = 1;
        String previousLetter = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals(previousLetter)) {
                currBlock += arr[i];
                currLength++;
            } else {
                currBlock = arr[i];
                currLength = 1;
            }
            if (currLength > largestLength) {
                largestBlock = currBlock;
                largestLength=currLength;
            }
            previousLetter = arr[i];

        }
        System.out.println(largestBlock);
    }
}
