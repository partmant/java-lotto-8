package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

import lotto.domain.vo.Lotto;

public final class LottoMachine {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private LottoMachine() {
    }

    public static Lotto generate() {
        List<Integer> numbers = new ArrayList<>(
                Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE)
        );
        numbers.sort(Integer::compareTo);
        return Lotto.from(numbers);
    }

    public static Lottos generateMultiple(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(generate());
        }
        return Lottos.from(lottoList);
    }
}
