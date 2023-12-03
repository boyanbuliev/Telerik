import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Jumps {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedList<Integer> numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toCollection(LinkedList::new));
        int current = numbers.pop();
        int count = 0;
        int largest = 0;
        LinkedList<Integer> jumps = new LinkedList<>();

        while (!numbers.isEmpty()) {
            LinkedList<Integer> numbers2 = new LinkedList<>(numbers);
            if (numbers2.isEmpty()) {
                current = numbers.pop();
                jumps.offer(count);
                if (count > largest)
                    largest = count;
                count = 0;
                continue;
            }
            if (numbers2.peek() > current) {
                count++;
                current = numbers2.pop();
            } else {
                numbers2.pop();
            }
        }
        jumps.offer(0);
        System.out.println(largest);
        System.out.println(jumps.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}