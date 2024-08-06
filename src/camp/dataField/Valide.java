package camp.dataField;

import camp.management.Search;
import camp.model.Subject;

import java.util.List;

public class Valide extends Search {
    public static void validateStudentId(String studentId) {
        boolean isNoneMatch = studentStore.stream()
                .noneMatch(student -> student.getStudentId().equals(studentId));
        if (isNoneMatch) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID입니다.");
        }
    }
    public static boolean isDuplicate(List<Subject> subjects, Subject subject) {
        for (Subject s : subjects) {
            if (s.getSubjectId().equals(subject.getSubjectId())) {
                return true;
            }
        }
        return false;
    }
    public static void validateSubjectId(String subjectId) {
        boolean isNoneMatch = subjectStore.stream()
                .noneMatch(subject -> subject.getSubjectId().equals(subjectId));
        if (isNoneMatch) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID입니다.");
        }
    }
    public static void validateRound(int round) {
        if (round < 1 || 10 < round) {
            throw new IllegalArgumentException("회차는 10 초과 및 1 미만의 수가 될 수 없습니다. (회차 범위: 1 ~ 10)");
        }
    }
    public static void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    }

    public static boolean validateScoreStudentId(String studentId) {
        return scoreStore.stream()
                .noneMatch(student -> student.getStudentId().equals(studentId));
    }

    public static boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreStore.stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId));
    }

    public static boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round);
    }
    public static boolean existsScore(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .anyMatch(score -> score.getSubjectId().equals(subjectId) &&
                        score.getRound() == round &&
                        score.getStudentId().equals(studentId));
    }

}
