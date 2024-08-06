package camp.controller;

import camp.manager.ScoreManager;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

public class ScoreController {
    private final ScoreManager scoreManager;

    public ScoreController(ScoreRepository scoreRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.scoreManager = new ScoreManager(scoreRepository, studentRepository, subjectRepository);
    }

    public void createScore(String studentId, String subjectId, int round, int scoreValue) {
        scoreManager.createScore(studentId, subjectId, round, scoreValue);
    }

    public void updateScore(String studentId, String subjectId, int round, int scoreValue) {
        scoreManager.updateScore(studentId, subjectId, round, scoreValue);
    }

    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        scoreManager.inquireRoundGradeBySubject(studentId, subjectId);
    }

    public void inquireMandatoryGrades(String status) {
        scoreManager.inquireMandatoryGrades(status);
    }

    // 1247: 수강생의 과목별 평균 등급 조회 메서드 수정
    public void inquireStudentAverageGrade(String studentId) {
        scoreManager.inquireStudentAverageGrade(studentId);
    }

    public void validateStudentId(String studentId) {
        scoreManager.validateStudentId(studentId);
    }

    public void validateSubjectId(String subjectId) {
        scoreManager.validateSubjectId(subjectId);
    }

    public void validateRound(int round) {
        scoreManager.validateRound(round);
    }

    public void validateScoreValue(int scoreValue) {
        scoreManager.validateScoreValue(scoreValue);
    }

    public boolean existsScore(String subjectId, int round) {
        return scoreManager.existsScore(subjectId, round);
    }

    public boolean validateScoreStudentId(String studentId) {
        return scoreManager.validateScoreStudentId(studentId);
    }

    public boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreManager.validateScoreSubjectId(studentId, subjectId);
    }

    public boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreManager.validateScoreRound(studentId, subjectId, round);
    }
}
