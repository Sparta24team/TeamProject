package camp;

import camp.controller.ScoreController;
import camp.controller.StudentController;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.view.ScoreView;
import camp.view.StudentView;
import java.util.Scanner;

/**
 * Notification Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다. main 메서드를 실행하면 프로그램이 실행됩니다. model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요! 프로젝트 구조를 변경하거나 기능을
 * 추가해도 괜찮습니다! 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */

public class CampManagementApplication {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentRepository studentRepository = new StudentRepository();
    private static final SubjectRepository subjectRepository = new SubjectRepository();
    private static final ScoreRepository scoreRepository = new ScoreRepository();
    private static final StudentController studentController = new StudentController(studentRepository, subjectRepository, scoreRepository);
    private static final ScoreController scoreController = new ScoreController(scoreRepository, studentRepository, subjectRepository);
    private static final StudentView studentView = new StudentView(studentController);
    private static final ScoreView scoreView = new ScoreView(scoreController);

    /**
     * 프로그램의 메인 메서드로, 프로그램을 실행
     [param] args 실행 인자
     */
    public static void main(String[] args) {
        setInitData(); //초기 데이터 설정
        while (true) {
            try {
                displayMainView(); //메인 화면 표시
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
            }
        }
    }

    //초기 데이터 설정
    private static void setInitData() {
        subjectRepository.initData();
    }

    //메인 화면을 표시하고 사용자 입력을 처리함 [throws] 인터럽스 예외
    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {

            System.out.println("\n======================================================");
            System.out.println("||\t\t내일배움캠프 수강생 관리 프로그램 실행 중..\t\t||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("||--------------------------------------------------||");
            System.out.println("||\t\t 1. 수강생 관리 메뉴 \t\t\t\t\t\t\t||");
            System.out.println("||\t\t 2. 점수 관리 메뉴 \t\t\t\t\t\t\t||");
            System.out.println("||\t\t 3. 메인 화면 이동 \t\t\t\t\t\t\t||");
            System.out.println("||--------------------------------------------------||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("||\t\t\t 선택하세요 \t\t\t\t\t\t\t\t||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("======================================================\n");


            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentView.displayStudentView(); // 수강생 관리
                case 2 -> scoreView.displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
