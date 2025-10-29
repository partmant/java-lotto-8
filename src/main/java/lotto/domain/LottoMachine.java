package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

import lotto.Lotto;

public final class LottoMachine {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private LottoMachine() {
    }

    public static Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE);
        numbers.sort(Integer::compareTo);
        return new Lotto(numbers);
    }
}
