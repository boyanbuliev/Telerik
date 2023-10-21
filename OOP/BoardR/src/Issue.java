import java.time.LocalDate;

public class Issue extends BoardItem {
    private final String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate, Status.OPEN);
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            this.description = "No description";
        }
    }

    public String getDescription() {
        return description;
    }

}
