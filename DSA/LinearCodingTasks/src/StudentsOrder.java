import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int[] nk = Arrays.stream(scanner.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<String> students = Arrays.stream(scanner.readLine().split(" ")).collect(Collectors.toList());

        for (int i = 0; i < nk[1]; i++) {
            String[] studentsToChange = scanner.readLine().split(" ");
            int index = students.indexOf(studentsToChange[0]);
            students.remove(index);
            students.add(students.indexOf(studentsToChange[1]), studentsToChange[0]);
        }
        System.out.println(students.toString().replaceAll("[\\[\\],]", ""));
    }
}