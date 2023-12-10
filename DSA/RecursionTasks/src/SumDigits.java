import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(sumDigits(Integer.parseInt(reader.readLine())));
    }

    private static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }

        return n % 10 + sumDigits(n / 10);
    }
}
