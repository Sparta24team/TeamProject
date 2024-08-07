package camp.controller;

import camp.manager.StudentManager;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;

/**
 * StudentController 클래스 : 학생 관련 작업을 관리하고 처리함.
 -
 * StudentManager 객체를 사용하여 학생 생성, 조회, 수정, 삭제 작업 수행.
 * 학생들이 등록한 과목 및 점수와 관련된 기능을 제공.
 -
 * 주요 기능:
 * - 새로운 학생 생성
 * - 시스템에 등록된 모든 학생 목록 조회
 * - 시스템에 등록된 모든 과목 목록 조회
 * - 특정 학생의 특정 과목에 대한 라운드별 점수 조회
 * - 특정 학생의 정보(ID로 조회) 반환
 * - 특정 학생의 이름 업데이트
 * - 특정 학생의 상태 업데이트
 * - 특정 학생 삭제
 -
 * StudentController 는 학생 관련 요청을 처리하고, 필요한 비즈니스 로직을 StudentManager 에게 위임.
 */

public class StudentController {
    private final StudentManager studentManager;

    // StudentController 객체 초기화
    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository, ScoreRepository scoreRepository) {
        this.studentManager = new StudentManager(studentRepository, subjectRepository, scoreRepository);
    }

    //이름, 과목 목록, 상태를 가진 학생을 생성
    public void createStudent(String studentName, List<Subject> subjects, String status) {
        studentManager.createStudent(studentName, subjects, status);
    }

    //시스템에 등록된 모든 학생 목록을 반환
    public List<Student> getAllStudents() {
        return studentManager.getAllStudents();
    }

    //시스템에 등록된 모든 과목 목록을 반환
    public List<Subject> getAllSubjects() {
        return studentManager.getAllSubjects();
    }

    //특정 학생이 특정 과목에서 각 라운드에 받은 점수를 조회
    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        studentManager.inquireRoundGradeBySubject(studentId, subjectId);
    }

    //특정 학생의 정보를 ID로 조회
    public Student getStudentById(String studentId) {
        return studentManager.getStudentById(studentId);
    }

    //특정 학생의 이름을 업데이트
    public void updateStudentName(String studentId, String newName) {
        studentManager.updateStudentName(studentId, newName);
    }

    //특정 학생의 상태를 업데이트
    public void updateStudentStatus(String studentId, String newStatus) {
        studentManager.updateStudentStatus(studentId, newStatus);
    }

    // 특정 학생을 삭제
    public void deleteStudent(String studentId) {
        studentManager.deleteStudent(studentId);
    }
}
