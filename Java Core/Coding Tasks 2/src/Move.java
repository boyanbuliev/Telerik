import java.util.Arrays;
import java.util.Scanner;

public class Move {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int startingPosition = Integer.parseInt(scanner.nextLine());
        int[] arr = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        String[] commands = scanner.nextLine().split(" ");
        int forwardSum = 0;
        int backwardSum = 0;


        while (!commands[0].equals("exit")) {
            int steps = Integer.parseInt(commands[0]);
            int size = Integer.parseInt(commands[2]);
            switch (commands[1]) {
                case "forward":
                    for (int i = 0; i < steps; i++) {
                        forwardSum += arr[(startingPosition + size) % arr.length];
                        startingPosition = (startingPosition + size) % arr.length;
                    }
                    break;
                case "backwards":
                    for (int i = 0; i < steps; i++) {
//                        int currPos = startingPosition - size % arr.length >= 0 ?
//                                startingPosition - size % arr.length : startingPosition + arr.length - size % arr.length;
                        int currPos = (startingPosition - size) % arr.length;
                        if (currPos < 0) {
                            currPos = arr.length - Math.abs(currPos);
                        }
                        backwardSum += arr[currPos];
                        startingPosition = currPos;
                    }
                    break;
            }

            commands = scanner.nextLine().split(" ");
        }
        System.out.println("Forward: " + forwardSum);
        System.out.println("Backwards: " + backwardSum);
    }
}
