package camp.model;

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

    public boolean isSameStudentId(String studentId) {
        return this.studentId.equals(studentId);
    }

    public boolean isSameStatus(String status) {
        return this.status.equals(status);
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

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
