import java.util.*;

public class StudentsOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nk = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<String, Integer> students = new HashMap<>();
        Map<Integer, String> studentsIntKey = new TreeMap<>();

        String[] studentsArr = scanner.nextLine().split(" ");

        for (int i = 0; i < nk[0]; i++) {
            students.put(studentsArr[i], i);
            studentsIntKey.put(i, studentsArr[i]);
        }

        for (int i = 0; i < nk[1]; i++) {
            String[] studentsToSwap = scanner.nextLine().split(" ");
            int student1pos = students.get(studentsToSwap[1]);
            int student2pos = students.get(studentsToSwap[0]);
            if (student1pos < student2pos) {
                for (Map.Entry<String, Integer> entry : students.entrySet()) {
                    if (entry.getValue() >= student1pos && entry.getValue() < student2pos) {
                        entry.setValue(entry.getValue() + 1);
                    }
                }
                students.put(studentsToSwap[0], student1pos);

            } else if (student1pos > student2pos) {
                for (Map.Entry<String, Integer> entry : students.entrySet()) {
                    if (entry.getValue() > student2pos && entry.getValue() < student1pos) {
                        entry.setValue(entry.getValue() - 1);
                    }
                }
                students.put(studentsToSwap[0], student1pos - 1);
            }
        }
        students.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> System.out.print(e.getKey() + " "));
    }
}