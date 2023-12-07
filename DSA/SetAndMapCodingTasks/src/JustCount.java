import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JustCount {
    static Map<Character, Integer> special = new TreeMap<>();
    static Map<Character, Integer> uppercase = new TreeMap<>();
    static Map<Character, Integer> lowercase = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        char[] input = reader.readLine().toCharArray();

        for (char ch : input) {
            if (Character.isUpperCase(ch)) {
                addToMap(uppercase, ch);
            } else if (Character.isLowerCase(ch)) {
                addToMap(lowercase, ch);
            } else {
                addToMap(special, ch);
            }
        }
        findLargestCount(special);
        findLargestCount(lowercase);
        findLargestCount(uppercase);
    }

    private static void findLargestCount(Map<Character, Integer> map) {
        if (map.isEmpty()) {
            System.out.println("-");
        } else {
            List<Character> keys = new ArrayList<>(map.keySet());
            int count = map.get(keys.get(0));
            char curr = keys.get(0);
            for (int i = 1; i < keys.size(); i++) {
                if (map.get(keys.get(i)) > count) {
                    count = map.get(keys.get(i));
                    curr = keys.get(i);
                }
            }
            System.out.println(curr + " " + count);
        }
    }

    private static void addToMap(Map<Character, Integer> map, char ch) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
}
