package camp.manager;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

import java.util.List;

public class StudentManager {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScoreRepository scoreRepository;

    public StudentManager(StudentRepository studentRepository, SubjectRepository subjectRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.scoreRepository = scoreRepository;
    }

    public void createStudent(String studentName, List<Subject> subjects, String status) {
        String studentId = studentRepository.sequence();  // studentRepository로 변경하여 sequence 호출
        Student student = new Student(studentId, studentName, subjects, status);
        studentRepository.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.getAllSubjects();
    }

    public void inquireRoundGradeBySubject(String studentId, String subjectId) {
        Student student = studentRepository.getStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID입니다.");
        }

        Subject subject = subjectRepository.getSubjectById(subjectId);
        if (subject == null) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID입니다.");
        }

        boolean hasScores = false;
        for (Score score : scoreRepository.getAllScores()) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId)) {
                hasScores = true;
                System.out.printf("회차 = %d%n등급 = %s%n", score.getRound(), score.getGrade());
                System.out.println("==================================");
            }
        }

        if (!hasScores) {
            System.out.println("수강생의 해당 과목에 대한 기록이 없습니다.");
        }
    }
}
