import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardItem> items;

    public Board() {
        this.items = new ArrayList<>();
    }

    public void addItem(BoardItem item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
            throw new IllegalArgumentException("Item already in the list");
        }
    }

    public int totalItems() {
        return items.size();
    }

    public void displayHistory() {
        items.forEach(i-> System.out.println(i.getHistory()));
    }
}
