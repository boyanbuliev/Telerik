enum Status {
    OPEN, TODO, IN_PROGRESS, DONE, VERIFIED;

    private static final Status[] values = values();

    public Status next() {
        return values[(this.ordinal() + 1)];
    }

    public Status previous() {
        return values[(this.ordinal()) - 1];
    }

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "Open";
            case TODO:
                return "To Do";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            case VERIFIED:
                return "Verified";
        }
        return "UNKNOWN";
    }
}