package camp.controller;

import camp.input.InputManager;
import camp.model.Student;
import camp.service.StudentService;
import camp.view.View;
import java.util.List;
import java.util.Set;

public class StudentController {

    private final ScoreController scoreController;
    private final StudentService studentService;
    private final InputManager inputManager;
    private final View view;

    public StudentController(ScoreController scoreController, StudentService studentService,  InputManager inputManager, View view) {
        this.studentService = studentService;
        this.scoreController = scoreController;
        this.inputManager = inputManager;
        this.view = view;
    }

    public void registerStudent() {
        view.printCreateStudentMessage();

        String studentName = inputManager.getStudentName();
        String status = inputManager.getStudentStatus();
        Set<String> subjectIds = inputManager.getSubjectIds();

        studentService.registerStudent(studentName, status, subjectIds);

        view.printCreatedStudentMessage();
    }

    public void inquireStudents() {
        view.printDividingLine();
        view.printInquireStudentMessage();

        List<Student> students = studentService.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            view.printInquireStudentsResult(i, student.getStudentId(), student.getStudentName());
        }

        view.printInquiredStudentMessage();

        String userChoice = inputManager.getUserChoiceFromDetailInquiryOptions();
        if (userChoice.equals("Yes")) {
            scoreController.inquireRoundGradesBySubjectAndStudent();
        }
    }
}
