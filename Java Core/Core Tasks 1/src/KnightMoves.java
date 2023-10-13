import java.util.Arrays;
import java.util.Scanner;

public class KnightMoves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][n];

        int[] horseMovesVertical = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] horseMovesHorizontal = {-1, 1, -2, 2, -2, 2, -1, 1};
        int moveCounter = 1;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int currRow = r;
                int currCol = c;

                while (matrix[currRow][currCol] == 0) {
                    matrix[currRow][currCol] = moveCounter++;

                    for (int m = 0; m < horseMovesVertical.length; m++) {
                        int nextRow = currRow + horseMovesVertical[m];
                        int nextCol = currCol + horseMovesHorizontal[m];

                        if (verifyMove(matrix, nextRow, nextCol)) {
                            continue;
                        }
                        currRow = nextRow;
                        currCol = nextCol;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]).replaceAll("[\\[\\],]", ""));
        }
    }

    public static boolean verifyMove(int[][] matrix, int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix.length) {
            return true;
        }
        if (matrix[nextRow][nextCol] != 0) {
            return true;
        }
        return false;
    }
}