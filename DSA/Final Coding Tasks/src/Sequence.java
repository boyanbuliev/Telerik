import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> elements = new ArrayList<>();
        elements.add(nk[0]);

        int index = 0;
        for (int i = 0; i < nk[1] - 1; i++) {
            if (i % 3 == 0) {
                elements.add(elements.get(index) + 1);
            } else if (i % 3 == 1) {
                elements.add(2 * elements.get(index) + 1);
            } else {
                elements.add(elements.get(index++) + 2);
            }
        }
        System.out.println(elements.get(elements.size() - 1));
    }
}
