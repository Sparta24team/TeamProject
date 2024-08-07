package camp;

import camp.config.ApplicationConfig;
import camp.io.InputManager;
import camp.io.View;
import camp.model.Score;
import camp.model.Student;
import camp.model.StudentAverageGrade;
import camp.model.SubjectAverageGrade;
import camp.service.ScoreService;
import camp.service.StudentService;
import java.util.List;
import java.util.Set;

public class CampManagementApplication {

    private static ApplicationConfig applicationConfig = new ApplicationConfig();
    private static SubjectRepositoryInitializer subjectRepositoryInitializer = applicationConfig.subjectRepositoryInitializer();

    private static StudentService studentService = applicationConfig.studentService();
    private static ScoreService scoreService = applicationConfig.scoreService();
    private static View view = new View();
    private static InputManager inputManager = new InputManager(view);

    public static void main(String[] args) {
        subjectRepositoryInitializer.initialize();
        while (true) {
            try {
                displayMainView();
            } catch (Exception e) {
                view.printErrorMessage(e.getMessage());
                view.printExitProgramMessageOnError();
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            view.printDividingLine();
            view.printApplicationStartMessage();

            int userChoice = inputManager.getUserChoiceFromManagementOptions();
            switch (userChoice) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    view.printWrongInputMessage();
                    view.printReturnMessage();
                    Thread.sleep(2000);
                }
            }
        }
        view.printExitProgramMessage();
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            view.printDividingLine();
            view.printRunningStudentManagementMessage();

            int userChoice = inputManager.getUserChoiceFromStudentManagementOptions();
            switch (userChoice) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudents(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    view.printWrongInputMessage();
                    view.printMoveToMainViewMessage();
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        view.printCreateStudentMessage();

        String studentName = inputManager.getStudentName();
        String status = inputManager.getStudentStatus();
        Set<String> subjectIds = inputManager.getSubjectIds();

        studentService.registerStudent(studentName, status, subjectIds);

        view.printCreatedStudentMessage();
    }

    // 수강생 목록 조회
    private static void inquireStudents() {
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

    //1. 866766 / park
    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            view.printDividingLine();
            view.printRunningScoreManagementMessage();

            int userChoice = inputManager.getUserChoiceFromScoreManagementOptions();
            switch (userChoice) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
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
    private static void createScore() {
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

    private static void validateRound(int round) {
        if (round < 1 || 10 < round) {
            throw new IllegalArgumentException("회차는 10 초과 및 1 미만의 수가 될 수 없습니다. (회차 범위: 1 ~ 10)");
        }
    }

    private static void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    }

    // 수강생의 과목별 회차 점수 수정     set
    private static void updateRoundScoreBySubject() {
        view.printDividingLine();
        view.printUpdateScoreMessage();

        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호
        String subjectId = inputManager.getSubjectId();   //과목
        int round = inputManager.getRound();//회차
        int value = inputManager.getScoreValue();       //점수

        Score updatedScore = scoreService.updateScore(studentId, subjectId, round, value);

        view.printUpdatedDetail(updatedScore.getStudentId(), updatedScore.getSubjectId(), updatedScore.getRound(), updatedScore.getValue());
        view.printDividingLine();
        view.printUpdatedScoreMessage();
    }

    // 수강생의 특정 과목 회차별 등급 조회
    // 코드 순서 변경 : 수강생 ID, 과목 ID 유효성 각각 조회로 변경함
    private static void inquireRoundGradeBySubject() {
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
    private static void inquireStudentAverageGrade() {
        view.printDividingLine();
        view.printInquireAverageGradeMessage();
        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호

        Student student = studentService.getStudent(studentId);
        if (student == null) {
            view.printDoesNotExistStudentIdMessage();
            return;
        }

        List<SubjectAverageGrade> subjectAverageGrades = scoreService.getSubjectAverageGradesByStudentId(studentId);
        for (SubjectAverageGrade subjectAverageGrade : subjectAverageGrades) {
            view.printInquireStudentAverageGradeResult(subjectAverageGrade.getSubjectName(), subjectAverageGrade.getAverageGrade());
        }
        view.printInquiredAverageGradeMessage();
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private static void inquireMandatoryGrades() {
        view.printDividingLine();
        view.printInquireAverageGradeFromMandatorySubjectByStatusMessage();
        String status = inputManager.getStudentStatus(); // 조회할 수강생 상태 status 저장

        List<StudentAverageGrade> studentAverageGrades = scoreService.getAverageGradesByStatusAndSubjectType(status, "MANDATORY");

        for (StudentAverageGrade studentAverageGrade : studentAverageGrades) {
            view.printInquireAverageGradeFromMandatorySubjectByStatusResult(studentAverageGrade.getStudentName(), studentAverageGrade.getAverageGrade());
        }

        view.printInquiredGradeMessage();
    }
}
