package camp.model;

public class Score {

    private String scoreId;
    private String subjectId;     //
    private String studentId;
    private int round;            //회차
    private int value;            //점수
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
    public void setRound(int round){
        this.round = round;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getRound() {
        return round;
    }

    public String getSubjectId() {

        return subjectId;
    }

    public String getStudentId(){
        return studentId;
    }
    public int getValue(){
        return value;
    }

}


