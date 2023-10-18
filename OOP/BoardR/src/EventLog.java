import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String viewInfo() {
        return String.format("[%s] %s", timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), description);
    }
}
