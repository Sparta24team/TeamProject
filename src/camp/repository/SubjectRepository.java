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

    public List<Subject> findAllById(Set<String> subjectIds) {
        return subjectStore.stream()
                .filter(subject -> subjectIds.contains(subject.getSubjectId()))
                .collect(Collectors.toList());
    }

    private String makeSubjectId() {
        sequence++;
        return SUBJECT_CODE + sequence;
    }

    public boolean doesNotExist(String subjectId) {
        return subjectStore.stream()
                .noneMatch(subject -> subject.isSameSubjectId(subjectId));
    }

    public Subject findById(String subjectId) {
        return subjectStore.stream()
                .filter(subject -> subject.isSameSubjectId(subjectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));
    }

    public List<Subject> findAllBySubjectType(String subjectType) {
        return subjectStore.stream()
                .filter(subject -> subject.isSameSubjectType(subjectType))
                .collect(Collectors.toList());
    }
}
