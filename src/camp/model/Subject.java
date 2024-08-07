package camp.model;

/**
 * Subject: 과목의 정보를 나타냄
 -
 * 과목의 ID, 이름, 유형을 관리
 */

public class Subject {
    private final String subjectId;
    private final String subjectName;
    private final String subjectType;

    // Subject 객체를 초기화
    public Subject(String subjectId, String subjectName, String subjectType) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // 과목 ID를 반환
    public String getSubjectId() {
        return subjectId;
    }

    // 과목 이름을 반환
    public String getSubjectName() {
        return subjectName;
    }

    // 과목 유형을 반환
    public String getSubjectType() {
        return subjectType;
    }

    // 과목 이름을 문자열로 반환
    @Override
    public String toString() {
        return subjectName;
    }

}
