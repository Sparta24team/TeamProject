package camp.model;

public class StudentAverageGrade {

    private final String studentName;
    private final String averageGrade;

    public StudentAverageGrade(String studentName, String averageGrade) {
        this.studentName = studentName;
        this.averageGrade = averageGrade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getAverageGrade() {
        return averageGrade;
    }
}
