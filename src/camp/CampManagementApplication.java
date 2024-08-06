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
        while (true) {
            try {
                displayMainView();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
            }
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


        List<Subject> subjects = new ArrayList<>();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, subjects, status); // 수강생 인스턴스 생성 예시 코드


        List<Subject> selectSubjects = new ArrayList<>();
        //필수 선택 과목 3,2개 이상인지 확인하기 위한 변수
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

            Subject subject = null;
            for (Subject s : subjectStore) {
                if (s.getSubjectId().equals(subjectId)) {
                    subject = s;
                    break;
                }
            }

            if (subject == null) {
                System.out.println("존재하지 않는 과목 ID입니다. 다시 입력해주세요.");
            } else if (isDuplicate(selectSubjects, subject)) {
                System.out.println("이미 등록된 과목입니다. 다른 과목을 입력해주세요.");
            } else {
                selectSubjects.add(subject);
                if (SUBJECT_TYPE_MANDATORY.equals(subject.getSubjectType())) {
                    mandatoryCount++;
                } else if (SUBJECT_TYPE_CHOICE.equals(subject.getSubjectType())) {
                    choiceCount++;
                }
            }

        }

        // 필수과목 3개 이상, 선택과목 2개 이상
        if (mandatoryCount < 3 || choiceCount < 2) {
            System.out.println("필수 과목은 3개 이상, 선택 과목은 2개 이상이어야 합니다.");
            return;
        }

        // 기능 구현
        student.setSubjects(selectSubjects);
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }

    // 중복 확인
    private static boolean isDuplicate(List<Subject> subjects, Subject subject) {
        for (Subject s : subjects) {
            if (s.getSubjectId().equals(subject.getSubjectId())) {
                return true;
            }
        }
        return false;
    }


    // 수강생 목록 조회


    // 수강생 목록 및 고유번호, 이름 및 상태와 선택한 과목 조회

    private static void inquireStudent() {
        String type;
        System.out.println("\n수강생 목록을 조회합니다...");
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            System.out.printf((i + 1) + ". 고유 번호 : %s / 이름 : %s / 상태 : %s / 선택한 과목 : %s\n"
                    , student.getStudentId(), student.getStudentName(), student.getStatus(), student.getSubjects());

        }
        System.out.println("\n수강생 목록 조회 성공!");
        System.out.print("수강생 정보 수정 또는 삭제 하시겠습니까?(수정하려면 '1'를 삭제하려면 '2' 입력해주세요.) : ");
        type = sc.next();
        if (type.equals("1")) {
            modifyStuedentNameOrStatus();
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


    //수강생 정보 수정
    private static void modifyStuedentNameOrStatus() {
        System.out.print("수정할 수강생의 고유번호를 입력해 주세요 :");
        String studentId = sc.next().trim();

        Student student = getStudentById(studentId);

        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 이름 수정");
        System.out.println("2. 상태 수정");
        System.out.print("선택: ");
        String choice = sc.next();

        if (choice.equals("1")) {
            System.out.print("수정할 이름을 입력해 주세요 : ");
            String newName = sc.next().trim();
            student.setStudentName(newName);
            System.out.println("수강생 이름 변경 완료");
        }
        if (choice.equals("2")) {
            System.out.print("수정할 상태를 입력해 주세요(예: Green, Red, Yellow) : ");
            String newStatus = sc.next().trim();
            if (newStatus.equalsIgnoreCase("Green") || newStatus.equalsIgnoreCase("Red") || newStatus.equalsIgnoreCase("Yellow")) {
                student.setStatus(newStatus);
                System.out.println("상태 수정 완료");
            }

        }

    }

    //수강생 정보 삭제
    private static void deleteStudent() {
        System.out.print("삭제할 수강생의 고유번호를 입력해 주세요 :");
        String StudentId = sc.next().trim();

        Student student = getStudentById(StudentId);
        Score score = getScoreById(StudentId);
        studentStore.remove(student);
        scoreStore.remove(score);

        System.out.println("수강생 삭제 완료");


    }

    //1. 866766 / park
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
        return sc.next().trim();
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

        if (existsScore(studentId, subjectId, round)) {
            throw new IllegalArgumentException("과목의 회차 점수 는 중복되어 등록될 수 없습니다.");
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

    public static boolean validateScoreStudentId(String studentId) {
        return scoreStore.stream()
                .noneMatch(student -> student.getStudentId().equals(studentId));
    }

    public static boolean validateScoreSubjectId(String studentId, String subjectId) {
        return scoreStore.stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId));
    }

    public static boolean validateScoreRound(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .noneMatch(score -> score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round);
    }

    private static boolean existsScore(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .anyMatch(score -> score.getSubjectId().equals(subjectId) &&
                        score.getRound() == round &&
                        score.getStudentId() == studentId);
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

    // 수강생의 과목별 회차 점수 수정     set
    private static void updateRoundScoreBySubject() {
        boolean valueFg = false;
        String studentId = null;
        String subjectId = null;
        int round = 0;


        while (!valueFg) {
            studentId = getStudentId(); // 관리할 수강생 고유 번호

            if (validateScoreStudentId(studentId)) {
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
            if (validateScoreSubjectId(studentId, subjectId)) {
                System.out.println("존재하지 않은 과목입니다.");
            } else {
                valueFg = true;
            }
        }
        valueFg = false;
        while (!valueFg) {
            System.out.println("수정할 회차를 입력해주세요");
            round = sc.nextInt();//회차

            if (validateScoreRound(studentId, subjectId, round)) {
                System.out.println("존재하지 않은 회차입니다.");
            } else {
                valueFg = true;
            }
        }
        System.out.println("수정할 점수를 입력해주세요");
        int value = sc.nextInt();       //점수
            /*
            private static void validateScoreValue(int scoreValue) {
        if (scoreValue < 0 || 100 < scoreValue) {
            throw new IllegalArgumentException("점수는 100 초과 및 음수가 될 수 없습니다. (점수 범위: 0 ~ 100)");
        }
    } boolaen 으로 받아서 초기화 되지 않도록 설정
             */
        System.out.println("시험 점수를 수정합니다...");
        System.out.println("==================================");
        // 기능 구현
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(studentId) && (
                    score.getSubjectId().equals(subjectId) &&
                            score.getRound() == round)) {
                score.setValue(value);

                break;
            }
        }
        for (Score score : scoreStore) {
            System.out.println("사용자 id: " + score.getStudentId());
            System.out.println("과목  id: " + score.getSubjectId());
            System.out.println("회차    : " + score.getRound());
            System.out.println("점수    : " + score.getValue());
            System.out.println("==============================");
        }
        System.out.println("\n점수 수정 성공!");

    }

    // 수강생의 특정 과목 회차별 등급 조회
    // 코드 순서 변경 : 수강생 ID, 과목 ID 유효성 각각 조회로 변경함
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        //수강생 ID 유효성 검사
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("존재하지 않는 수강생 ID 입니다.");
            return;
        }

        //과목 ID 입력 받기
        System.out.println("조회할 과목의 고유 번호를 입력하세요 : ");
        String subjectId = sc.next();

        // 과목 ID 유효성 검사
        Subject subject = getSubjectById(subjectId);
        if (subject == null) {
            System.out.println("존재하지 않는 과목 ID입니다.");
            return;
        }

        System.out.println("회차별 등급을 조회합니다...");
        System.out.println("==================================");

        boolean hasScores = false;
        for (Score score : scoreStore) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId)) {
                hasScores = true;
                System.out.printf("회차 = %d%n 등급 = %s%n",
                        score.getRound(), score.getGrade());
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

        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("존재하지 않는 수강생 ID입니다.");
            return;
        }

        System.out.println("과목별 평균 등급을 조회합니다...");
        System.out.println("==================================");

        boolean hasScores = false; //수강생이 점수 가지고 있는지 추적
        for (Subject subject : subjectStore) { // 모든 과목을 참조
            int totalScore = 0; // 현재 과목의 점수 합계 초기화
            int scoreCount = 0; // 현재 과목의 점수 개수 초기화

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
        String status = sc.next().trim(); // 조회할 수강생 상태 status 저장

        List<Student> eligibleStudents = new ArrayList<>(); // 적격 수강생 ID를 저장할 집합

        // 수강생 목록을 순회하며 상태가 일치하는 수강생의 ID를 수집
        for (Student student : studentStore) {
            if (student.getStatus().equalsIgnoreCase(status)) {
                eligibleStudents.add(student);
            }
        }
        // 적격 수강생이 없을 경우
        if (eligibleStudents.isEmpty()) {
            System.out.println("해당 상태의 수강생이 없습니다.");
            return;
        }

        int totalScore = 0;
        int scoreCount = 0;

        Map<String, List<Integer>> studentScoresMap = new HashMap<>();

        // 필수 과목 점수 합산
        for (Student student : eligibleStudents) {
            List<Integer> studentScores = new ArrayList<>();
            for (Subject subject : subjectStore) {
                if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    for (Score score : scoreStore) {
                        if (score.getStudentId().equals(student.getStudentId()) && score.getSubjectId().equals(subject.getSubjectId())) {
                            totalScore += score.getScoreValue();
                            scoreCount++;
                            studentScores.add(score.getScoreValue());
                        }
                    }
                }
            }
            studentScoresMap.put(student.getStudentId(), studentScores);
        }
        //필수 과목 점수 데이터 없는 경우
        if (scoreCount == 0) {
            System.out.println("필수 과목 점수 데이터가 없습니다.");
        } else {
            try {
                int averageScore = totalScore / scoreCount;
                String averageGrade = calculateGradeForScore(averageScore);
                System.out.printf("상태: %s, 필수 과목 평균 등급: %s%n", status, averageGrade);
                for (Map.Entry<String, List<Integer>> entry : studentScoresMap.entrySet()) { // 수정햇슈
                    System.out.printf("수강생 ID: %s, 점수: %s%n", entry.getKey(), entry.getValue()); // 수정햇슈
                }
            } catch (Exception e) {
                System.out.println("등급 계산 중 오류 발생: " + e.getMessage());
            }
        }
        System.out.println("\n등급 조회 완료!");
    }

    // 점수를 위한 등급 계산 메서드 추가
    private static String calculateGradeForScore(int scoreValue) {
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

    //수강생 ID에 해당하는 수강생 반환
    private static Student getStudentById(String studentId) {
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    //주어진 과목 ID에 해당하는 과목 반환
    private static Subject getSubjectById(String subjectId) {
        for (Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId)) {
                return subject;
            }
        }
        return null;
    }

    private static Score getScoreById(String scoreId) {
        for (Score score : scoreStore) {
            if (score.getStudentId().equals(scoreId)) {
                return score;
            }
        }
        return null;
    }
}
