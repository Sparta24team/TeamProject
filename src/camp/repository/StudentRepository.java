package camp.repository;

import camp.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentRepository {

    private final String STUDENT_CODE = "ST";

    private static StudentRepository instance;

    private final List<Student> studentStore;
    private int sequence = 0;

    private StudentRepository() {
        this.studentStore = new ArrayList<>();
    }

    public static StudentRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new StudentRepository();
            return instance;
        }
        return instance;
    }

    public void save(Student student) {
        String studentId = makeStudentId();
        student.setStudentId(studentId);
        studentStore.add(student);
    }

    public List<Student> findAll() {
        return studentStore;
    }

    private String makeStudentId() {
        sequence++;
        return STUDENT_CODE + sequence;
    }

    public boolean doesNotExist(String studentId) {
        return studentStore.stream()
                .noneMatch(student -> student.isSameStudentId(studentId));
    }

    public List<Student> findAllByStatus(String status) {
        return studentStore.stream()
                .filter(student -> student.isSameStatus(status))
                .collect(Collectors.toList());
    }

    public Student findById(String studentId) {
        return studentStore.stream()
                .filter(student -> student.isSameStudentId(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생 ID입니다."));
    }
}
