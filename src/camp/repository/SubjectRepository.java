package camp.repository;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * SubjectRepository: 과목 데이터를 저장하고 관리
 -
 * 과목 데이터를 저장하는 메서드와 과목 데이터를 조회하는 메서드를 제공
 -
 * 주요 기능:
 * - 과목 데이터 초기화
 * - 새로운 과목 ID 시퀀스 생성
 * - 모든 과목 데이터 조회
 * - 과목 ID로 과목 조회
 */

public class SubjectRepository {
    private static List<Subject> subjectStore = new ArrayList<>(); // 과목 데이터를 저장하는 리스트
    private static int subjectIndex = 0; // 과목 ID 시퀀스 생성 인덱스
    private static final String INDEX_TYPE_SUBJECT = "SU";

    /**
     * 과목 ID 시퀀스를 생성합니다. [return] 생성된 과목 ID
     */
    public static String sequence() {
        subjectIndex++;
        return INDEX_TYPE_SUBJECT + subjectIndex;
    }

    //초기 데이터 설정
    public void initData() {
        subjectStore = List.of(
                new Subject(sequence(), "Java", "MANDATORY"),
                new Subject(sequence(), "객체지향", "MANDATORY"),
                new Subject(sequence(), "Spring", "MANDATORY"),
                new Subject(sequence(), "JPA", "MANDATORY"),
                new Subject(sequence(), "MySQL", "MANDATORY"),
                new Subject(sequence(), "디자인 패턴", "CHOICE"),
                new Subject(sequence(), "Spring Security", "CHOICE"),
                new Subject(sequence(), "Redis", "CHOICE"),
                new Subject(sequence(), "MongoDB", "CHOICE")
        );
    }

    /**
     * 모든 과목 데이터를 반환.[return] 모든 과목 데이터가 포함된 리스트
     */
    public List<Subject> getAllSubjects() {
        return subjectStore;
    }

    /**
     * 과목 ID로 과목을 조회
     [param] subjectId 조회할 과목 ID
     [return] 조회된 과목 객체, 존재하지 않으면 null
     */
    public Subject getSubjectById(String subjectId) {
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }
}
