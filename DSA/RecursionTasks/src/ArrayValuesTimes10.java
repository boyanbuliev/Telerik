import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArrayValuesTimes10 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int index = Integer.parseInt(reader.readLine());

        System.out.println(arrayValuesTimes10(arr, index));
    }

    private static boolean arrayValuesTimes10(int[] arr, int index) {
        if (index == arr.length - 1) {
            return false;
        }
        return arr[index] * 10 == arr[index + 1] || arrayValuesTimes10(arr, index + 1);
    }
}
