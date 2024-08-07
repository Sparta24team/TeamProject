package camp.repository;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    private static List<Subject> subjectStore = new ArrayList<>();
    private static int subjectIndex = 0;
    private static final String INDEX_TYPE_SUBJECT = "SU";

    public static String sequence() {
        subjectIndex++;
        return INDEX_TYPE_SUBJECT + subjectIndex;
    }

    public void initData() {
        subjectStore = List.of(
                new Subject(sequence(), "Java", "MANDATORY"),
                new Subject(sequence(), "객체지향", "MANDATORY"),
                new Subject(sequence(), "Spring", "MANDATORY"),
                new Subject(sequence(), "JPA", "MANDATORY"),
                new Subject(sequence(), "MySQL", "MANDATORY"),
                new Subject(sequence(), "디자인 패턴", "CHOICE"),
                new Subject(sequence(), "Spring Security", "CHOICE"),
                new Subject(sequence(), "Redis", "CHOICE"),
                new Subject(sequence(), "MongoDB", "CHOICE")
        );
    }

    public List<Subject> getAllSubjects() {
        return subjectStore;
    }

    public Subject getSubjectById(String subjectId) {
        return subjectStore.stream()
                .filter(subject -> subject.getSubjectId().equals(subjectId))
                .findFirst()
                .orElse(null);
    }
}
