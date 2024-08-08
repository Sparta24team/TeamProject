package camp.model;

public class Subject {

    private String subjectId;
    private final String subjectName;
    private final String subjectType;

    public Subject(String subjectName, String subjectType) {
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public boolean isSameSubjectId(String subjectId) {
        return this.subjectId.equals(subjectId);
    }

    public boolean isSameSubjectType(String subjectType) {
        return this.subjectType.equals(subjectType);
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

    public boolean isMandatory() {
        return subjectType.equals("MANDATORY");
    }

    public boolean isChoice() {
        return subjectType.equals("CHOICE");
    }
}
