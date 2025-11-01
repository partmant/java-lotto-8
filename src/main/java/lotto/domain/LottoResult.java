package lotto.domain;

import lotto.domain.vo.PurchaseAmount;

import java.util.EnumMap;
import java.util.Map;

public final class LottoResult {

    private final Map<Rank, Integer> results;

    private LottoResult(Map<Rank, Integer> results) {
        this.results = new EnumMap<>(results);
    }

    public static LottoResult of(Map<Rank, Integer> results) {
        return new LottoResult(results);
    }

    public int countOf(Rank rank) {
        return results.getOrDefault(rank, 0);
    }

    public long totalReward() {
        return results.entrySet().stream()
                .mapToLong(entry -> entry.getKey().totalRewardFor(entry.getValue()))
                .sum();
    }

    public double profitRate(PurchaseAmount purchaseAmount) {
        return purchaseAmount.calculateProfitRate(totalReward());
    }
}
