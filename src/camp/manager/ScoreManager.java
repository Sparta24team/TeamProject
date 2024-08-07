package camp.manager;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

import java.util.List;

/**
 * ScoreManager: 학생의 점수 관련 작업을 관리하고 처리.
 * -
 * 이 클래스는 ScoreRepository, StudentRepository, SubjectRepository 객체를 사용하여
 * 점수 생성, 조회, 업데이트, 검증 작업을 수행.
 * -
 * 주요 기능:
 * - 새로운 점수 생성
 * - 점수 업데이트
 * - 특정 과목에 대한 회차별 점수 조회
 * - 필수 과목 점수 조회
 * - 학생의 과목별 평균 점수 조회
 * - 학생 ID, 과목 ID, 회차 번호, 점수 값 검증
 * - 점수가 존재하는지 확인
 * - 점수를 부여받은 학생인지 확인
 * - 학생 ID와 과목 ID의 유효성 검증
 */

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

    // 특정 학생이 특정 과목에서 특정 회차에 받은 점수를 생성
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

    // 점수를 업데이트
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

    // 특정 학생이 특정 과목에서 각 회차에 받은 점수를 조회
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

    // 필수 과목 점수를 조회
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

    // 특정 학생의 과목별 평균 점수를 조회
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
    }


    // 학생 ID를 검증
    public boolean validateStudentId(String studentId) {
        return studentRepository.getStudentById(studentId) != null;
    }

    // 과목 ID를 검증
    public boolean validateSubjectId(String subjectId) {
        return subjectRepository.getSubjectById(subjectId) != null;
    }

    // 회차를 검증
    public void validateRound(int round) {
        if (round < 1 || 10 < round) {
            throw new IllegalArgumentException("회차는 10 초과 및 1 미만의 수가 될 수 없습니다. (회차 범위: 1 ~ 10)");
        }
    }

    // 점수값을 검증
    public void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    }

    // 특정 과목의 특정 회차에 점수가 존재하는지 확인
    public boolean existsScore(String subjectId, int round) {
        return scoreRepository.getAllScores().stream()
                .anyMatch(score -> score.getSubjectId().equals(subjectId) && score.getRound() == round);
    }

    // 학생 ID로 점수를 부여받은 학생인지 검사
    public boolean validateScoreStudentId(String studentId) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getStudentId().equals(studentId));
    }

    // 학생 ID와 과목 ID가 유효한 조합인지 검증
    public boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId));
    }

    // 학생 ID, 과목 ID, 회차 번호가 유효한지 검증
    public boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreRepository.getAllScores().stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round);
    }

    // 점수를 기준으로 등급을 계산
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

    // 평균 점수를 기준으로 등급을 계산
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
