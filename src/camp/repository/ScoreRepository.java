package camp.repository;

import camp.model.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreRepository {
    private static List<Score> scoreStore = new ArrayList<>();
    private static int scoreIndex = 0;
    private static final String INDEX_TYPE_SCORE = "SC";

    public static String sequence() {
        scoreIndex++;
        return INDEX_TYPE_SCORE + scoreIndex;
    }

    public void addScore(Score score) {
        scoreStore.add(score);
    }

    public List<Score> getAllScores() {
        return scoreStore;
    }
}
