package camp;

import camp.model.Subject;
import camp.repository.SubjectRepository;
import java.util.List;

public class SubjectRepositoryInitializer {

    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    private final SubjectRepository subjectRepository;

    public SubjectRepositoryInitializer(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void initialize() {
        List<Subject> subjects = List.of(
                new Subject(
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );

        for (Subject subject : subjects) {
            subjectRepository.save(subject);
        }
    }
}
