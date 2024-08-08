package camp.application;

import camp.controller.ScoreController;
import camp.controller.StudentController;
import camp.input.InputManager;
import camp.view.View;

public class CampManagementApplication {

    private final StudentController studentController;
    private final ScoreController scoreController;
    private final InputManager inputManager;
    private final View view;

    public CampManagementApplication(StudentController studentController, ScoreController scoreController, InputManager inputManager, View view) {
        this.studentController = studentController;
        this.scoreController = scoreController;
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

    private void displayScoreManagementView() {
        boolean flag = true;
        while (flag) {
            view.printDividingLine();
            view.printRunningScoreManagementMessage();

            int userChoice = inputManager.getUserChoiceFromScoreManagementOptions();
            switch (userChoice) {
                case 1 -> registerScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradesBySubjectAndStudent(); // 수강생의 특정 과목 회차별 등급 조회
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

    private void registerStudent() {
        studentController.registerStudent();
    }

    private void inquireStudents() {
        studentController.inquireStudents();
    }

    private void registerScore() {
        scoreController.registerScore();
    }

    private void updateRoundScoreBySubject() {
        scoreController.updateRoundScoreBySubject();
    }

    private void inquireRoundGradesBySubjectAndStudent() {
        scoreController.inquireRoundGradesBySubjectAndStudent();
    }

    private void inquireStudentAverageGrade() {
        scoreController.inquireStudentAverageGrade();
    }

    private void inquireMandatoryGrades() {
        scoreController.inquireMandatoryGrades();
    }
}
