package camp.io;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputManager {

    private static Scanner sc = new Scanner(System.in);

    public int getUserChoiceFromManagementOptions() {
        System.out.println("1. 수강생 관리");
        System.out.println("2. 점수 관리");
        System.out.println("3. 프로그램 종료");
        System.out.print("관리 항목을 선택하세요...");
        return sc.nextInt();
    }

    public int getUserChoiceFromStudentManagementOptions() {
        System.out.println("1. 수강생 등록");
        System.out.println("2. 수강생 목록 조회");
        System.out.println("3. 메인 화면 이동");
        System.out.print("관리 항목을 선택하세요...");
        return sc.nextInt();
    }

    public String getStudentName() {
        System.out.print("수강생 이름 입력: ");
        return sc.next();
    }

    public String getStudentStatus() {
        String status = "";
        boolean isNotValidStatus = true;
        while (isNotValidStatus) {
            System.out.print("수강생 상태 입력를 입력해주세요 (예: Green, Red, Yellow): ");
            status = sc.next().trim();
            if (status.equalsIgnoreCase("Green") || status.equalsIgnoreCase("Red") || status.equalsIgnoreCase("Yellow")) {
                isNotValidStatus = false;
            } else {
                System.out.println("잘못된 상태입니다. 다시 입력해주세요.");
            }
        }
        return status;
    }

    public Set<String> getSubjectIds() {
        System.out.println("수강과목 입력");
        Set<String> subjectIds = new HashSet<>();
        while (true) {
            System.out.print("과목 ID 입력주세요(exit 입력시 종료):");
            String subjectId = sc.next();
            if (subjectId.equalsIgnoreCase("exit")) {
                System.out.println("과목 입력을 종료합니다.");
                break;
            }
            subjectIds.add(subjectId);
        }
        return subjectIds;
    }

    public String getUserChoiceFromDetailInquiryOptions() {
        System.out.println("상세정보를 조회하시겠습니까?(조회하려면 'Yes'를 뒤로가려면 '아무키나' 입력해주세요.)");
        return sc.next();
    }

    public int getUserChoiceFromUserManagementOptions() {
        System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
        System.out.println("2. 수강생의 과목별 회차 점수 수정");
        System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("4. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
        System.out.println("5. 수강생의 과목별 평균 등급 조회");
        System.out.println("6. 메인 화면 이동");
        System.out.print("관리 항목을 선택하세요...");
        return sc.nextInt();
    }

    public String getStudentId() {
        System.out.println("수강생의 ID를 입력하세요.");
        return sc.next();
    }

    public String getSubjectId() {
        System.out.println("과목의 ID를 입력하세요.");
        return sc.next();
    }

    public int getRound() {
        System.out.println("회차를 입력하세요.");
        return sc.nextInt();
    }

    public int getScoreValue() {
        System.out.println("점수를 입력하세요.");
        return sc.nextInt();
    }
}
