package camp.model;

public class SubjectAverageGrade {

    private final String subjectName;
    private final String averageGrade;

    public SubjectAverageGrade(String subjectName, String averageGrade) {
        this.subjectName = subjectName;
        this.averageGrade = averageGrade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getAverageGrade() {
        return averageGrade;
    }
}
