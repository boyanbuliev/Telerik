import java.time.LocalDate;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;

    public BoardItem(String title, LocalDate dueDate) {
        setTitle(title);
        setDueDate(dueDate);
        this.status = Status.Open;
    }

    public String getTitle() {
        return title;
    }

    public BoardItem setTitle(String title) {
        if (title.length() < 5 || title.length() > 30) {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }
        this.title = title;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BoardItem setDueDate(LocalDate dueDate) {
        if (dueDate.compareTo(LocalDate.now()) < 0) {
            throw new IllegalArgumentException("Due date should not be in the past");
        }

        this.dueDate = dueDate;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public BoardItem setStatus(Status status) {
        this.status = status;
        return this;
    }

    public void revertStatus() {
        this.status = status.previous();
    }

    public void advanceStatus() {
        this.status = status.next();
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate.toString());
    }
}
