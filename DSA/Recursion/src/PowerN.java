import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerN {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(powerN(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())));
    }

    private static int powerN(int n, int x) {
        if (x == 0) {
            return 1;
        }

        return n * powerN(n, x - 1);
    }
}
