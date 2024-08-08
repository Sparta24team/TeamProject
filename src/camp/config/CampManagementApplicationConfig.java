package camp.config;

import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.service.ScoreService;
import camp.service.StudentService;

public class CampManagementApplicationConfig {

    public void initializeSubjectRepository() {
        SubjectRepositoryInitializer subjectRepositoryInitializer = new SubjectRepositoryInitializer(subjectRepository());
        subjectRepositoryInitializer.initialize();
    }

    public StudentService studentService() {
        return StudentService.getInstance(studentRepository(), subjectRepository());
    }

    public ScoreService scoreService() {
        return ScoreService.getInstance(scoreRepository(), studentRepository(), subjectRepository());
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
