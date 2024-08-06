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

    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository, ScoreRepository scoreRepository) {
        this.studentManager = new StudentManager(studentRepository, subjectRepository, scoreRepository);
    }

    public void createStudent(String studentName, List<Subject> subjects, String status) {
        studentManager.createStudent(studentName, subjects, status);
    }

    public List<Student> getAllStudents() {
        return studentManager.getAllStudents();
    }

    public List<Subject> getAllSubjects() {
        return studentManager.getAllSubjects();
    }

    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        studentManager.inquireRoundGradeBySubject(studentId, subjectId);
    }
}
