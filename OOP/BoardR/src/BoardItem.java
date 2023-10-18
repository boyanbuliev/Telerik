import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;
    private List<EventLog> log;

    public BoardItem(String title, LocalDate dueDate) {
            setTitle(title);
            setDueDate(dueDate);
        this.status = Status.OPEN;
        addLogEntry(String.format("Item created: %s [%s | %s]", this.title, this.status, this.dueDate));
    }

    public String getTitle() {
        return title;
    }

    public BoardItem setTitle(String title) {
        if (title.length() < 5 || title.length() > 30) {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }
        if (this.title != null) {
            addPropertyLog("Title", this.title, title);
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
        if (this.dueDate != null) {
            addPropertyLog("DueDate", this.dueDate, dueDate);
        }
        this.dueDate = dueDate;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    private void addLogEntry(String message) {
        if (log == null) {
            log = new ArrayList<>();
        }
        log.add(new EventLog(message));
    }

    public void revertStatus() {
        try {
            status = status.previous();
            addPropertyLog("Status", status.next(), status);
        } catch (ArrayIndexOutOfBoundsException e) {
            addLogEntry(String.format("Can't revert, already at %s", status));
        }
    }

    public void advanceStatus() {
        try {
            status = status.next();
            addPropertyLog("Status", status.previous(), status);
        } catch (ArrayIndexOutOfBoundsException e) {
            addLogEntry(String.format("Can't advance, already at %s", status));
        }
    }

    private <E> void addPropertyLog(String property, E firstElement, E secondElement) {
        addLogEntry(String.format("%s changed from %s to %s", property, firstElement, secondElement));
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

    public void displayHistory() {
        log.forEach(e -> System.out.println(e.viewInfo()));
    }
}
