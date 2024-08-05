package camp.repository;

import camp.model.Score;
import java.util.List;

public class ScoreRepository {

    private static final String SCORE_CODE = "SC";

    private final List<Score> scoreStore;
    private int sequence = 0;

    public ScoreRepository(List<Score> scoreStore) {
        this.scoreStore = scoreStore;
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
}
