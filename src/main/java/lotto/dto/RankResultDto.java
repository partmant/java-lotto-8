package lotto.dto;

import lotto.domain.Rank;

public final class RankResultDto {

    private final Rank rank;
    private final int count;

    private RankResultDto(Rank rank, int count) {
        this.rank = rank;
        this.count = count;
    }

    public static RankResultDto from(Rank rank, int count) {
        return new RankResultDto(rank, count);
    }

    public Rank rank() {
        return rank;
    }

    public int count() {
        return count;
    }
}
