package lotto.dto;

import lotto.domain.Rank;

public final class RankResultDto {

    private final String description;
    private final long reward;
    private final int count;

    private RankResultDto(String description, long reward, int count) {
        this.description = description;
        this.reward = reward;
        this.count = count;
    }

    public static RankResultDto from(Rank rank, int count) {
        String description = generateDescription(rank);

        return new RankResultDto(description, rank.reward(), count);
    }

    private static String generateDescription(Rank rank) {
        if (rank.hasBonus()) {
            return String.format("%d개 일치, 보너스 볼 일치", rank.matchCount());
        }
        return String.format("%d개 일치", rank.matchCount());
    }

    public String formattedLine() {
        return String.format("%s (%,d원) - %d개", description, reward, count);
    }

    public String description() {
        return description;
    }

    public long reward() {
        return reward;
    }

    public int count() {
        return count;
    }
}