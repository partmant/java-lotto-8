package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

import lotto.domain.vo.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

public final class LottoResultTest {

    @DisplayName("등수별 당첨 개수를 반환한다.")
    @Test
    void 등수별_당첨_개수를_반환한다() {
        Map<Rank, Integer> resultMap = Map.of(
                Rank.FIRST, 1,
                Rank.THIRD, 2,
                Rank.MISS, 3
        );
        LottoResult result = LottoResult.of(resultMap);

        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(2);
        assertThat(result.countOf(Rank.MISS)).isEqualTo(3);
    }

    @DisplayName("총 상금을 계산한다.")
    @Test
    void 총_상금을_계산한다() {
        Map<Rank, Integer> resultMap = Map.of(
                Rank.FIRST, 1,
                Rank.THIRD, 1,
                Rank.FIFTH, 1
        );
        LottoResult result = LottoResult.of(resultMap);

        long totalReward = result.totalReward();

        long expected = Rank.FIRST.totalRewardFor(1)
                + Rank.THIRD.totalRewardFor(1)
                + Rank.FIFTH.totalRewardFor(1);
        assertThat(totalReward).isEqualTo(expected);
    }

    @DisplayName("구입 금액 대비 수익률을 계산한다.")
    @ParameterizedTest(name = "[{index}] 총 상금 {0}, 구입 금액 {1} → 예상 수익률 {2}%")
    @MethodSource("provideProfitRateData")
    void 구입금액_대비_수익률을_계산한다(long totalReward, int purchaseAmountValue, double expectedRate) {
        Map<Rank, Integer> resultMap = Map.of(Rank.FIRST, 0);
        LottoResult result = LottoResult.of(resultMap);
        PurchaseAmount purchaseAmount = PurchaseAmount.from(String.valueOf(purchaseAmountValue));

        double rate = purchaseAmount.calculateProfitRate(totalReward);

        assertThat(rate).isEqualTo(expectedRate);
    }

    private static Stream<Arguments> provideProfitRateData() {
        return Stream.of(
                of(5000L, 8000, 62.5),
                of(16000L, 8000, 200.0),
                of(0L, 8000, 0.0)
        );
    }
}
