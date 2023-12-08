import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BunnyEars2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(bunnyEars(Integer.parseInt(reader.readLine())));
    }

    private static int bunnyEars(int n) {
        if (n == 0) {
            return 0;
        }
        return n % 2 == 0 ? 3 + bunnyEars(n - 1) : 2 + bunnyEars(n - 1);
    }
}
