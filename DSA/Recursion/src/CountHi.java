import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountHi {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(countHi(reader.readLine()));
    }

    private static int countHi(String str) {
        if (str.length() < 2) {
            return 0;
        }

        return str.startsWith("hi") ? 1 + countHi(str.substring(1)) : countHi(str.substring(1));
    }

}
