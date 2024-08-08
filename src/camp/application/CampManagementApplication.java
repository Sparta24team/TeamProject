package camp.application;

import camp.dto.StudentAverageGrade;
import camp.dto.SubjectAverageGrade;
import camp.input.InputManager;
import camp.model.Score;
import camp.model.Student;
import camp.service.ScoreService;
import camp.service.StudentService;
import camp.view.View;
import java.util.List;
import java.util.Set;

public class CampManagementApplication {

    private final StudentService studentService;
    private final ScoreService scoreService;
    private final InputManager inputManager;
    private final View view;

    public CampManagementApplication(StudentService studentService, ScoreService scoreService, InputManager inputManager, View view) {
        this.studentService = studentService;
        this.scoreService = scoreService;
        this.inputManager = inputManager;
        this.view = view;
    }

    public void run() {
        try {
            displayMainView();
        } catch (Exception e) {
            view.printErrorMessage(e.getMessage());
            view.printExitProgramMessageOnError();
        }
    }

    private void displayMainView() throws InterruptedException {
        boolean isNotExitProgram = true;
        while (isNotExitProgram) {
            view.printDividingLine();
            view.printApplicationStartMessage();

            int userChoice = inputManager.getUserChoiceFromManagementOptions();
            switch (userChoice) {
                case 1 -> displayStudentManagementView();
                case 2 -> displayScoreManagementView();
                case 3 -> isNotExitProgram = false;
                default -> {
                    view.printWrongInputMessage();
                    view.printReturnMessage();
                    Thread.sleep(2000);
                }
            }
        }
        view.printExitProgramMessage();
    }

    private void displayStudentManagementView() {
        boolean doesNotGoToMain = true;
        while (doesNotGoToMain) {
            view.printDividingLine();
            view.printRunningStudentManagementMessage();

            int userChoice = inputManager.getUserChoiceFromStudentManagementOptions();
            switch (userChoice) {
                case 1 -> registerStudent(); // 수강생 등록
                case 2 -> inquireStudents(); // 수강생 목록 조회
                case 3 -> doesNotGoToMain = false; // 메인 화면 이동
                default -> {
                    view.printWrongInputMessage();
                    view.printMoveToMainViewMessage();
                    doesNotGoToMain = false;
                }
            }
        }
    }

    private void registerStudent() {
        view.printCreateStudentMessage();

        String studentName = inputManager.getStudentName();
        String status = inputManager.getStudentStatus();
        Set<String> subjectIds = inputManager.getSubjectIds();

        studentService.registerStudent(studentName, status, subjectIds);

        view.printCreatedStudentMessage();
    }

    private void inquireStudents() {
        view.printDividingLine();
        view.printInquireStudentMessage();

        List<Student> students = studentService.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            view.printInquireStudentsResult(i, student.getStudentId(), student.getStudentName());
        }

        view.printInquiredStudentMessage();

        String userChoice = inputManager.getUserChoiceFromDetailInquiryOptions();
        if (userChoice.equals("Yes")) {
            inquireRoundGradeBySubject();
        }
    }

    private void displayScoreManagementView() {
        boolean flag = true;
        while (flag) {
            view.printDividingLine();
            view.printRunningScoreManagementMessage();

            int userChoice = inputManager.getUserChoiceFromScoreManagementOptions();
            switch (userChoice) {
                case 1 -> registerScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireMandatoryGrades(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 5 -> inquireStudentAverageGrade(); // 수강생의 과목별 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    view.printWrongInputMessage();
                    view.printMoveToMainViewMessage();
                    flag = false;
                }
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private void registerScore() {
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

    // 수강생의 과목별 회차 점수 수정
    private void updateRoundScoreBySubject() {
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

    // 수강생의 특정 과목 회차별 등급 조회
    private void inquireRoundGradeBySubject() {
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

    // 수강생의 과목별 평균 등급 조회
    private void inquireStudentAverageGrade() {
        view.printDividingLine();
        view.printInquireAverageGradeMessage();

        String studentId = inputManager.getStudentId();

        List<SubjectAverageGrade> subjectAverageGrades = scoreService.getSubjectAverageGradesByStudentId(studentId);
        for (SubjectAverageGrade subjectAverageGrade : subjectAverageGrades) {
            view.printInquireStudentAverageGradeResult(subjectAverageGrade.getSubjectName(), subjectAverageGrade.getAverageGrade());
        }

        view.printInquiredAverageGradeMessage();
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private void inquireMandatoryGrades() {
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
}
