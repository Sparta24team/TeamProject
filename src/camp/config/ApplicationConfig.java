package camp.config;

import camp.SubjectRepositoryInitializer;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.service.StudentService;

public class ApplicationConfig {

    public SubjectRepositoryInitializer subjectRepositoryInitializer() {
        return new SubjectRepositoryInitializer(subjectRepository());
    }

    public StudentService studentService() {
        return StudentService.getInstance(studentRepository(), subjectRepository());
    }

    public StudentRepository studentRepository() {
        return StudentRepository.getInstance();
    }

    public ScoreRepository scoreRepository() {
        return ScoreRepository.getInstance();
    }

    public SubjectRepository subjectRepository() {
        return SubjectRepository.getInstance();
    }
}
