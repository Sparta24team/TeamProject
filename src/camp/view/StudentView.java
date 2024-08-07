package camp.view;

import camp.controller.StudentController;
import camp.model.Subject;
import camp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * StudentView : 사용자 인터페이스를 통해 수강생 관리 기능을 제공
 -
 * 주요 기능:
 * - 수강생 관리 메뉴 표시 및 사용자 입력 처리
 * - 수강생 등록
 * - 수강생 목록 조회
 * - 수강생 정보 수정 및 삭제
 * - 수강생의 특정 과목에 대한 회차별 등급 조회
 */

public class StudentView {
    private final StudentController studentController;
    private static final Scanner sc = new Scanner(System.in);

    // StudentController 객체 초기화
    public StudentView(StudentController studentController) {
        this.studentController = studentController;
    }

    // 수강생 관리 메뉴 표시하고 사용자 입력을 처리
    public void displayStudentView() {
        boolean flag = true;
        while (flag) {

            System.out.println("\n======================================================");
            System.out.println("||\t\t\t\t수강생 관리 실행 중...\t\t\t\t\t||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("||--------------------------------------------------||");
            System.out.println("||\t\t 1. 수강생 등록 \t\t\t\t\t\t\t\t||");
            System.out.println("||\t\t 2. 수강생 목록 조회 \t\t\t\t\t\t\t||");
            System.out.println("||\t\t 3. 메인 화면 이동 \t\t\t\t\t\t\t||");
            System.out.println("||--------------------------------------------------||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("||\t\t\t 선택하세요 \t\t\t\t\t\t\t\t||");
            System.out.println("||\t\t\t\t\t\t\t\t\t\t\t\t\t||");
            System.out.println("======================================================\n");

            int input = sc.nextInt();

            switch (input) {
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

    // 새로운 수강생 등록
    private void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        String status = "";
        boolean validStatus = false;
        while (!validStatus) {
            System.out.print("수강생 상태 입력 (예: Green, Red, Yellow): ");
            status = sc.next().trim();
            if (status.equalsIgnoreCase("Green") || status.equalsIgnoreCase("Red") || status.equalsIgnoreCase("Yellow")) {
                validStatus = true;
            } else {
                System.out.println("잘못된 상태입니다. 다시 입력해주세요.");
            }
        }

        List<Subject> selectSubjects = new ArrayList<>();
        int mandatoryCount = 0;
        int choiceCount = 0;

        System.out.println("수강과목 입력");
        String subjectId;
        while (true) {
            System.out.print("과목 ID 입력주세요(exit 입력시 종료):");
            subjectId = sc.next();
            if (subjectId.equalsIgnoreCase("exit")) {
                System.out.println("과목 입력을 종료합니다.");
                break;
            }

            Subject subject = findSubjectById(subjectId);

            if (subject == null) {
                System.out.println("존재하지 않는 과목 ID입니다. 다시 입력해주세요.");
            } else if (isDuplicate(selectSubjects, subject)) {
                System.out.println("이미 등록된 과목입니다. 다른 과목을 입력해주세요.");
            } else {
                selectSubjects.add(subject);
                if ("MANDATORY".equals(subject.getSubjectType())) {
                    mandatoryCount++;
                } else if ("CHOICE".equals(subject.getSubjectType())) {
                    choiceCount++;
                }
            }
        }

        if (mandatoryCount < 3 || choiceCount < 2) {
            System.out.println("필수 과목은 3개 이상, 선택 과목은 2개 이상이어야 합니다.");
            return;
        }

        studentController.createStudent(studentName, selectSubjects, status);
        System.out.println("수강생 등록 성공!\n");
    }

    // 과목 ID로 과목을 찾음
    private Subject findSubjectById(String subjectId) {
        List<Subject> subjects = studentController.getAllSubjects(); // 수정된 부분
        for (Subject subject : subjects) {
            if (subject.getSubjectId().equals(subjectId)) {
                return subject;
            }
        }
        return null;
    }

    // 과목 중복 여부를 확인
    private boolean isDuplicate(List<Subject> subjects, Subject subject) {
        for (Subject s : subjects) {
            if (s.getSubjectId().equals(subject.getSubjectId())) {
                return true;
            }
        }
        return false;
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 목록 조회 방법을 선택해주세요.");
            System.out.println("1. 수강생 목록 기본조회");
            System.out.println("2. 상태에 따른 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> basicInquireStudent(); // 수강생 목록 기본조회
                case 2 -> statusInquireStudent(); // 상태에 따른 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }

        }
    }




    // 수강생 목록 및 고유번호, 이름 및 상태와 선택한 과목 조회

    private static void basicInquireStudent() {
        String type;
        List<Student> students = studentController.getAllStudents();
        System.out.println("\n수강생 목록을 조회합니다...");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf((i + 1) + ". 고유 번호 : %s / 이름 : %s / 상태 : %s / 선택한 과목 : %s\n"
                    , student.getStudentId(), student.getStudentName(), student.getStatus(), student.getSubjects());

        }
        System.out.println("\n수강생 목록 조회 성공!");
        System.out.print("수강생 정보 수정 또는 삭제 하시겠습니까?(수정하려면 '1'를 삭제하려면 '2' 입력해주세요.) : ");
        type = sc.next();
        if (type.equals("1")) {
            modifyStudentNameOrStatus();
        }
        if (type.equals("2")) {
            deleteStudent();
        }
        System.out.println("상세정보를 조회하시겠습니까?(조회하려면 'Yes'를 뒤로가려면 '아무키나' 입력해주세요.)");
        type = sc.next();
        if (type.equals("Yes")) {
            inquireRoundGradeBySubject();
        }
    }

    private static void statusInquireStudent(){
        String type;
        List<Student> students = studentController.getAllStudents();
        System.out.println("조회 하고 싶은 상태를 입력해주세요. (Green, Red, Yellow 중 택 1) )");
        type = sc.next();
        boolean flag = false;
        for (Student student : students) {
            if (student.getStatus().equals(type)){
                flag = true;
                System.out.printf("상태 : %s / 이름 : %s / 고유 번호 : %s\n"
                        ,student.getStatus(), student.getStudentName(), student.getStudentId());
            }
        }
        if (!flag){
            System.out.println("일치하는 상태의 수강생이 없습니다.");

        } else {
            System.out.println("\n상태에 따른 수강생 목록 조회 성공!");
        }

        System.out.println("다른 상태인 수강생을 조회하시려면 'Yes'입력 , 나가려면 'exit'를 입력해주세요.");
        type = sc.next();
        if (type.equals("Yes")) {
            statusInquireStudent();
        }
        if (type.equals("exit")) {
            displayStudentView();
        }

    }

    // 수강생의 이름 또는 상태를 수정
    private void modifyStudentNameOrStatus() { // 추가된 코드
        System.out.print("수정할 수강생의 고유번호를 입력해 주세요 :");
        String studentId = sc.next().trim();
        Student student = studentController.getStudentById(studentId);

        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 이름 수정");
        System.out.println("2. 상태 수정");
        System.out.print("선택: ");
        String choice = sc.next();

        if (choice.equals("1")) {
            System.out.print("수정할 이름을 입력해 주세요 : ");
            String newName = sc.next().trim();
            studentController.updateStudentName(studentId, newName);
            System.out.println("수강생 이름 변경 완료");
        }
        if (choice.equals("2")) {
            System.out.print("수정할 상태를 입력해 주세요(예: Green, Red, Yellow) : ");
            String newStatus = sc.next().trim();
            if (newStatus.equalsIgnoreCase("Green") || newStatus.equalsIgnoreCase("Red") || newStatus.equalsIgnoreCase("Yellow")) {
                studentController.updateStudentStatus(studentId, newStatus);
                System.out.println("상태 수정 완료");
            }
        }
    }

    // 수강생을 삭제
    private void deleteStudent() {
        System.out.print("삭제할 수강생의 고유번호를 입력해 주세요 :");
        String studentId = sc.next().trim();
        studentController.deleteStudent(studentId);
        System.out.println("수강생 삭제 완료");
    }

    // 특정 학생의 특정 과목에 대한 회차별 등급을 조회
    private void inquireRoundGradeBySubject() {
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();
        System.out.println("조회할 과목의 번호를 입력하세요.");
        String subjectId = sc.next();

        studentController.inquireRoundGradeBySubject(studentId, subjectId);
        System.out.println("\n등급 조회 완료!");
    }
}
