import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class NoahsArk {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> animals = new TreeMap<>();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String animal = reader.readLine();
            animals.put(animal, animals.containsKey(animal) ? animals.get(animal) + 1 : 1);
        }
        animals.forEach((key, value) -> System.out.printf("%s %d %s%n",
                key, value, value % 2 == 0 ? "Yes" : "No"));
    }
}
