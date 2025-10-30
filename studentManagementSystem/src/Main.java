import model.Address;
import model.Classroom;
import model.Student;
import service.StudentManagement;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentManagement sms = new StudentManagement();

        sms.addClassroom(new Classroom(1, "A"));
        sms.addClassroom(new Classroom(2, "B"));
        sms.addClassroom(new Classroom(3, "C"));
        sms.addClassroom(new Classroom(4, "D"));

        sms.addStudent(new Student(1, "stud1", 1, 88, "F", 10));
        sms.addStudent(new Student(2, "stud2", 1, 70, "F", 11));
        sms.addStudent(new Student(3, "stud3", 2, 88, "M", 22)); // not added
        sms.addStudent(new Student(8, "stud8", 3, 0, "M", 11));

        sms.addAddress(new Address(1, "452002", "indore", 1));
        sms.addAddress(new Address(2, "442002", "indore", 2));
        sms.addAddress(new Address(10, "482002", "indore", 8));

        sms.assignRanks();

        System.out.println("Students from Indore:");
        sms.findStudentsByCity("indore", null, null, null).forEach(System.out::println);

        System.out.println("\nPassed Students:");
        sms.getPassedStudents(null, null, null, null, null).forEach(System.out::println);

        System.out.println("\nPaginated (1–2) by Marks:");
        List<Student> paginated = sms.getPaginated(
                sms.getPassedStudents("F", null, null, null, null),
                1, 2,
                Comparator.comparingDouble(Student::getMarks).reversed()
        );
        paginated.forEach(System.out::println);
    }
}