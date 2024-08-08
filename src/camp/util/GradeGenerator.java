package camp.util;

public class GradeGenerator {

    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    private GradeGenerator() {
    }

    public static String generateGrade(String subjectType, int scoreValue) {
        if (SUBJECT_TYPE_MANDATORY.equals(subjectType)) {
            if (scoreValue >= 95) {
                return "A";
            }
            if (scoreValue >= 90) {
                return "B";
            }
            if (scoreValue >= 80) {
                return "C";
            }
            if (scoreValue >= 70) {
                return "D";
            }
            if (scoreValue >= 60) {
                return "F";
            }
            return "N";
        }
        if (SUBJECT_TYPE_CHOICE.equals(subjectType)) {
            if (scoreValue >= 90) {
                return "A";
            }
            if (scoreValue >= 80) {
                return "B";
            }
            if (scoreValue >= 70) {
                return "C";
            }
            if (scoreValue >= 60) {
                return "D";
            }
            if (scoreValue >= 50) {
                return "F";
            }
            return "N";
        }

        throw new IllegalStateException("유효하지 않은 과목 타입입니다.");
    }
}
