package camp.management;

import camp.dataField.DataField;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

public class Search extends DataField {

    public static void inquireStudent() {

        String type;
        System.out.println("\n수강생 목록을 조회합니다...");
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            System.out.printf((i + 1) + ". 고유 번호 : %s / 이름 : %s\n", student.getStudentId(), student.getStudentName());

        }
        System.out.println("\n수강생 목록 조회 성공!");
        System.out.println("상세정보를 조회하시겠습니까?(조회하려면 'Yes'를 뒤로가려면 '아무키나' 입력해주세요.)");
        type = sc.next();
        if (type.equals("Yes")) {
            inquireRoundGradeBySubject();
        }
    }

    public static void inquireRoundGradeBySubject() {
        System.out.print("\n관리할 수강생의 ID를 입력하세요 : ");
        String studentId = sc.next(); // 관리할 수강생 고유 번호

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

    public static void inquireMandatoryGrades() {
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
                String averageGrade = calculateSubjectScoreGrade("MANDATORY",averageScore);
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
    // 수강생의 과목별 평균 등급 조회
    public static void inquireStudentAverageGrade() {
        System.out.print("\n관리할 수강생의 ID를 입력하세요 : ");
        String studentId = sc.next(); // 관리할 수강생 고유 번호

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
                String averageGrade = Management.calculateGrade(subject.getSubjectId(), averageScore);
                System.out.printf("과목: %s, 평균 등급: %s%n", subject.getSubjectName(), averageGrade);
            }
        }

        if (!hasScores) {
            System.out.println("해당 수강생에 대한 점수 데이터가 없습니다.");
        }

        System.out.println("\n평균 등급 조회 완료!");
    }

}
