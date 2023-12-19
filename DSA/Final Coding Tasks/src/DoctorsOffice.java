import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoctorsOffice {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String input = reader.readLine();

        while (!input.equals("End")) {
            String[] split = input.split(" ");

            switch (split[0]) {
                case "Append":
                    list.add(split[1]);
                    map.put(split[1], map.getOrDefault(split[1], 0) + 1);
                    System.out.println("OK");
                    break;
                case "Insert":
                    int pos = Integer.parseInt(split[1]);
                    if (pos < 0 || pos > list.size()) {
                        System.out.println("Error");
                        break;
                    }
                    list.add(pos, split[2]);
                    map.put(split[2], map.getOrDefault(split[2], 0) + 1);
                    System.out.println("OK");
                    break;
                case "Find":
                    if (!map.containsKey(split[1])) {
                        System.out.println(0);
                        break;
                    }
                    System.out.println(map.get(split[1]));
                    break;
                case "Examine":
                    int count = Integer.parseInt(split[1]);
                    if (count < 0 || count > list.size()) {
                        System.out.println("Error");
                        break;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < count; i++) {
                        String curr = list.remove(0);
                        sb.append(curr).append(" ");
                        map.put(curr, map.get(curr) - 1);
                    }
                    System.out.println(sb.toString().trim());
                    break;
            }
            input = reader.readLine();
        }
    }
}
