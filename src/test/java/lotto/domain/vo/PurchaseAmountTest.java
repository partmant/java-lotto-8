package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountTest {

    private static final String VALID_AMOUNT_INPUT = "5000";
    private static final int EXPECTED_LOTTO_COUNT = 5;

    @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY.getMessage());
    }

    @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @ValueSource(strings = {"천원", "1a00", "12 34"})
    void 입력_값이_숫자가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC.getMessage());
    }

    @DisplayName("입력 값이 int의 범위를 초과하면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("provideOutOfRangeValues")
    void 입력_값이_int_범위를_초과하면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_INTEGER_RANGE.getMessage());
    }

    @DisplayName("입력 값이 음수이거나 0이면 예외를 발생시킨다.")
    @Test
    void 입력_값이_양의_정수가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> PurchaseAmount.from("-3000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NEGATIVE_OR_ZERO.getMessage());
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외를 발생시킨다.")
    @Test
    void 구입_금액이_1000원_단위가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> PurchaseAmount.from("2100"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_PURCHASE_AMOUNT_UNIT.getMessage());
    }

    @DisplayName("입력 값이 유효하면 예외가 발생하지 않는다.")
    @Test
    void 입력_값이_유효하면_객체가_정상_생성된다() {
        assertThatCode(() -> PurchaseAmount.from(VALID_AMOUNT_INPUT))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액으로 구입 가능한 로또 개수를 계산한다.")
    @Test
    void 구입_금액으로_구입_가능한_로또_개수를_계산한다() {
        PurchaseAmount amount = PurchaseAmount.from(VALID_AMOUNT_INPUT);

        assertThat(amount.calculateLottoCount()).isEqualTo(EXPECTED_LOTTO_COUNT);
    }

    static Stream<String> provideOutOfRangeValues() {
        return Stream.of(
                String.valueOf((long) Integer.MAX_VALUE + 1),
                String.valueOf((long) Integer.MIN_VALUE - 1)
        );
    }
}
