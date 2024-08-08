package camp.config;

import camp.controller.ScoreController;
import camp.controller.StudentController;
import camp.input.InputManager;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import camp.service.ScoreService;
import camp.service.StudentService;
import camp.view.View;

public class CampManagementApplicationConfig {

    private final View view = new View();
    private final InputManager inputManager = new InputManager(view);

    public void initializeSubjectRepository() {
        SubjectRepositoryInitializer subjectRepositoryInitializer = new SubjectRepositoryInitializer(subjectRepository());
        subjectRepositoryInitializer.initialize();
    }

    public ScoreController scoreController() {
        return new ScoreController(scoreService(), inputManager, view);
    }

    public StudentController studentController() {
        return new StudentController(scoreController(), studentService(), inputManager, view);
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

    public View view() {
        return view;
    }

    public InputManager inputManager() {
        return inputManager;
    }
}
