package camp.repository;

import camp.model.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoreRepository {

    private static final String SCORE_CODE = "SC";

    private static ScoreRepository instance;

    private final List<Score> scoreStore;
    private int sequence = 0;

    private ScoreRepository() {
        this.scoreStore = new ArrayList<>();
    }

    public static ScoreRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ScoreRepository();
            return instance;
        }
        return instance;
    }

    public void save(Score score) {
        String scoreId = makeScoreId();
        score.setScoreId(scoreId);
        scoreStore.add(score);
    }

    public List<Score> findAll() {
        return scoreStore;
    }

    private String makeScoreId() {
        sequence++;
        return SCORE_CODE + sequence;
    }

    public List<Score> findByStudentIdAndSubjectId(String studentId, String subjectId) {
        return scoreStore.stream()
                .filter(score -> score.isSameStudentId(studentId))
                .filter(score -> score.isSameSubjectId(subjectId))
                .collect(Collectors.toList());
    }

    public boolean existScore(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .anyMatch(
                        score -> score.isSameStudentId(studentId) &&
                                score.isSameSubjectId(subjectId) &&
                                score.isSameRound(round)
                );
    }

    public boolean doesNotExist(String studentId, String subjectId, int round) {
        return !existScore(studentId, subjectId, round);
    }

    public Score updateScore(String studentId, String subjectId, int round, int scoreValue, String grade) {
        Score score = scoreStore.stream()
                .filter(s -> s.isSameStudentId(studentId))
                .filter(s -> s.isSameSubjectId(subjectId))
                .filter(s -> s.isSameRound(round))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 점수입니다."));

        score.updateValue(scoreValue);
        score.updateGrade(grade);

        return score;
    }

    public List<Score> findAllByStudentIdAndSubjectId(String studentId, String subjectId) {
        return scoreStore.stream()
                .filter(score -> score.isSameStudentId(studentId))
                .filter(score -> score.isSameSubjectId(subjectId))
                .collect(Collectors.toList());
    }
}
