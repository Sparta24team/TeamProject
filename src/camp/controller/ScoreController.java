package camp.controller;

import camp.dto.StudentAverageGrade;
import camp.dto.SubjectAverageGrade;
import camp.input.InputManager;
import camp.model.Score;
import camp.service.ScoreService;
import camp.view.View;
import java.util.List;

public class ScoreController {

    private final ScoreService scoreService;
    private final InputManager inputManager;
    private final View view;

    public ScoreController(ScoreService scoreService, InputManager inputManager, View view) {
        this.scoreService = scoreService;
        this.inputManager = inputManager;
        this.view = view;
    }

    public void registerScore() {
        view.printCreateScoreMessage();
        String studentId = inputManager.getStudentId();

        String subjectId = inputManager.getSubjectId();

        int round = inputManager.getRound();
        validateRound(round);

        int scoreValue = inputManager.getScoreValue();
        validateScoreValue(scoreValue);

        scoreService.registerScore(studentId, subjectId, round, scoreValue);

        view.printCreatedScoreMessage(studentId, subjectId, round, scoreValue);
    }

    public void updateRoundScoreBySubject() {
        view.printDividingLine();
        view.printUpdateScoreMessage();

        String studentId = inputManager.getStudentId();
        String subjectId = inputManager.getSubjectId();
        int round = inputManager.getRound();
        int value = inputManager.getScoreValue();

        Score updatedScore = scoreService.updateScore(studentId, subjectId, round, value);

        view.printUpdatedDetail(updatedScore.getStudentId(), updatedScore.getSubjectId(), updatedScore.getRound(), updatedScore.getValue());
        view.printDividingLine();
        view.printUpdatedScoreMessage();
    }

    public void inquireRoundGradesBySubjectAndStudent() {
        view.printDividingLine();
        view.printInquireGradeMessage();

        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호
        String subjectId = inputManager.getSubjectId();

        List<Score> scores = scoreService.getScoresByStudentIdAndSubjectId(studentId, subjectId);
        if (scores.isEmpty()) {
            view.printDoesNotExistStudentSubjectData();
            return;
        }

        view.printDividingLine();
        for (Score score : scores) {
            view.printInquiredGradeResult(score.getRound(), score.getGrade());
        }

        view.printInquiredGradeMessage();
    }

    public void inquireStudentAverageGrade() {
        view.printDividingLine();
        view.printInquireAverageGradeMessage();

        String studentId = inputManager.getStudentId();

        List<SubjectAverageGrade> subjectAverageGrades = scoreService.getSubjectAverageGradesByStudentId(studentId);
        for (SubjectAverageGrade subjectAverageGrade : subjectAverageGrades) {
            view.printInquireStudentAverageGradeResult(subjectAverageGrade.getSubjectName(), subjectAverageGrade.getAverageGrade());
        }

        view.printInquiredAverageGradeMessage();
    }

    public void inquireMandatoryGrades() {
        view.printDividingLine();
        view.printInquireAverageGradeFromMandatorySubjectByStatusMessage();

        String status = inputManager.getStudentStatus();
        List<StudentAverageGrade> studentAverageGrades = scoreService.getAverageGradesByStatusAndSubjectType(status, "MANDATORY");

        for (StudentAverageGrade studentAverageGrade : studentAverageGrades) {
            view.printInquireAverageGradeFromMandatorySubjectByStatusResult(studentAverageGrade.getStudentName(),
                    studentAverageGrade.getAverageGrade());
        }

        view.printInquiredGradeMessage();
    }

    private void validateRound(int round) {
        if (round < 1 || 10 < round) {
            throw new IllegalArgumentException("회차는 10 초과 및 1 미만의 수가 될 수 없습니다. (회차 범위: 1 ~ 10)");
        }
    }
    private void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    }
}
