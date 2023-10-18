enum Status {
    OPEN("Open"), TODO("To Do"), IN_PROGRESS("In Progress"), DONE("Done"), VERIFIED("Verified");

    private static final Status[] values = values();
    public final String name;

    Status(String name) {
        this.name = name;
    }

    public Status next() {
        return values[(this.ordinal() + 1)];
    }

    public Status previous() {
        return values[(this.ordinal()) - 1];
    }

    @Override
    public String toString() {
        return switch (this) {
            case OPEN -> OPEN.name;
            case TODO -> TODO.name;
            case IN_PROGRESS -> IN_PROGRESS.name;
            case DONE -> DONE.name;
            case VERIFIED -> VERIFIED.name;
        };
    }
}