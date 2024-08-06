package camp.model;

public class Score {

    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private int value;
    private String grade;

    public Score(String subjectId, String studentId, int round, int value, String grade) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.value = value;
        this.grade = grade;
    }

    public String getScoreId() {
        return scoreId;
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

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setValue(int value) {
        this.value = value;
        this.grade = calculateGrade(value);
    }

    private String calculateGrade(int value) {
        // 점수에 따른 등급 계산 로직
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

    public boolean isSameStudentId(String studentId) {
        return this.studentId.equals(studentId);
    }

    public boolean isSameSubjectId(String subjectId) {
        return this.subjectId.equals(subjectId);
    }

    public boolean isSameRound(int round) {
        return this.round == round;
    }

    public void updateValue(int scoreValue) {
        this.value = scoreValue;
    }

    public void updateGrade(String grade) {
        this.grade = grade;
    }
}


