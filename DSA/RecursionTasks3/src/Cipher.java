import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cipher {
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String message = reader.readLine();

        String[] cipher = reader.readLine().split("(?=[A-Z])");

        Map<String, String> codes = new HashMap<>();
        for (String str : cipher) {
            codes.put(str.substring(0, 1), str.substring(1));
        }
        decipher(codes, new StringBuilder(), message);
        System.out.println(result.size());
            for (String str : result) {
                System.out.println(str);

        }
    }

    private static void decipher(Map<String, String> codes, StringBuilder curr, String message) {
        if (message.length() == 0) {
            result.add(curr.toString());
            return;

        }
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            if (message.startsWith(entry.getValue())) {
                curr.append(entry.getKey());
                decipher(codes, curr, message.replaceFirst(entry.getValue(), ""));
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}

