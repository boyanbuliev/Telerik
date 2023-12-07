import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {

    private static Set<Item> allItems = new TreeSet<>();
    private static Map<String, Set<Item>> itemsSortedByType = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = reader.readLine();

            String[] commandArgs = command.split(" ");
            switch (commandArgs[0]) {
                case "add":
                    add(commandArgs);
                    break;
                case "filter":
                    filter(commandArgs);
                    break;

                case "end":
                    return;
            }
        }
    }

    private static void add(String[] args) {
        Item item = new Item(args[1], Double.parseDouble(args[2]), args[3]);
        if (allItems.add(item)) {
            itemsSortedByType.putIfAbsent(item.type, new TreeSet<>());
            itemsSortedByType.get(item.type).add(item);
            System.out.printf("Ok: Item %s added successfully%n", item.name);
        } else {
            System.out.printf("Error: Item %s already exists%n", item.name);
        }
    }

    private static void filter(String[] args) {
        if (args[2].equals("type")) {
            filterByType(args[3]);
        } else {
            filterByPrice(args);
        }
    }

    private static void filterByPrice(String[] args) {
        if (args.length < 7 && args[3].equals("from")) {
            System.out.printf("Ok: %s%n", allItems.stream().filter(i -> i.price >= Double.parseDouble(args[4]))
                    .limit(10).map(Item::toString).collect(Collectors.joining(", ")));
        } else if (args.length < 7 && args[3].equals("to")) {
            System.out.printf("Ok: %s%n", allItems.stream().filter(i -> i.price <= Double.parseDouble(args[4]))
                    .limit(10).map(Item::toString).collect(Collectors.joining(", ")));
        } else {
            System.out.printf("Ok: %s%n", allItems.stream()
                    .filter(i -> i.price >= Double.parseDouble(args[4]) && i.price <= Double.parseDouble(args[6]))
                    .limit(10).map(Item::toString).collect(Collectors.joining(", ")));
        }
    }

    private static void filterByType(String type) {
        if (!itemsSortedByType.containsKey(type)) {
            System.out.printf("Error: Type %s does not exist%n", type);
        } else {
            System.out.printf("Ok: %s%n",
                    itemsSortedByType.get(type)
                            .stream().limit(10).map(Item::toString)
                            .collect(Collectors.joining(", ")));

        }
    }

    private static class Item implements Comparable<Item> {
        String name;
        double price;
        String type;

        Item(String name, double price, String type) {
            this.name = name;
            this.price = price;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Item)) return false;
            Item item = (Item) o;
            return Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return String.format("%s(%.2f)", name, price);
        }

        @Override
        public int compareTo(Item o) {
            return Comparator.comparing((Item i) -> i.price)
                    .thenComparing((Item i) -> i.name)
                    .thenComparing((Item i) -> i.type)
                    .compare(this, o);
        }
    }
}