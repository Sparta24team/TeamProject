package camp.model;

import java.util.List;

public class Student {
    private final String studentId;
    private final String studentName;
    private final List<Subject> subjects;
    private final String status;

    public Student(String studentId, String studentName, List<Subject> subjects, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjects = subjects;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getStatus() {
        return status;
    }
}
