package camp.repository;

import camp.model.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * ScoreRepository: 점수 데이터를 저장하고 관리
 -
 * 점수 데이터를 저장하는 메서드와 점수 데이터를 조회하는 메서드를 제공
 -
 * 주요 기능:
 * - 새로운 점수 저장
 * - 모든 점수 데이터 조회
 * - 점수 ID 시퀀스 생성
 */

public class ScoreRepository {
    private static List<Score> scoreStore = new ArrayList<>(); //// 점수 데이터 저장 리스트
    private static int scoreIndex = 0; // 점수 ID 시퀀스 생성 인덱스
    private static final String INDEX_TYPE_SCORE = "SC";

    /**
     * 점수 ID 시퀀스를 생성
      >return 생성된 점수 ID
     */

    public static String sequence() {
        scoreIndex++;
        return INDEX_TYPE_SCORE + scoreIndex;
    }

    /**
     * 새로운 점수를 저장.
      > param 저장할 점수 객체
     */

    public void addScore(Score score) {
        scoreStore.add(score);
    }

    /**
     * 모든 점수 데이터를 반환.
     >return 모든 점수 데이터가 포함된 리스트
     */
    public List<Score> getAllScores() {
        return scoreStore;
    }
}
