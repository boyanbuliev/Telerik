import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangePi {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(changePi(reader.readLine()));
    }

    private static String changePi(String str) {
//        if (index == str.length() - 1) {
//            return str;
//        }
//
//        return str.startsWith("pi", index) ?
//                changePi(str.substring(0, index) + "3.14" + str.substring(index + 2), index + 3) :
//                changePi(str, index + 1);
//    }
        if (str.length() < 2) {
            return str;
        }
        return str.startsWith("pi") ? "3.14" + changePi(str.substring(2)) : str.charAt(0) + changePi(str.substring(1));
    }
}
