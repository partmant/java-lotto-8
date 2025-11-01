package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.domain.vo.Lotto;

public final class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public boolean hasCountOf(int expectedCount) {
        return lottos.size() == expectedCount;
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.determineRankFor(lotto);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }

        return LottoResult.of(resultMap);
    }
}
