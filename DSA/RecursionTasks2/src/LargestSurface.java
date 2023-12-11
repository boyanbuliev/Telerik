import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LargestSurface {

    private static int highest = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] labyrinth = new int[arr[0]][];
        for (int i = 0; i < arr[0]; i++) {
            labyrinth[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < arr[0]; i++) {
            for (int j = 0; j < arr[1]; j++) {
                int curr = traverse(labyrinth, i, j, labyrinth[i][j]);
                highest = Math.max(curr, highest);
            }
        }
        System.out.println(highest);
    }

    private static int traverse(int[][] labyrinth, int row, int col, int currentValue) {
        int current = 0;
        if (isOutOfBounds(labyrinth, row, col)) {
            return 0;
        }
        if (isVisited(labyrinth, row, col)) {
            return 0;
        }
        if (labyrinth[row][col] != currentValue) {
            return 0;
        }

        labyrinth[row][col] = 0;
        current++;
        current += traverse(labyrinth, row, col + 1, currentValue);
        current += traverse(labyrinth, row + 1, col, currentValue);
        current += traverse(labyrinth, row, col - 1, currentValue);
        current += traverse(labyrinth, row - 1, col, currentValue);

        return current;
    }

    private static boolean isOutOfBounds(int[][] labyrinth, int row, int col) {
        return row >= labyrinth.length || row < 0 || col >= labyrinth[row].length || col < 0;
    }

    private static boolean isVisited(int[][] labyrinth, int row, int col) {
        return labyrinth[row][col] == 0;
    }
}