import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Navigation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        BigInteger[][] matrix = new BigInteger[row][col];
        BigInteger startingNum = BigInteger.ONE;

        for (int i = 0; i < row; i++) {
            BigInteger currentNum = startingNum;
            for (int j = 0; j < col; j++) {
                matrix[row - 1 - i][j] = currentNum;
                currentNum = currentNum.multiply(BigInteger.valueOf(2));
            }
            startingNum = startingNum.multiply(BigInteger.valueOf(2));
        }
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                matrix[i][j] = new BigInteger("2").pow(row - 1 + j - i);
//            }
//        }
        int[] codes = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int startRow = row - 1;
        int startCol = 0;
        BigInteger sum = BigInteger.ONE;
        matrix[row - 1][0] = BigInteger.ZERO;

        int[] rowMoves = {0, 0, 1, -1};
        int[] colMoves = {1, -1, 0, 0};

        for (int i = 0; i < n; i++) {
            int tarRow = codes[i] / Math.max(row, col);
            int tarCol = codes[i] % Math.max(row, col);
            while (startRow != tarRow || startCol != tarCol) {
                for (int move = 0; move < rowMoves.length; move++) {
                    int curRow = startRow + rowMoves[move];
                    int curCol = startCol + colMoves[move];

                    if (curRow < 0 || curRow >= row || curCol < 0 || curCol >= col) {
                        continue;
                    }
                    if (Math.abs(tarRow - curRow) > Math.abs(tarRow - startRow) || Math.abs(tarCol - curCol) > Math.abs(tarCol - startCol)) {
                        continue;
                    }
                    startRow = curRow;
                    startCol = curCol;
                    sum = sum.add(matrix[curRow][curCol]);
                    matrix[curRow][curCol] = BigInteger.ZERO;
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}

