import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[] arr = new long[n + 1];
        System.out.println(fibonacci(n, arr));
    }

    private static long fibonacci(int n, long[] arr) {
        if (arr[n] != 0) {
            return arr[n];
        }
        if (n <= 2) {
            return arr[n] = 1;
        }
        return arr[n] = fibonacci(n - 1, arr) + fibonacci(n - 2, arr);

    }
}
