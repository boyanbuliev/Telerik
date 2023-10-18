import java.util.Arrays;
import java.util.Scanner;

public class ZigZag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[input[0]][input[1]];


        for (int i = 0; i < input[0]; i++) {
            for (int j = 0; j < input[1]; j++) {
                matrix[i][j] = 1 + i * 3 + j * 3;
            }
        }
        int horizontalDirection = -1;
        int verticalDirection = 1;
        int horizontalPosition = 1;
        int verticalPosition = 1;
        int sum = matrix[0][0];

        while (true) {
            if (verticalPosition == matrix[0].length - 1 || verticalPosition == 0) {
                verticalDirection *= -1;
                horizontalDirection *= -1;
            }

            sum += matrix[horizontalPosition][verticalPosition];
            horizontalPosition += horizontalDirection;
            verticalPosition += verticalDirection;
            horizontalDirection *= -1;

            if (horizontalPosition == 0 && verticalPosition == 0 || horizontalPosition == 0 && verticalPosition == matrix[0].length - 1
                    || horizontalPosition == matrix.length - 1 && verticalPosition == 0
                    || horizontalPosition == matrix.length - 1 && verticalPosition == matrix[0].length - 1) {
                sum += matrix[horizontalPosition][verticalPosition];
                break;
            }
        }
        System.out.println(sum);
    }
}