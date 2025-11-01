package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean hasBonus;
    private final int reward;

    Rank(int matchCount, boolean hasBonus, int reward) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.reward = reward;
    }

    public static Rank from(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && hasBonus) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return MISS;
    }

    public long totalRewardFor(int count) {
        return (long) reward * count;
    }
}
