package camp.model;
/**
 * Score : 학생의 점수를 나타냄
 -
 * 특정 학생이 특정 과목에서 특정 회차에 받은 점수와
 * 해당 점수에 대한 등급을 관리합
 */

public class Score {

    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private int value;
    private String grade;

    //Score 객체 초기화
    public Score(String scoreId, String subjectId, String studentId, int round, int value, String grade) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.value = value;
        this.grade = grade;
    }

    //점수 ID 반환
    public String getScoreId() {
        return scoreId;
    }

    //회차 설정
    public void setRound(int round) {
        this.round = round;
    }

    //점수 설정, 해당 점수에 따른 등급 계산하여 설정
    public void setValue(int value) {
        this.value = value;
        this.grade = calculateGrade(value);
    }

    //회차 반환
    public int getRound() {
        return round;
    }

    //과목 ID 반환
    public String getSubjectId() {
        return subjectId;
    }

    //학생 ID 반환
    public String getStudentId() {
        return studentId;
    }

    //점수값 반환
    public int getValue() {
        return value;
    }

    //등급 반환
    public String getGrade() {
        return grade;
    }

    //점수값 반환
    public int getScoreValue() {
        return value;
    }

    //점수 기준으로 등급 계산
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
