package camp.model;

public class Subject {

    private String subjectId;
    private final String subjectName;
    private final String subjectType;

    public Subject(String subjectName, String subjectType) {
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
