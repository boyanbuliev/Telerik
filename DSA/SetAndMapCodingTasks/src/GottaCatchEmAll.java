import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class GottaCatchEmAll {

    private static Map<String, Set<Pokemon>> pokemonTypes = new HashMap<>();
    private static List<Pokemon> pokemonRanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        while (!line.equals("end")) {
            String[] split = line.split(" ");
            switch (split[0]) {
                case "add":
                    addPokemon(split);
                    break;
                case "find":
                    findByType(split[1]);
                    break;
                case "ranklist":
                    rank(Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
                    break;
            }

            line = reader.readLine();
        }
    }

    private static void rank(int start, int end) {

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(i + 1).append(". ").append(pokemonRanks.get(i)).append("; ");
        }
        sb.replace(sb.lastIndexOf(";"), sb.length(), "");
        System.out.println(sb.toString());
    }

    private static void findByType(String arg) {
        if (!pokemonTypes.containsKey(arg)) {
            System.out.printf("Type %s: %n", arg);
            return;
        }
        System.out.printf("Type %s: %s%n", arg, pokemonTypes
                .get(arg).stream().limit(5)
                .map(Pokemon::toString).collect(Collectors.joining("; ")));
    }

    private static void addPokemon(String[] args) {
        Pokemon pokemon = new Pokemon(args[1], args[2], Integer.parseInt(args[3]));
        pokemonTypes.putIfAbsent(args[2], new TreeSet<>());
        pokemonTypes.get(args[2]).add(pokemon);
        pokemonRanks.add(Integer.parseInt(args[4]) - 1, pokemon);
        System.out.printf("Added pokemon %s to position %s%n", pokemon.name, args[4]);
    }

    private static class Pokemon implements Comparable<Pokemon> {
        String name;
        String type;
        int power;

        public Pokemon(String name, String type, int power) {
            this.name = name;
            this.type = type;
            this.power = power;
        }

        @Override
        public String toString() {
            return String.format("%s(%d)", name, power);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pokemon)) return false;
            Pokemon pokemon = (Pokemon) o;
            return power == pokemon.power && Objects.equals(name, pokemon.name) && Objects.equals(type, pokemon.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, power);
        }

        @Override
        public int compareTo(Pokemon o) {
            return name.compareTo(o.name) > 0 ? 1 : name.compareTo(o.name) < 0 ? -1 : Integer.compare(o.power, power);
        }
    }
}
