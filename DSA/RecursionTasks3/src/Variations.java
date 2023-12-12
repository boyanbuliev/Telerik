import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Variations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int z = Integer.parseInt(reader.readLine());

        String[] xy = reader.readLine().split(" ");
        Arrays.sort(xy);
        printVariations(xy, new String[z], 0, z);


    }

    private static void printVariations(String[] xy, String[] curr, int start, int z) {
        if (start == z) {
            StringBuilder sb = new StringBuilder();
            for (String s : curr) {
                sb.append(s);
            }
            System.out.println(sb.toString());
            return;
        }

        for (String str : xy) {
            curr[start] = str;
            printVariations(xy, curr, start + 1, z);
        }
    }
}
