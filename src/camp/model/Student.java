package camp.model;

public class Student {
    private String studentId;
    private String studentName;
    private String status;

    public Student(String seq, String studentName,String status) {
        this.studentId = seq;
        this.studentName = studentName;
        this.status = status;
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
}
