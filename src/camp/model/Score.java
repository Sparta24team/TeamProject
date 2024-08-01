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

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public int getRound() {
        return round;
    }

    public String getSubjectId() {
        return subjectId;
    }
}
