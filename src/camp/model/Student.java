package camp.model;

import java.util.List;

/**
 * Student 클래스는 학생의 정보를 나타냄
 -
 * 학생의 ID, 이름, 과목 목록, 상태를 관리
 */

public class Student {
    private final String studentId;
    private final List<Subject> subjects;
    private String studentName;
    private String status;

    //Student 객체 초기화
    public Student(String studentId, String studentName, List<Subject> subjects, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjects = subjects;
        this.status = status;
    }

    // 학생 ID를 반환
    public String getStudentId() {
        return studentId;
    }

    // 학생 이름을 반환
    public String getStudentName() {
        return studentName;
    }

    // 학생이 수강하는 과목 목록을 반환
    public List<Subject> getSubjects() {
        return subjects;
    }

    // 학생 상태를 반환
    public String getStatus() {
        return status;
    }

    // 학생 상태를 설정
    public void setStatus(String status) {
        this.status = status;
    }

    // 학생 이름을 설정
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
