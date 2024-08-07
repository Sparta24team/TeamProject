package camp.controller;

import camp.manager.ScoreManager;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

public class ScoreController {
    private final ScoreManager scoreManager;

    // ScoreManager 객체 초기화
    public ScoreController(ScoreRepository scoreRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.scoreManager = new ScoreManager(scoreRepository, studentRepository, subjectRepository);
    }

    //특정 학생이 특정 과목에서 특정 회차에서 받은 점수를 생성
    public void createScore(String studentId, String subjectId, int round, int scoreValue) {
        scoreManager.createScore(studentId, subjectId, round, scoreValue);
    }

    //점수 업데이트
    public void updateScore(String studentId, String subjectId, int round, int scoreValue) {
        scoreManager.updateScore(studentId, subjectId, round, scoreValue);
    }

    // 회차별 과목 점수 조회
    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        scoreManager.inquireRoundGradeBySubject(studentId, subjectId);
    }

    //필수 과목 점수 조회
    public void inquireMandatoryGrades(String status) {
        scoreManager.inquireMandatoryGrades(status);
    }

    //수강생의 과목별 평균 등급 조회 메서드 수정
    public void inquireStudentAverageGrade(String studentId) {
        scoreManager.inquireStudentAverageGrade(studentId);
    }

    //학생 ID 검증 메서드
    public boolean validateStudentId(String studentId) {
        return scoreManager.validateStudentId(studentId);
    }

    //과목 ID 검증 메서드
    public boolean validateSubjectId(String subjectId) {
        return scoreManager.validateSubjectId(subjectId);
    }

    //회차 번호 검증 메서드
    public void validateRound(int round) {
        scoreManager.validateRound(round);
    }

    //주어진 점수가 유효한지 검증함
    public void validateScoreValue(int scoreValue) {
        scoreManager.validateScoreValue(scoreValue);
    }

    //점수가 회차에 존재하는지 검증
    public boolean existsScore(String subjectId, int round) {
        return scoreManager.existsScore(subjectId, round);
    }

    //학생 ID로 점수를 부여받은 학생인지 검사
    public boolean validateScoreStudentId(String studentId) {
        return scoreManager.validateScoreStudentId(studentId);
    }

    //학생 ID와 과목 ID가 유효한 조합인지 검증
    public boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreManager.validateScoreSubjectId(studentId, subjectId);
    }

    //학생 iD, 과목 ID, 회차 번호가 유효한지 검증
    public boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreManager.validateScoreRound(studentId, subjectId, round);
    }
}
