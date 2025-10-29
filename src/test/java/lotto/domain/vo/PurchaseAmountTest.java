package lotto.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseAmountTest {

    private static final String VALID_AMOUNT_INPUT = "5000";
    private static final int EXPECTED_LOTTO_COUNT = 5;

    @DisplayName("검증된 금액으로 PurchaseAmount 객체를 생성한다.")
    @Test
    void 검증된_금액으로_PurchaseAmount_객체_생성이_성공한다() {
        PurchaseAmount amount = PurchaseAmount.from(VALID_AMOUNT_INPUT);
        assertThat(amount).isNotNull();
    }

    @DisplayName("구입 금액으로 구입 가능한 로또 개수를 계산한다.")
    @Test
    void 구입_금액으로_구입_가능한_로또_개수를_계산한다() {
        PurchaseAmount amount = PurchaseAmount.from(VALID_AMOUNT_INPUT);
        assertThat(amount.calculateLottoCount()).isEqualTo(EXPECTED_LOTTO_COUNT);
    }
}
