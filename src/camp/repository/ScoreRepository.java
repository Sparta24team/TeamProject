package camp.repository;

import camp.model.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
