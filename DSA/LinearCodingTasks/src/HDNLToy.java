import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class HDNLToy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Stack<Pair> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        String spacing = " ";
        int counter = 1;

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            char currChar = input.charAt(0);
            int currInt = Integer.parseInt(input.substring(1));
            Pair currPair = new Pair(currChar, currInt);

            if (stack.isEmpty()) {
                stack.push(currPair);
                result.add(String.format("<%s>", input));
                continue;
            }
            if (stack.peek().value >= currPair.value) {
                while (!stack.isEmpty() && stack.peek().value >= currPair.value) {
                    result.add(String.format("%s</%s>", repeatStr(--counter), stack.pop().toString()));
                }
            }
            stack.push(currPair);
            result.add(String.format("%s<%s>", repeatStr(counter++), input));

        }
        while (!stack.isEmpty()) {
            result.add(String.format("%s</%s>", repeatStr(--counter), stack.pop().toString()));
        }
        result.forEach(System.out::println);
    }

    private static String repeatStr(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private static class Pair {
        char key;
        int value;

        public Pair(char key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%c%d", key, value);
        }
    }
}