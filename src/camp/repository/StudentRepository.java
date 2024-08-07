package camp.repository;

import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static List<Student> studentStore = new ArrayList<>();
    private static int studentIndex = 0;
    private static final String INDEX_TYPE_STUDENT = "ST";

    public static String sequence() {
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    public void addStudent(Student student) {
        studentStore.add(student);
    }

    public List<Student> getAllStudents() {
        return studentStore;
    }

    public Student getStudentById(String studentId) {
        return studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public void deleteStudent(String studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            studentStore.remove(student);
        }
    }
}
