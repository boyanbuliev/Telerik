enum Status {
    Open, Todo, InProgress, Done, Verified;

    private static final Status[] values = values();

    public Status next() {
        return values[(this.ordinal() + 1)];
    }

    public Status previous() {
        return values[(this.ordinal()) - 1];
    }
}