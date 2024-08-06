package camp.repository;

import camp.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
