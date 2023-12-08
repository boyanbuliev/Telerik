import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangePi {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(changePi(reader.readLine(), 0));
    }

    private static String changePi(String str, int index) {
        if (index == str.length() - 1) {
            return str;
        }

        return str.substring(index, index + 2).equals("pi") ?
                changePi(str.substring(0, index) + "3.14" + str.substring(index + 2), index + 3) :
                changePi(str, index + 1);
    }
}
