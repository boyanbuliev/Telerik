import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountOccurrences2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(countOccurrences(Integer.parseInt(reader.readLine())));
    }

    private static int countOccurrences(int n) {
        if (n == 0) {
            return 0;
        }

        return n % 10 == 8 ? n / 10 % 10 == 8 ? 2 + countOccurrences(n / 10) : 1 + countOccurrences(n / 10) : countOccurrences(n / 10);
    }
}
