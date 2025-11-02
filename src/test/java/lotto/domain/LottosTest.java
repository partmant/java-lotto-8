package lotto.domain;

import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("보유한 모든 Lotto의 당첨 결과를 계산하여 WinningResult로 반환한다.")
    @Test
    void 여러_로또의_결과를_계산하여_WinningResult를_반환한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");
        BonusNumber bonusNumber = BonusNumber.from("7");
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        Lottos lottos = Lottos.from(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 8)),
                Lotto.from(List.of(1, 2, 3, 4, 8, 9)),
                Lotto.from(List.of(1, 2, 3, 8, 9, 10)),
                Lotto.from(List.of(11, 12, 13, 14, 15, 16))
        ));

        LottoResult result = lottos.calculateResult(winningLotto);

        Map<Rank, Integer> expected = Map.of(
                Rank.FIRST, 1,
                Rank.SECOND, 1,
                Rank.THIRD, 1,
                Rank.FOURTH, 1,
                Rank.FIFTH, 1,
                Rank.MISS, 1
        );

        expected.forEach((rank, count) ->
                assertThat(result.countOf(rank))
                        .as(rank.name() + " 등수 개수")
                        .isEqualTo(count)
        );
    }
}
