package camp.model;

import java.util.*;

import java.util.List;

public class Student {

    private String studentId;
    private String studentName;
    private String status;
    private List<Subject> subjects;

    public Student(String studentName, String status, List<Subject> subjects) {
        this.studentName = studentName;
        this.status = status;
        this.subjects = subjects;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStatus() {
        return status;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
