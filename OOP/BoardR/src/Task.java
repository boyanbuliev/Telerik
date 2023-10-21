import java.time.LocalDate;

public class Task extends BoardItem {
    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        setAssignee(assignee);
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        if (assignee.length() < 5 || assignee.length() > 30) {
            throw new IllegalArgumentException("Please provide an assignee with length between 5 and 30 chars");
        }
        if (this.assignee != null)
            addLogEntry(String.format("Assignee changed from %s to %s", this.assignee, assignee));
        this.assignee = assignee;
    }

    @Override
    public String viewInfo() {
        return String.format("Task: %s, Assignee: %s", super.viewInfo(), getAssignee());
    }
}

