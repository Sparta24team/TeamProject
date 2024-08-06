package camp.repository;

import camp.model.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SubjectRepository {

    private static final String SUBJECT_CODE = "SU";

    private static SubjectRepository instance;

    private final List<Subject> subjectStore;
    private int sequence = 0;

    private SubjectRepository() {
        this.subjectStore = new ArrayList<>();
    }

    public static SubjectRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubjectRepository();
            return instance;
        }
        return instance;
    }

    public void save(Subject subject) {
        String subjectId = makeSubjectId();
        subject.setSubjectId(subjectId);
        subjectStore.add(subject);
    }

    public List<Subject> findAll() {
        return subjectStore;
    }

    public List<Subject> findAllById(Set<String> subjectIds) {
        return subjectStore.stream()
                .filter(subject -> subjectIds.contains(subject.getSubjectId()))
                .collect(Collectors.toList());
    }

    private String makeSubjectId() {
        sequence++;
        return SUBJECT_CODE + sequence;
    }
}
