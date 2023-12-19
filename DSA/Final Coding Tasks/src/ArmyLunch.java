import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ArmyLunch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        String[] soldiers = reader.readLine().split(" ");

        Queue<String> sergeants = new LinkedList<>();
        Queue<String> corporals = new LinkedList<>();
        Queue<String> privates = new LinkedList<>();

        for (String s : soldiers) {
            if (s.startsWith("C")) {
                corporals.offer(s);
            } else if (s.startsWith("S")) {
                sergeants.offer(s);
            } else if (s.startsWith("P")) {
                privates.offer(s);
            }
        }

        for (String sergeant : sergeants) {
            System.out.print(sergeant + " ");
        }
        for (String corporal : corporals) {
            System.out.print(corporal + " ");
        }
        for (String aPrivate : privates) {
            System.out.print(aPrivate + " ");
        }
    }
}
