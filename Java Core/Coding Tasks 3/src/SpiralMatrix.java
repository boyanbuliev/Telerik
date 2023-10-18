import java.util.Arrays;
import java.util.Scanner;

public class SpiralMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][n];

        int currNum = 1;

        int minRow = 0;
        int minCol = 0;
        int maxRow = n - 1;
        int maxCol = n - 1;

        while (currNum <= n * n) {
            for (int i = minCol; i <= maxCol; i++) {
                matrix[minRow][i] = currNum++;
            }
            for (int i = minRow + 1; i <= maxRow; i++) {
                matrix[i][maxCol] = currNum++;
            }
            for (int i = maxCol - 1; i > minCol; i--) {
                matrix[maxRow][i] = currNum++;
            }
            for (int i = maxRow; i > minRow; i--) {
                matrix[i][minCol] = currNum++;
            }
            minCol++;
            minRow++;
            maxCol--;
            maxRow--;
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]).replaceAll("[\\[\\],]", ""));
        }
    }
}