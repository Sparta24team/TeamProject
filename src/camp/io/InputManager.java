package camp.io;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputManager {

    private static Scanner sc = new Scanner(System.in);

    private final View view;

    public InputManager(View view) {
        this.view = view;
    }

    public int getUserChoiceFromManagementOptions() {
        view.printManagementOptions();
        view.printChooseManagementOptionMessage();
        return sc.nextInt();
    }

    public int getUserChoiceFromStudentManagementOptions() {
        view.printStudentManagementOptions();
        view.printChooseManagementOptionMessage();
        return sc.nextInt();
    }

    public String getStudentName() {
        view.printInputStudentNameMessage();
        return sc.next();
    }

    public String getStudentStatus() {
        String status = "";
        boolean isNotValidStatus = true;
        while (isNotValidStatus) {
            view.printInputStudentStatusMessage();
            status = sc.next().trim();
            if (status.equalsIgnoreCase("Green") || status.equalsIgnoreCase("Red") || status.equalsIgnoreCase("Yellow")) {
                isNotValidStatus = false;
            } else {
                view.printWrongStudentStatusMessage();
            }
        }
        return status;
    }

    public Set<String> getSubjectIds() {
        view.printStartInputSubjectMessage();
        Set<String> subjectIds = new HashSet<>();
        while (true) {
            view.printInputSubjectIdMessageAndExitOption();
            String subjectId = sc.next();
            if (subjectId.equalsIgnoreCase("exit")) {
                view.exitInputSubjectMessage();
                break;
            }
            subjectIds.add(subjectId);
        }
        return subjectIds;
    }

    public String getUserChoiceFromDetailInquiryOptions() {
        view.printInputDetailInquiryOptionMessage();
        return sc.next();
    }

    public int getUserChoiceFromScoreManagementOptions() {
        view.printScoreManagementOptions();
        view.printChooseManagementOptionMessage();
        return sc.nextInt();
    }

    public String getStudentId() {
        view.printInputStudentIdMessage();
        return sc.next();
    }

    public String getSubjectId() {
        view.printInputSubjectIdMessage();
        return sc.next();
    }

    public int getRound() {
        view.printInputRoundMessage();
        return sc.nextInt();
    }

    public int getScoreValue() {
        view.printScoreValueMessage();
        return sc.nextInt();
    }
}
