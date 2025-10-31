package lotto.domain;

public enum Rank {
    FIRST(6, false),
    SECOND(5, true),
    THIRD(5, false),
    FOURTH(4, false),
    FIFTH(3, false),
    MISS(0, false);

    private final int matchCount;
    private final boolean requiresBonus;

    Rank(int matchCount, boolean requiresBonus) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && hasBonus) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return MISS;
    }
}
