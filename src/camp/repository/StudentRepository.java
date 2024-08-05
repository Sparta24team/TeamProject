package camp.repository;

import camp.model.Student;
import java.util.List;

public class StudentRepository {

    private final String STUDENT_CODE = "ST";

    private final List<Student> studentStore;
    private int sequence = 0;

    public StudentRepository(List<Student> studentStore) {
        this.studentStore = studentStore;
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
