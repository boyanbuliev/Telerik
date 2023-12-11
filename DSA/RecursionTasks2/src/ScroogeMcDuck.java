import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ScroogeMcDuck {


    private static int counter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] labyrinth = new int[arr[0]][];

        int row = -1;
        int col = -1;
        for (int i = 0; i < arr[0]; i++) {
            labyrinth[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < labyrinth[i].length; j++) {
                if (labyrinth[i][j] == 0) {
                    labyrinth[i][j] = 1;
                    row = i;
                    col = j;
                }
            }
        }
        solveLabyrinth(labyrinth, row, col);
        System.out.println(counter);
    }

    private static void solveLabyrinth(int[][] labyrinth, int row, int col) {
        if (isOutOfBounds(labyrinth, row, col)) {
            return;
        }
        if (hasNoMoves(labyrinth, row, col)) {
            return;
        }
        counter++;
        labyrinth[row][col]--;

        String direction = "";
        int currMaxValue = -1;
        if (!isOutOfBounds(labyrinth, row, col - 1) && labyrinth[row][col - 1] > currMaxValue) {
            currMaxValue = labyrinth[row][col - 1];
            direction = "left";
        }
        if (!isOutOfBounds(labyrinth, row, col + 1) && labyrinth[row][col + 1] > currMaxValue) {
            currMaxValue = labyrinth[row][col + 1];
            direction = "right";
        }
        if (!isOutOfBounds(labyrinth, row - 1, col) && labyrinth[row - 1][col] > currMaxValue) {
            currMaxValue = labyrinth[row - 1][col];
            direction = "up";
        }
        if (!isOutOfBounds(labyrinth, row + 1, col) && labyrinth[row + 1][col] > currMaxValue) {
            direction = "down";
        }

        switch (direction) {
            case "left":
                solveLabyrinth(labyrinth, row, col - 1);
                break;
            case "right":
                solveLabyrinth(labyrinth, row, col + 1);
                break;
            case "up":
                solveLabyrinth(labyrinth, row - 1, col);
                break;
            case "down":
                solveLabyrinth(labyrinth, row + 1, col);
                break;
        }
    }

    private static boolean hasNoMoves(int[][] labyrinth, int row, int col) {
        return (isOutOfBounds(labyrinth, row, col - 1) || labyrinth[row][col - 1] == 0)
                && (isOutOfBounds(labyrinth, row, col + 1) || labyrinth[row][col + 1] == 0)
                && (isOutOfBounds(labyrinth, row - 1, col) || labyrinth[row - 1][col] == 0)
                && (isOutOfBounds(labyrinth, row + 1, col) || labyrinth[row + 1][col] == 0);
    }

    private static boolean isOutOfBounds(int[][] labyrinth, int row, int col) {
        return row >= labyrinth.length || row < 0 || col >= labyrinth[row].length || col < 0;
    }

}
