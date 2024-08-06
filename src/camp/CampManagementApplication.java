package camp;

import camp.config.ApplicationConfig;
import camp.io.InputManager;
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
    private static InputManager inputManager = new InputManager();

    public static void main(String[] args) {
        subjectRepositoryInitializer.initialize();
        while (true) {
            try {
                displayMainView();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");

            int userChoice = inputManager.getUserChoiceFromManagementOptions();
            switch (userChoice) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");

            int userChoice = inputManager.getUserChoiceFromStudentManagementOptions();
            switch (userChoice) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }

        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");

        String studentName = inputManager.getStudentName();
        String status = inputManager.getStudentStatus();
        Set<String> subjectIds = inputManager.getSubjectIds();

        studentService.registerStudent(studentName, status, subjectIds);

        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        List<Student> students = studentService.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf((i + 1) + ". 고유 번호 : %s / 이름 : %s\n", student.getStudentId(), student.getStudentName());
        }
        System.out.println("\n수강생 목록 조회 성공!");

        String userChoice = inputManager.getUserChoiceFromDetailInquiryOptions();
        if (userChoice.equals("Yes")) {
            inquireRoundGradeBySubject();
        }
    }

    //1. 866766 / park
    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");

            int userChoice = inputManager.getUserChoiceFromUserManagementOptions();
            switch (userChoice) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireMandatoryGrades(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 5 -> inquireStudentAverageGrade(); // 수강생의 과목별 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        System.out.println("시험 점수를 등록합니다.");
        String studentId = inputManager.getStudentId();

        String subjectId = inputManager.getSubjectId();

        int round = inputManager.getRound();
        validateRound(round);

        int scoreValue = inputManager.getScoreValue();
        validateScoreValue(scoreValue);

        System.out.println("시험 점수를 등록합니다.");
        scoreService.registerScore(studentId, subjectId, round, scoreValue);
        System.out.println();
        System.out.printf("%s번 수강생 %s 과목 %d회차 %d점수 등록 성공!\n", studentId, subjectId, round, scoreValue);

        System.out.println("\n점수 등록 성공!");
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
        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호

        String subjectId = inputManager.getSubjectId();   //과목

        int round = inputManager.getRound();//회차

        int value = inputManager.getScoreValue();       //점수

        System.out.println("시험 점수를 수정합니다...");
        System.out.println("==================================");

        Score updatedScore = scoreService.updateScore(studentId, subjectId, round, value);

        System.out.println("사용자 id: " + updatedScore.getStudentId());
        System.out.println("과목  id: " + updatedScore.getSubjectId());
        System.out.println("회차    : " + updatedScore.getRound());
        System.out.println("점수    : " + updatedScore.getValue());
        System.out.println("==============================");
        System.out.println("\n점수 수정 성공!");

    }

    // 수강생의 특정 과목 회차별 등급 조회
    // 코드 순서 변경 : 수강생 ID, 과목 ID 유효성 각각 조회로 변경함
    private static void inquireRoundGradeBySubject() {
        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호

        //과목 ID 입력 받기
        String subjectId = inputManager.getSubjectId();

        System.out.println("회차별 등급을 조회합니다...");
        System.out.println("==================================");

        List<Score> scores = scoreService.getScoresByStudentIdAndSubjectId(studentId, subjectId);
        if (scores.isEmpty()) {
            System.out.println("수강생의 해당 과목에 대한 기록이 없습니다.");
            return;
        }
        for (Score score : scores) {
            System.out.printf("회차 = %d%n 등급 = %s%n", score.getRound(), score.getGrade());
            System.out.println("==================================");
        }

        System.out.println("\n등급 조회 완료!");
    }

    // 수강생의 과목별 평균 등급 조회
    private static void inquireStudentAverageGrade() {
        String studentId = inputManager.getStudentId(); // 관리할 수강생 고유 번호

        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("존재하지 않는 수강생 ID입니다.");
            return;
        }

        System.out.println("과목별 평균 등급을 조회합니다...");
        System.out.println("==================================");

        List<SubjectAverageGrade> subjectAverageGrades = scoreService.getSubjectAverageGradesByStudentId(studentId);
        for (SubjectAverageGrade subjectAverageGrade : subjectAverageGrades) {
            System.out.printf("과목: %s, 평균 등급: %s%n", subjectAverageGrade.getSubjectName(), subjectAverageGrade.getAverageGrade());
        }
        System.out.println("\n평균 등급 조회 완료!");
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private static void inquireMandatoryGrades() {
        System.out.println("특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다...");
        String status = inputManager.getStudentStatus(); // 조회할 수강생 상태 status 저장

        List<StudentAverageGrade> studentAverageGrades = scoreService.getAverageGradesByStatusAndSubjectType(status, "MANDATORY");

        for (StudentAverageGrade studentAverageGrade : studentAverageGrades) {
            System.out.println("수강생 이름: " + studentAverageGrade.getStudentName());
            System.out.println("평균 등급: " + studentAverageGrade.getAverageGrade());
        }

        System.out.println("\n등급 조회 완료!");
    }

    //수강생 ID에 해당하는 수강생 반환
    private static Student getStudentById(String studentId) {
        List<Student> students = studentService.getStudents();
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
