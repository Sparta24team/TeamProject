package camp;

import camp.dataField.DataField;
import camp.management.Management;
import camp.management.Search;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

/**
 * Notification Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다. main 메서드를 실행하면 프로그램이 실행됩니다. model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요! 프로젝트 구조를 변경하거나 기능을
 * 추가해도 괜찮습니다! 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(DataField.scoreIndex);
        DataField.setInitData();
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
            System.out.println("\n==========================================");
            System.out.println("=\t  내일배움캠프 수강생 관리 프로그램 실행 중..  =");
            System.out.println("=\t                                     =");
            System.out.println("=----------------------------------------=");
            System.out.println("=\t         1. Student Management       =");
            System.out.println("=\t         2. Score Management         =");
            System.out.println("=\t         3. exit program             =");
            System.out.println("=----------------------------------------=");
            System.out.println("=\t                                     =");
            System.out.println("=\t          Choice Management          =");
            System.out.println("=\t                                     =");
            System.out.println("==========================================\n");
            int input = sc.nextInt();

            switch (input) {
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
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> Management.createStudent(); // 수강생 등록
                case 2 -> Search.inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }

        }
    }
    private static void displayScoreView() {
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
                case 1 -> Management.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> Management.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> Search.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> Search.inquireMandatoryGrades(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 5 -> Search.inquireStudentAverageGrade(); // 수강생의 과목별 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

}
