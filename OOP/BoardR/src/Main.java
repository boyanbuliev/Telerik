import loggers.ConsoleLogger;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        BoardItem task = new Task("Write unit tests", "Pesho", tomorrow);
        BoardItem issue = new Issue("Review tests", "Someone must review Pesho's tests.", tomorrow);

        System.out.println(task.viewInfo());
        System.out.println(issue.viewInfo());
    }
}

