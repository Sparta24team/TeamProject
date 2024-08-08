package camp.view;

public class View {

    public void printExitProgramMessage() {
        System.out.println("프로그램을 종료합니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println("에러가 발생했습니다.");
        System.out.println("에러 메시지: " + message);
    }

    public void printExitProgramMessageOnError() {
        System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
    }

    public void printDividingLine() {
        System.out.println("\n==================================");
    }

    public void printApplicationStartMessage() {
        System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
    }

    public void printWrongInputMessage() {
        System.out.println("잘못된 입력입니다.");
    }

    public void printReturnMessage() {
        System.out.println("되돌아갑니다!");
    }

    public void printRunningStudentManagementMessage() {
        System.out.println("수강생 관리 실행 중...");
    }

    public void printMoveToMainViewMessage() {
        System.out.println("메인 회면으로 이동합니다.");
    }

    public void printCreateStudentMessage() {
        System.out.println("\n수강생을 등록합니다...");
    }

    public void printCreatedStudentMessage() {
        System.out.println("수강생 등록 성공!\n");
    }

    public void printInquireStudentMessage() {
        System.out.println("\n수강생 목록을 조회합니다...");
    }

    public void printInquiredStudentMessage() {
        System.out.println("\n수강생 목록 조회 성공!");
    }

    public void printRunningScoreManagementMessage() {
        System.out.println("점수 관리 실행 중...");
    }

    public void printCreateScoreMessage() {
        System.out.println("시험 점수를 등록합니다.");
    }

    public void printCreatedScoreMessage(String studentId, String subjectId, int round, int scoreValue) {
        System.out.printf("%s번 수강생 %s 과목 %d회차 %d점수 등록 성공!\n", studentId, subjectId, round, scoreValue);
    }

    public void printUpdateScoreMessage() {
        System.out.println("시험 점수를 수정합니다...");
    }

    public void printUpdatedScoreMessage() {
        System.out.println("\n점수 수정 성공!");
    }

    public void printUpdatedDetail(String studentId, String subjectId, int round, int value) {
        System.out.println("사용자 id: " + studentId);
        System.out.println("과목  id: " + subjectId);
        System.out.println("회차    : " + round);
        System.out.println("점수    : " + value);
    }

    public void printInquireGradeMessage() {
        System.out.println("회차별 등급을 조회합니다...");
    }

    public void printDoesNotExistStudentSubjectData() {
        System.out.println("수강생의 해당 과목에 대한 기록이 없습니다.");
    }

    public void printInquiredGradeResult(int round, String grade) {
        System.out.printf("회차 = %d%n 등급 = %s%n", round, grade);
    }

    public void printInquiredGradeMessage() {
        System.out.println("\n등급 조회 완료!");
    }

    public void printInquireAverageGradeMessage() {
        System.out.println("과목별 평균 등급을 조회합니다...");
    }

    public void printInquiredAverageGradeMessage() {
        System.out.println("\n평균 등급 조회 완료!");
    }

    public void printInquireAverageGradeFromMandatorySubjectByStatusMessage() {
        System.out.println("특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다...");
    }

    public void printInquireStudentAverageGradeResult(String subjectName, String averageGrade) {
        System.out.printf("과목: %s, 평균 등급: %s%n", subjectName, averageGrade);
    }

    public void printInquireAverageGradeFromMandatorySubjectByStatusResult(String studentName, String averageGrade) {
        System.out.println("수강생 이름: " + studentName);
        System.out.println("평균 등급: " + averageGrade);
    }

    public void printInquireStudentsResult(int index, String studentId, String studentName) {
        System.out.printf((index + 1) + ". 고유 번호 : %s / 이름 : %s\n", studentId, studentName);
    }

    public void printManagementOptions() {
        System.out.println("1. 수강생 관리");
        System.out.println("2. 점수 관리");
        System.out.println("3. 프로그램 종료");
    }

    public void printChooseManagementOptionMessage() {
        System.out.print("관리 항목을 선택하세요...");
    }

    public void printStudentManagementOptions() {
        System.out.println("1. 수강생 등록");
        System.out.println("2. 수강생 목록 조회");
        System.out.println("3. 메인 화면 이동");
    }

    public void printInputStudentNameMessage() {
        System.out.print("수강생 이름 입력: ");
    }

    public void printInputStudentStatusMessage() {
        System.out.print("수강생 상태 입력를 입력해주세요 (예: Green, Red, Yellow): ");
    }

    public void printWrongStudentStatusMessage() {
        System.out.println("잘못된 상태입니다. 다시 입력해주세요.");
    }

    public void printStartInputSubjectMessage() {
        System.out.println("수강과목 입력");
    }

    public void printInputSubjectIdMessageAndExitOption() {
        System.out.print("과목 ID 입력주세요(exit 입력시 종료):");
    }

    public void exitInputSubjectMessage() {
        System.out.println("과목 입력을 종료합니다.");
    }

    public void printInputDetailInquiryOptionMessage() {
        System.out.println("상세정보를 조회하시겠습니까?(조회하려면 'Yes'를 뒤로가려면 '아무키나' 입력해주세요.)");
    }

    public void printScoreManagementOptions() {
        System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
        System.out.println("2. 수강생의 과목별 회차 점수 수정");
        System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("4. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
        System.out.println("5. 수강생의 과목별 평균 등급 조회");
        System.out.println("6. 메인 화면 이동");
    }

    public void printInputStudentIdMessage() {
        System.out.println("수강생의 ID를 입력하세요.");
    }

    public void printInputSubjectIdMessage() {
        System.out.println("과목의 ID를 입력하세요.");
    }

    public void printInputRoundMessage() {
        System.out.println("회차를 입력하세요.");
    }

    public void printScoreValueMessage() {
        System.out.println("점수를 입력하세요.");
    }
}
