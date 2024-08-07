package camp.manager;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

import java.util.Iterator;
import java.util.List;

public class ScoreManager {
    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    // ScoreRepository, StudentRepository, SubjectRepository 객체 초기화
    public ScoreManager(ScoreRepository scoreRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public void createScore(String studentId, String subjectId, int round, int scoreValue) {
        validateStudentId(studentId);
        validateSubjectId(subjectId);
        validateRound(round);
        validateScoreValue(scoreValue);

        if (existsScore(subjectId, round)) {
            throw new IllegalArgumentException("과목의 회차 점수는 중복되어 등록될 수 없습니다.");
        }

        String grade = calculateGrade(subjectId, scoreValue);
        Score score = new Score(ScoreRepository.sequence(), subjectId, studentId, round, scoreValue, grade);
        scoreRepository.addScore(score);
    }

    public void updateScore(String studentId, String subjectId, int round, int scoreValue) {
        validateStudentId(studentId);
        validateSubjectId(subjectId);
        validateRound(round);
        validateScoreValue(scoreValue);

        for (Score score : scoreRepository.getAllScores()) {
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId) && score.getRound() == round) {
                score.setValue(scoreValue);
                break;
            }
        }
    }

    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        validateStudentId(studentId);
        validateSubjectId(subjectId);

        for (Score score : scoreRepository.getAllScores()) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId)) {
                System.out.printf("회차 = %d%n 등급 = %s%n", score.getRound(), score.getGrade());
                System.out.println("==================================");
            }
        }
    }

    public void inquireMandatoryGrades(String status) {
        List<Student> students = studentRepository.getAllStudents();
        int totalScore = 0;
        int scoreCount = 0;

        for (Student student : students) {
            if (student.getStatus().equalsIgnoreCase(status)) {
                for (Subject subject : student.getSubjects()) {
                    if ("MANDATORY".equals(subject.getSubjectType())) {
                        for (Score score : scoreRepository.getAllScores()) {
                            if (score.getStudentId().equals(student.getStudentId()) && score.getSubjectId().equals(subject.getSubjectId())) {
                                totalScore += score.getScoreValue();
                                scoreCount++;
                            }
                        }
                    }
                }
            }
        }

        if (scoreCount == 0) {
            System.out.println("필수 과목 점수 데이터가 없습니다.");
        } else {
            int averageScore = totalScore / scoreCount;
            String averageGrade = calculateGradeForScore(averageScore);
            System.out.printf("상태: %s, 필수 과목 평균 등급: %s%n", status, averageGrade);
        }
    }

    public void inquireStudentAverageGrade(String studentId) {
        validateStudentId(studentId);

        Student student = studentRepository.getStudentById(studentId);
        if (student == null) {
            System.out.println("존재하지 않는 수강생 ID입니다.");
            return;
        }

        System.out.println("과목별 평균 등급을 조회합니다...");
        System.out.println("==================================");

        boolean hasScores = false;
        for (Subject subject : subjectRepository.getAllSubjects()) {
            int totalScore = 0;
            int scoreCount = 0;

            for (Score score : scoreRepository.getAllScores()) {
                if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subject.getSubjectId())) {
                    totalScore += score.getScoreValue();
                    scoreCount++;
                    hasScores = true;
                }
            }

            if (scoreCount > 0) {
                int averageScore = totalScore / scoreCount;
                String averageGrade = calculateGrade(subject.getSubjectId(), averageScore);
                System.out.printf("과목: %s, 평균 등급: %s%n", subject.getSubjectName(), averageGrade);
            }
        }

        if (!hasScores) {
            System.out.println("해당 수강생에 대한 점수 데이터가 없습니다.");
        }

        System.out.println("\n평균 등급 조회 완료!");
    }

    public boolean validateStudentId(String studentId) {
        return studentRepository.getStudentById(studentId) != null;
    }

    public boolean validateSubjectId(String subjectId) {
        return subjectRepository.getSubjectById(subjectId) != null;
    }

    public void validateRound(int round) {
        if (round < 1 || 10 < round) {
            throw new IllegalArgumentException("회차는 10 초과 및 1 미만의 수가 될 수 없습니다. (회차 범위: 1 ~ 10)");
        }
    }

    public void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    }

    public boolean existsScore(String subjectId, int round) {
        return scoreRepository.getAllScores().stream()
                .anyMatch(score -> score.getSubjectId().equals(subjectId) && score.getRound() == round);
    }

    public boolean validateScoreStudentId(String studentId) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getStudentId().equals(studentId));
    }

    public boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId));
    }

    public boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round);
    }

    private String calculateGrade(String subjectId, int scoreValue) {
        Subject subject = subjectRepository.getSubjectById(subjectId);
        if (subject == null) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID입니다.");
        }


        String subjectType = subject.getSubjectType();
        if ("MANDATORY".equals(subjectType)) {
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
        if ("CHOICE".equals(subjectType)) {
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

    private String calculateGradeForScore(int scoreValue) {
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
}
