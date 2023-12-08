import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraysContaining11 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int index = Integer.parseInt(reader.readLine());

        System.out.println(arraysContaining11(arr, index));
    }

    private static int arraysContaining11(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        }
        return arr[index] == 11 ? 1 + arraysContaining11(arr, index + 1) : arraysContaining11(arr, index + 1);
    }
}
