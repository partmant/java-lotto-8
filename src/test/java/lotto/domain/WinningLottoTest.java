package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumbers;

public final class WinningLottoTest {

    private static final WinningNumbers WINNING_NUMBERS = WinningNumbers.from("1, 2, 3, 4, 5, 6");
    private static final BonusNumber BONUS_NUMBER = BonusNumber.from("7");
    private static final WinningLotto WINNING_LOTTO = WinningLotto.of(WINNING_NUMBERS, BONUS_NUMBER);

    @DisplayName("로또 번호와 당첨 번호를 비교하여 일치 개수 및 보너스 번호 여부에 따라 등수를 판별한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호: {0} → 예상 등수: {1}")
    @MethodSource("provideLottoAndExpectedRank")
    void 로또와_당첨번호를_비교하여_등수를_판별한다(List<Integer> lottoNumbers, Rank expectedRank) {
        Lotto lotto = Lotto.from(lottoNumbers);

        Rank result = WINNING_LOTTO.determineRankFor(lotto);

        assertThat(result).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideLottoAndExpectedRank() {
        return Stream.of(
                of(List.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                of(List.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                of(List.of(1, 2, 3, 4, 5, 10), Rank.THIRD),
                of(List.of(1, 2, 3, 4, 10, 11), Rank.FOURTH),
                of(List.of(1, 2, 3, 10, 11, 12), Rank.FIFTH),
                of(List.of(10, 11, 12, 13, 14, 15), Rank.MISS)
        );
    }
}
