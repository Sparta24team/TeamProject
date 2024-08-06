package camp.dataField;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataField {
    // 데이터 저장소
    public static List<Student> studentStore;
    public static List<Subject> subjectStore;
    public static List<Score> scoreStore;

    // 과목 타입
    public static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    public static int studentIndex;
    public static final String INDEX_TYPE_STUDENT = "ST";
    public static int subjectIndex;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    public static int scoreIndex;
    public static final String INDEX_TYPE_SCORE = "SC";

    public static Scanner sc = new Scanner(System.in);
    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }
    public static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }
    public static Student getStudentById(String studentId) {
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    public static Subject getSubjectById(String subjectId) {
        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) {
                return subject;
            }
        }
        return null;
    }
    public static String calculateSubjectScoreGrade(String subjectType, int scoreValue) {
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
        return "유효 하지 않은 과목타입 입니다.";
    }
}
