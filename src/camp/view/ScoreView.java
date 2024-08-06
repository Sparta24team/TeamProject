package camp.view;

import camp.controller.ScoreController;

import java.util.Scanner;

public class ScoreView {
    private final ScoreController scoreController;
    private static final Scanner sc = new Scanner(System.in);

    public ScoreView(ScoreController scoreController) {
        this.scoreController = scoreController;
    }

    public void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("5. 수강생의 과목별 평균 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
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

    private void createScore() {
        System.out.println("시험 점수를 등록합니다.");
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();
        scoreController.validateStudentId(studentId);

        System.out.println("관리할 과목의 번호를 입력하세요.");
        String subjectId = sc.next();
        scoreController.validateSubjectId(subjectId);

        System.out.println("등록할 회차를 입력하세요.");
        int round = sc.nextInt();
        scoreController.validateRound(round);

        System.out.println("등록할 점수를 입력하세요.");
        int scoreValue = sc.nextInt();
        scoreController.validateScoreValue(scoreValue);

        if (scoreController.existsScore(subjectId, round)) {
            throw new IllegalArgumentException("과목의 회차 점수는 중복되어 등록될 수 없습니다.");
        }

        scoreController.createScore(studentId, subjectId, round, scoreValue);
        System.out.println("\n점수 등록 성공!");
    }

    private void updateRoundScoreBySubject() {
        boolean valueFg = false;
        String studentId = null;
        String subjectId = null;
        int round = 0;

        while (!valueFg) {
            studentId = getStudentId(); // 관리할 수강생 고유 번호

            if (scoreController.validateScoreStudentId(studentId)) {
                System.out.println("존재하지 않은 수강생입니다.");
            } else {
                valueFg = true;
            }
        }
        valueFg = false;
        // 기능 구현 (수정할 과목 및 회차, 점수);
        while (!valueFg) {
            System.out.println("수정할 과목을 입력해주세요");
            subjectId = sc.next().trim();   //과목
            if (scoreController.validateScoreSubjectId(studentId, subjectId)) {
                System.out.println("존재하지 않은 과목입니다.");
            } else {
                valueFg = true;
            }
        }
        valueFg = false;
        while (!valueFg) {
            System.out.println("수정할 회차를 입력해주세요");
            round = sc.nextInt();//회차

            if (scoreController.validateScoreRound(studentId, subjectId, round)) {
                System.out.println("존재하지 않은 회차입니다.");
            } else {
                valueFg = true;
            }
        }
        System.out.println("수정할 점수를 입력해주세요");
        int value = sc.nextInt();       //점수

        scoreController.updateScore(studentId, subjectId, round, value);
        System.out.println("\n점수 수정 성공!");
    }

    private void inquireRoundGradeBySubject() {
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();
        System.out.println("조회할 과목의 번호를 입력하세요.");
        String subjectId = sc.next();

        scoreController.inquireRoundGradeBySubject(studentId, subjectId);
        System.out.println("\n등급 조회 완료!");
    }

    private void inquireMandatoryGrades() {
        System.out.println("조회할 상태를 입력하세요 (Green, Red, Yellow): ");
        String status = sc.next().trim();

        scoreController.inquireMandatoryGrades(status);
        System.out.println("\n등급 조회 완료!");
    }

    private void inquireStudentAverageGrade() {
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();

        scoreController.inquireStudentAverageGrade(studentId);
        System.out.println("\n평균 등급 조회 완료!");
    }

    private String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next().trim();
    }
}
