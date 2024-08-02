package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import java.util.*;

/**
 * Notification Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다. main 메서드를 실행하면 프로그램이 실행됩니다. model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요! 프로젝트 구조를 변경하거나 기능을
 * 추가해도 괜찮습니다! 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {

    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(scoreIndex);
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
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
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        //상태값 테스트 코드
        System.out.print("수강생 상태 입력 (예: Green, Red, Yellow): ");
        String status = sc.next();

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, status); // 수강생 인스턴스 생성 예시 코드

        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
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

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        System.out.println("시험 점수를 등록합니다.");
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();
        validateStudentId(studentId);

        System.out.println("관리할 과목의 번호를 입력하세요.");
        String subjectId = sc.next();
        validateSubjectId(subjectId);

        System.out.println("등록할 회차를 입력하세요.");
        int round = sc.nextInt();
        validateRound(round);

        System.out.println("등록할 점수를 입력하세요.");
        int scoreValue = sc.nextInt();
        validateScoreValue(scoreValue);

        if (existsScore(subjectId, round)) {
            throw new IllegalArgumentException("과목의 회차 점수는 중복되어 등록될 수 없습니다.");
        }

        String grade = calculateGrade(subjectId, scoreValue);

        System.out.println("시험 점수를 등록합니다.");
        Score score = new Score(sequence(INDEX_TYPE_SCORE), subjectId, studentId, round, scoreValue, grade);
        scoreStore.add(score);
        System.out.println();
        System.out.printf("%s번 수강생 %s 과목 %d회차 %d점수 등록 성공!\n", studentId, subjectId, round, scoreValue);

        System.out.println("\n점수 등록 성공!");
    }

    private static void validateStudentId(String studentId) {
        boolean isNoneMatch = studentStore.stream()
                .noneMatch(student -> student.getStudentId().equals(studentId));
        if (isNoneMatch) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID입니다.");
        }
    }

    private static void validateSubjectId(String subjectId) {
        boolean isNoneMatch = subjectStore.stream()
                .noneMatch(subject -> subject.getSubjectId().equals(subjectId));
        if (isNoneMatch) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID입니다.");
        }
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

    private static boolean existsScore(String subjectId, int round) {
        return scoreStore.stream()
                .anyMatch(score -> score.getSubjectId().equals(subjectId) && score.getRound() == round);
    }

    private static String calculateGrade(String subjectId, int scoreValue) {
        Subject subject = subjectStore.stream()
                .filter(s -> s.getSubjectId().equals(subjectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목 ID입니다."));

        String subjectType = subject.getSubjectType();
        if (SUBJECT_TYPE_MANDATORY.equals(subjectType)) {
            if (scoreValue >= 95) {
                return "A";
            }
            if (scoreValue >= 90) {
                return "B";
            }
            if (scoreValue >= 80) {
                return "C";
            }
            if (scoreValue >= 70) {
                return "D";
            }
            if (scoreValue >= 60) {
                return "F";
            }
            return "N";
        }
        if (SUBJECT_TYPE_CHOICE.equals(subjectType)) {
            if (scoreValue >= 90) {
                return "A";
            }
            if (scoreValue >= 80) {
                return "B";
            }
            if (scoreValue >= 70) {
                return "C";
            }
            if (scoreValue >= 60) {
                return "D";
            }
            if (scoreValue >= 50) {
                return "F";
            }
            return "N";
        }

        throw new IllegalStateException("유효하지 않은 과목 타입입니다.");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        //과목 ID 입력 받기
        System.out.println("조회할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();

        System.out.println("회차별 등급을 조회합니다...");
        System.out.println("==================================");

        boolean hasScores = false;

        for(Score score : scoreStore) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId)) {
                hasScores = true;
                System.out.printf("회차 = %d%n 등급 = %s%n",
                        score.getRound(),score.getGrade());
                System.out.println("==================================");
            }
        }

        if (!hasScores) {
            System.out.println("수강생의 해당 과목에 대한 기록이 없습니다.");
        }

        System.out.println("\n등급 조회 완료!");
    }

    // 수강생의 과목별 평균 등급 조회
    private static void inquireStudentAverageGrade() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        System.out.println("과목별 평균 등급을 조회합니다...");
        System.out.println("==================================");

        boolean hasScores = false; //수강생이 점수 가지고 있는지 추적

        for (Subject subject : subjectStore) {
            int totalScore = 0; //현재 과목의 점수 합계 초기화
            int scoreCount = 0; //현재 과목의 점수 개수 초기화

            //점수 데이터 순환
            for (Score score : scoreStore) {
                if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subject.getSubjectId())) {
                    totalScore += score.getScoreValue(); //점수 합산
                    scoreCount++; //점수 개수 증가
                    hasScores = true;
                }
            }

            if (scoreCount > 0) {
                // 평균 점수를 계산 => 등급 계산
                int averageScore = totalScore / scoreCount;
                String averageGrade = calculateGrade(subject.getSubjectId(), averageScore);
                System.out.printf("과목: %s, 평균 등급: %s%n", subject.getSubjectName(), averageGrade);
            }
        }

        if (!hasScores) {
            System.out.println("해당 수강생에 대한 점수 데이터가 없습니다.");
        }

        System.out.println("\n평균 등급 조회 완료!");
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private static void inquireMandatoryGrades() {
        System.out.println("특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다...");
        System.out.print("조회할 상태를 입력하세요 (Green, Red, Yellow): ");
        String status = sc.next(); // 조회할 수강생 상태 status 저장

        Set<String> eligibleStudentIds = new HashSet<>(); // 적격 수강생 ID를 저장할 집합

        // 수강생 목록을 순회하며 상태가 일치하는 수강생의 ID를 수집
        for (Student student : studentStore) {
            if (student.getStatus().equalsIgnoreCase(status)) {
                eligibleStudentIds.add(student.getStudentId());
            }
        }

        int studentTotalScore = 0; //필수과목점수 합계
        int studentScoreCount = 0; //필수과목점수 개수

        // 필수 과목 점수 합산
        for (Student student : studentStore) {
            if (eligibleStudentIds.contains(student.getStudentId())) { //studentStore 에 ID가 포함 되어 있는지 확인

                for (Score score : scoreStore) { //scoreStore Id와 일치하는 점수 확인
                    if (score.getStudentId().equals(student.getStudentId())) {
                        Subject foundSubject = null;

                        for (Subject subject : subjectStore) { //subject 현재 점수의 과목 ID와 일치, 필수 과목 확인
                            if (subject.getSubjectId().equals(score.getSubjectId()) &&
                                    subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                                foundSubject = subject;
                                break; // 조건을 만족하는 첫 번째 과목을 찾으면 중단
                            }
                        }
                        if (foundSubject != null) {
                            studentTotalScore += score.getScoreValue(); //필수 과목의 점수를 합산
                            studentScoreCount++; //필수 과목 점수의 개수 +1
                        }
                    }
                }
            }
        }
        //필수 과목 점수 데이터 없는 경우
        if (studentScoreCount == 0) {
            System.out.println("수강생 이름: %s, 필수 과목 점수 데이터가 없습니다.%n");
        } else {
            int averageScore = studentTotalScore / studentScoreCount; //평균 점수 계산
            String averageGrade = calculateGrade(INDEX_TYPE_SUBJECT, averageScore); // 평균 점수로 등급 계산
            System.out.printf("수강생 이름: %s, 필수 과목 평균 등급: %s%n", averageGrade);
        }

        System.out.println("\n등급 조회 완료!");
    }
}
