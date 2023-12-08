import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Triangle {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(triangle(Integer.parseInt(reader.readLine())));
    }

    private static int triangle(int n) {
        if (n == 0) {
            return 0;
        }
        return n + triangle(n - 1);
    }
}
