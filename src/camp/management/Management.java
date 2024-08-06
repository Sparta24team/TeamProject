package camp.management;

import camp.dataField.DataField;
import camp.dataField.Valide;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management extends DataField {

    // 수강생 등록
    public static void createStudent() {
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
            } else if (Valide.isDuplicate(selectSubjects, subject)) {
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
    // 수강생 점수 등록
    public static void createScore() {
        System.out.println("시험 점수를 등록합니다.");
        System.out.println("관리할 수강생의 번호를 입력하세요.");
        String studentId = sc.next();
        Valide.validateStudentId(studentId);

        System.out.println("관리할 과목의 번호를 입력하세요.");
        String subjectId = sc.next();
        Valide.validateSubjectId(subjectId);

        System.out.println("등록할 회차를 입력하세요.");
        int round = sc.nextInt();
        Valide.validateRound(round);

        System.out.println("등록할 점수를 입력하세요.");
        int scoreValue = sc.nextInt();
        Valide.validateScoreValue(scoreValue);

        if (Valide.existsScore(studentId, subjectId, round)) {
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

    //수강생 점수 수정
    public static void updateRoundScoreBySubject() {
        boolean valueFg = false;
        String studentId = null;
        String subjectId = null;
        int round = 0;


        while(!valueFg) {
             // 관리할 수강생 고유 번호
            System.out.print("\n관리할 수강생의 번호를 입력하시오...");
            studentId = sc.next().trim();
            if (Valide.validateScoreStudentId(studentId)) {
                System.out.println("존재하지 않은 수강생입니다.");
            } else {
                valueFg = true;
            }
        }
        valueFg = false;
        // 기능 구현 (수정할 과목 및 회차, 점수);
        while(!valueFg) {
            System.out.println("수정할 과목을 입력해주세요");
            subjectId = sc.next().trim();   //과목
            if (Valide.validateScoreSubjectId(studentId,subjectId)) {
                System.out.println("존재하지 않은 과목입니다.");
            }else {
                valueFg = true;
            }
        }
        valueFg = false;
        while(!valueFg) {
            System.out.println("수정할 회차를 입력해주세요");
            round = sc.nextInt();//회차

            if (Valide.validateScoreRound(studentId,subjectId,round)){
                System.out.println("존재하지 않은 회차입니다.");
            }else{
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
                    score.getSubjectId().equals(subjectId)&&
                            score.getRound() == round)){
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

    public static String calculateGrade(String subjectId, int scoreValue) {
        Subject subject = subjectStore.stream()
                .filter(s -> s.getSubjectId().equals(subjectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목 ID입니다."));

        String subjectType = subject.getSubjectType();
        String grade = calculateSubjectScoreGrade(subjectType,scoreValue);
        return grade;
    }

}
