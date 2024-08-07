package camp.model;

import java.util.List;

public class Student {
    private final String studentId;
    private final List<Subject> subjects;
    private String studentName;
    private String status;

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

    // 추가된 메서드: 상태 설정
    public void setStatus(String status) {
        this.status = status;
    }

    // 추가된 메서드: 이름 설정
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
