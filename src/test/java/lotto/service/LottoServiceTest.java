package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.vo.Lotto;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.vo.WinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoServiceTest {

    @DisplayName("구입 금액으로 구매 가능한 개수만큼 로또를 발행한다.")
    @Test
    void 구입금액으로_로또를_발행한다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from("5000");

        Lottos lottos = LottoService.issueLottos(purchaseAmount);

        assertThat(lottos.hasCountOf(5)).isTrue();
    }

    @DisplayName("발행된 로또와 당첨 번호로 당첨 결과를 계산한다.")
    @Test
    void 당첨_결과를_계산한다() {
        // given
        List<Lotto> lottoList = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 8)),
                Lotto.from(List.of(1, 2, 3, 4, 9, 10))
        );
        Lottos lottos = Lottos.from(lottoList);

        WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");
        BonusNumber bonusNumber = BonusNumber.from("7");
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        // when
        LottoResult result = LottoService.calculateResult(lottos, winningLotto);

        // then
        var expectedCounts = Map.of(
                Rank.FIRST, 1,
                Rank.SECOND, 1,
                Rank.THIRD, 1,
                Rank.FOURTH, 0,
                Rank.FIFTH, 0,
                Rank.MISS, 1
        );

        expectedCounts.forEach((rank, expectedCount) ->
                assertThat(result.countOf(rank))
                        .as("%s 등수의 개수 검증", rank)
                        .isEqualTo(expectedCount)
        );
    }


    @DisplayName("발행된 로또의 총 수익률을 계산한다.")
    @Test
    void 총_수익률을_계산한다() {
        // given
        List<Lotto> lottoList = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 8)),
                Lotto.from(List.of(1, 2, 3, 4, 9, 10))
        );
        Lottos lottos = Lottos.from(lottoList);

        WinningNumbers winningNumbers = WinningNumbers.from("1,2,3,4,5,6");
        BonusNumber bonusNumber = BonusNumber.from("7");
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);
        PurchaseAmount purchaseAmount = PurchaseAmount.from("4000");

        // when
        double profitRate = LottoService.calculateProfitRate(lottos, winningLotto, purchaseAmount);

        // then
        assertThat(profitRate).isGreaterThan(507875.0);
    }
}
