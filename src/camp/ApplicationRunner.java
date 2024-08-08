package camp;

import camp.application.CampManagementApplication;
import camp.config.CampManagementApplicationConfig;
import camp.controller.ScoreController;
import camp.controller.StudentController;
import camp.input.InputManager;
import camp.view.View;

public class ApplicationRunner {

    public static void main(String[] args) {
        CampManagementApplicationConfig campManagementApplicationConfig = new CampManagementApplicationConfig();

        ScoreController scoreController = campManagementApplicationConfig.scoreController();
        StudentController studentController = campManagementApplicationConfig.studentController();
        InputManager inputManager = campManagementApplicationConfig.inputManager();
        View view = campManagementApplicationConfig.view();
        campManagementApplicationConfig.initializeSubjectRepository();

        CampManagementApplication campManagementApplication = new CampManagementApplication(
                studentController, scoreController, inputManager, view
        );

        campManagementApplication.run();
    }
}
