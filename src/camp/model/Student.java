package camp.model;
import java.util.*;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjects;
    private String status;

    public Student(String seq, String studentName, List<Subject> subjects, String status) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjects = subjects;
        this.status = status;
    }

    // Getter
    public String getStudentId() { return studentId; }

    public String getStudentName() { return studentName; }

    public String getStatus() {
        return status;
    }

    public List<Subject> getSubjects() { return subjects; }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
