package camp.model;

public class Score {

    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private int value;
    private String grade;

    public Score(String scoreId, String subjectId, String studentId, int round, int value, String grade) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.value = value;
        this.grade = grade;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setValue(int value) {
        this.value = value;
        this.grade = calculateGrade(value);
    }

    public int getRound() {
        return round;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getValue() {
        return value;
    }

    public String getGrade() {
        return grade;
    }

    public int getScoreValue() {
        return value;
    }

    private String calculateGrade(int value) {
        if (value >= 90) {
            return "A";
        } else if (value >= 80) {
            return "B";
        } else if (value >= 70) {
            return "C";
        } else if (value >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
