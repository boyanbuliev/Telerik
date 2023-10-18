import java.util.Arrays;
import java.util.Scanner;

public class MatrixMaxSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] coordinatesArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] coordinates = new int[coordinatesArr.length / 2][2];
        for (int i = 0, j = 0; i < coordinates.length; i++, j += 2) {
            coordinates[i][0] = coordinatesArr[j];
            coordinates[i][1] = coordinatesArr[j + 1];
        }

        int largestSum = 0;

        for (int i = 0; i < coordinates.length; i++) {
            int horizontalPosition = Math.abs(coordinates[i][0]) - 1;
            int horizontalTarget = coordinates[i][0] > 0 ? 0 : matrix.length - 1;
            int verticalPosition = coordinates[i][1] > 0 ? 0 : matrix[i].length - 1;
            int verticalTarget = Math.abs(coordinates[i][1]) - 1;
            int horizontalMove = coordinates[i][0] >= 0 ? -1 : 1;
            int verticalMove = coordinates[i][1] >= 0 ? 1 : -1;
            int currSum = 0;

            while (true) {
                currSum += matrix[horizontalPosition][verticalPosition];
                if (verticalPosition != verticalTarget) {
                    verticalPosition += verticalMove;
                    continue;
                }
                if (horizontalPosition != horizontalTarget) {
                    horizontalPosition += horizontalMove;
                    continue;
                }
                break;
            }
            largestSum = Math.max(currSum, largestSum);
        }
        System.out.println(largestSum);
    }
}
