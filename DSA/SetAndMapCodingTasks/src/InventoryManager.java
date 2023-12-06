import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InventoryManager {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Item> items = new TreeSet<>();

        String input = reader.readLine();
        while (!input.equals("end")) {
            if (input.contains("add")) {
                String[] item = input.split(" ");
                Item curr = new Item(item[1], Double.parseDouble(item[2]), item[3]);
                if (items.add(curr)) {
                    System.out.printf("Ok: Item %s added successfully%n", curr.name);
                } else {
                    System.out.printf("Error: Item %s already exists%n", curr.name);
                }
            } else if (input.contains("filter by type")) {
                String type = input.split(" ")[3];
                if (items.stream().noneMatch(i -> i.type.equals(type))) {
                    System.out.printf("Error: Type %s does not exist%n", type);
                    input = reader.readLine();
                    continue;
                }
                System.out.printf("Ok: %s%n", items.stream()
                        .filter(i -> i.type.equals(type))
                        .limit(10)
                        .map(Item::toString)
                        .collect(Collectors.joining(", ")).trim());
            } else {
                double from;
                double to;

                if (input.matches("^filter by price from .* to .*")) {
                    from = Double.parseDouble(input.split(" ")[4]);
                    to = Double.parseDouble(input.split(" ")[6]);
                } else if (input.matches("^filter by price from(?!.* to).*$")) {
                    from = Double.parseDouble(input.split(" ")[4]);
                    to = -1;
                } else {
                    from = -1;
                    to = Double.parseDouble(input.split(" ")[4]);
                }
                System.out.printf("Ok: %s%n", items.stream()
                        .filter(i -> (from == -1 || i.price >= from) && (to == -1 || i.price <= to))
                        .limit(10)
                        .map(Item::toString)
                        .collect(Collectors.joining(", ")));
            }
//            } else if (input.matches("^filter by price from .* to .*")) {
//                double from = Double.parseDouble(input.split(" ")[4]);
//                double to = Double.parseDouble(input.split(" ")[6]);
//                System.out.printf("Ok: %s%n", items.stream()
//                        .filter(i -> i.price >= from && i.price <= to)
//                        .limit(10)
//                        .map(Item::toString)
//                        .collect(Collectors.joining(", ")).trim());
//            } else if (input.matches("^filter by price from(?!.* to).*$")) {
//                double from = Double.parseDouble(input.split(" ")[4]);
//                System.out.printf("Ok: %s%n", items.stream()
//                        .filter(i -> i.price >= from)
//                        .limit(10)
//                        .map(Item::toString)
//                        .collect(Collectors.joining(", ")));
//            } else {
//                double to = Double.parseDouble(input.split(" ")[4]);
//                System.out.printf("Ok: %s%n", items.stream()
//                        .filter(i -> i.price <= to)
//                        .limit(10)
//                        .map(Item::toString)
//                        .collect(Collectors.joining(", ")).trim());
//            }
            input = reader.readLine();
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
            return Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && Objects.equals(type, item.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(price, name, type);
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