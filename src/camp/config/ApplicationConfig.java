package camp.config;

import camp.SubjectRepositoryInitializer;
import camp.model.GradeGenerator;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.service.ScoreService;
import camp.service.StudentService;

public class ApplicationConfig {

    public SubjectRepositoryInitializer subjectRepositoryInitializer() {
        return new SubjectRepositoryInitializer(subjectRepository());
    }

    public StudentService studentService() {
        return StudentService.getInstance(studentRepository(), subjectRepository());
    }

    public ScoreService scoreService() {
        return ScoreService.getInstance(scoreRepository(), studentRepository(), subjectRepository(), new GradeGenerator());
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
