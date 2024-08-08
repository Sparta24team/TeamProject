package camp;

import camp.application.CampManagementApplication;
import camp.config.CampManagementApplicationConfig;
import camp.input.InputManager;
import camp.service.ScoreService;
import camp.service.StudentService;
import camp.view.View;

public class ApplicationRunner {

    public static void main(String[] args) {
        CampManagementApplicationConfig campManagementApplicationConfig = new CampManagementApplicationConfig();

        StudentService studentService = campManagementApplicationConfig.studentService();
        ScoreService scoreService = campManagementApplicationConfig.scoreService();
        campManagementApplicationConfig.initializeSubjectRepository();
        View view = new View();
        InputManager inputManager = new InputManager(view);

        CampManagementApplication campManagementApplication = new CampManagementApplication(
                studentService, scoreService, inputManager, view
        );

        campManagementApplication.run();
    }
}
