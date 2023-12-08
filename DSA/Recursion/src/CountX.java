import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountX {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(countX(reader.readLine()));
    }

    private static int countX(String str) {
        if (str.length() == 0) {
            return 0;
        }

        return str.charAt(0) == 'x' ? 1 + countX(str.substring(1)) : countX(str.substring(1));
    }
}
