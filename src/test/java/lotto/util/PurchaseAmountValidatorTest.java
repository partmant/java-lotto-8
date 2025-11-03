package lotto.util;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("PurchaseAmountValidator 테스트")
class PurchaseAmountValidatorTest {

    @Nested
    @DisplayName("입력값 유효성 검증")
    class InputValidationTest {

        @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
        @NullSource
        @ValueSource(strings = {"", " ", "\t"})
        @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
        void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
            assertThatThrownBy(() -> PurchaseAmountValidator.validate(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(NULL_OR_EMPTY.getMessage());
        }

        @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
        @ValueSource(strings = {"천원", "1a00", "12 34"})
        @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
        void 입력_값이_숫자가_아니면_예외를_발생시킨다(String input) {
            assertThatThrownBy(() -> PurchaseAmountValidator.validate(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(NON_NUMERIC.getMessage());
        }

        @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
        @MethodSource("lotto.util.PurchaseAmountValidatorTest#provideOutOfRangeValues")
        @DisplayName("입력 값이 int의 범위를 초과하면 예외를 발생시킨다.")
        void 입력_값이_int_범위를_초과하면_예외를_발생시킨다(String input) {
            assertThatThrownBy(() -> PurchaseAmountValidator.validate(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(OUT_OF_INTEGER_RANGE.getMessage());
        }
    }

    @Nested
    @DisplayName("구입 금액 규칙 검증")
    class AmountRuleTest {

        @Test
        @DisplayName("입력 값이 양의 정수가 아니면 예외를 발생시킨다.")
        void 입력_값이_양의_정수가_아니면_예외를_발생시킨다() {
            assertThatThrownBy(() -> PurchaseAmountValidator.validate("-3000"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(NEGATIVE_OR_ZERO.getMessage());
        }

        @Test
        @DisplayName("구입 금액이 1000원 단위가 아니면 예외를 발생시킨다.")
        void 구입_금액이_1000원_단위가_아니면_예외를_발생시킨다() {
            assertThatThrownBy(() -> PurchaseAmountValidator.validate("2100"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(INVALID_PURCHASE_AMOUNT_UNIT.getMessage());
        }
    }

    @Nested
    @DisplayName("정상 입력 검증")
    class ValidInputTest {

        @Test
        @DisplayName("입력 값이 유효하면 예외가 발생하지 않는다.")
        void 입력_값이_유효하면_성공한다() {
            assertThatCode(() -> PurchaseAmountValidator.validate("10000"))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("입력 값이 유효하면 정수로 변환해서 반환한다.")
        void 입력_값이_유효하면_정수로_변환해서_반환한다() {
            int result = PurchaseAmountValidator.validate("13000");
            assertThat(result).isEqualTo(13000);
        }
    }

    static Stream<String> provideOutOfRangeValues() {
        return Stream.of(
                String.valueOf((long) Integer.MAX_VALUE + 1),
                String.valueOf((long) Integer.MIN_VALUE - 1)
        );
    }
}
