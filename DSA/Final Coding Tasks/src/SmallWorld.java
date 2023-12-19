import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class SmallWorld {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[nm[0]][];
        for (int i = 0; i < nm[0]; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nm[0]; i++) {
            for (int j = 0; j < nm[1]; j++) {
                int res = conquer(matrix, i, j);
                if (res > 0) {
                    queue.offer(res);
                }
            }
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private static int conquer(int[][] matrix, int row, int col) {
        int counter = 0;
        if (isOutOfBounds(matrix, row, col)) {
            return 0;
        }
        if (isVisited(matrix, row, col)) {
            return 0;
        }

        matrix[row][col] = 0;
        counter++;
        counter += conquer(matrix, row + 1, col);
        counter += conquer(matrix, row - 1, col);
        counter += conquer(matrix, row, col + 1);
        counter += conquer(matrix, row, col - 1);
        return counter;
    }

    private static boolean isOutOfBounds(int[][] matrix, int row, int col) {
        return row >= matrix.length || row < 0 || col >= matrix[row].length || col < 0;
    }

    private static boolean isVisited(int[][] matrix, int row, int col) {
        return matrix[row][col] == 0;
    }
}
