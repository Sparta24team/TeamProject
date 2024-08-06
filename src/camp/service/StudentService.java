package camp.service;

import camp.model.Student;
import camp.model.Subject;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StudentService {

    private static StudentService instance;

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    private StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public static StudentService getInstance(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        if (Objects.isNull(instance)) {
            instance = new StudentService(studentRepository, subjectRepository);
            return instance;
        }
        return instance;
    }

    public void registerStudent(String studentName, String studentStatus, Set<String> subjectIds) {
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);
        if (isMandatorySubjectLessThan(3, subjects) || isChoiceSubjectLessThan(2, subjects)) {
            throw new IllegalArgumentException("필수 과목은 3개 이상, 선택 과목은 2개 이상이어야 합니다.");
        }

        Student student = new Student(studentName, studentStatus, subjects);
        studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    private boolean isMandatorySubjectLessThan(int condition, List<Subject> subjects) {
        long mandatorySubjectCount = subjects.stream()
                .filter(Subject::isMandatory)
                .count();
        return mandatorySubjectCount < condition;
    }

    private boolean isChoiceSubjectLessThan(int condition, List<Subject> subjects) {
        long choiceSubjectCount = subjects.stream()
                .filter(Subject::isChoice)
                .count();
        return choiceSubjectCount < condition;
    }
}
