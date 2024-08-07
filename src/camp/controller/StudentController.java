package camp.controller;

import camp.manager.StudentManager;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;

public class StudentController {
    private final StudentManager studentManager;

    // StudentManager 객체를 초기화
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

    public Student getStudentById(String studentId) {
        return studentManager.getStudentById(studentId);
    }

    public void updateStudentName(String studentId, String newName) {
        studentManager.updateStudentName(studentId, newName);
    }

    public void updateStudentStatus(String studentId, String newStatus) {
        studentManager.updateStudentStatus(studentId, newStatus);
    }

    public void deleteStudent(String studentId) {
        studentManager.deleteStudent(studentId);
    }
}
