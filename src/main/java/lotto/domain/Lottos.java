package lotto.domain;

import java.util.List;

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
}
