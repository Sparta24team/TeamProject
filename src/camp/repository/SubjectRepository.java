package camp.repository;

import camp.model.Subject;
import java.util.List;

public class SubjectRepository {

    private static final String SUBJECT_CODE = "SU";

    private final List<Subject> subjectStore;
    private int sequence = 0;

    public SubjectRepository(List<Subject> subjectStore) {
        this.subjectStore = subjectStore;
    }

    public void save(Subject subject) {
        String subjectId = makeSubjectId();
        subject.setSubjectId(subjectId);
        subjectStore.add(subject);
    }

    public List<Subject> findAll() {
        return subjectStore;
    }

    private String makeSubjectId() {
        sequence++;
        return SUBJECT_CODE + sequence;
    }
}
