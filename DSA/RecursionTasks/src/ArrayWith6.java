import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArrayWith6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int index = Integer.parseInt(reader.readLine());

        System.out.println(arrayWith6(arr, index));
    }

    private static boolean arrayWith6(int[] arr, int index) {
        if (index == arr.length || arr.length == 0) {
            return false;
    }
        return arr[index] == 6 || arrayWith6(arr, index + 1);
    }
}
