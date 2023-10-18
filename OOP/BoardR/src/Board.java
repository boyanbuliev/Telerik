import java.util.ArrayList;
import java.util.List;

public class Board {
    List<BoardItem> items;

    public Board() {
        this.items = new ArrayList<>();
    }

    public List<BoardItem> getItems() {
        return this.items;
    }

    public Board setItems(List<BoardItem> items) {
        this.items = items;
        return this;
    }
}
