import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Task task = new Task("Write unit tests", "Pesho", LocalDate.now().plusDays(1));
        Issue issue = new Issue("Review tests", "Someone must review Pesho's tests.", LocalDate.now().plusDays(1));

        Board board = new Board();

        board.addItem(task);  // treating type Task as type BoardItem
        board.addItem(issue); // treating type Issue as type BoardItem

        board.displayHistory();
    }
}

