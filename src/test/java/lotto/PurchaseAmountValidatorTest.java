package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class PurchaseAmountValidatorTest {

    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    @DisplayName("유효하지 않은 입력에 대하여 예외를 발생시킨다.")
    void 입력이_유효하지_않으면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
    void 입력_값이_숫자가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate("천원"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력 값은 숫자여야 합니다.");
    }

    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("provideOutOfRangeValues")
    @DisplayName("입력 값이 int의 범위를 초과하면 예외를 발생시킨다.")
    void 입력_값이_int_범위를_초과하면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 처리 가능한 정수 범위를 초과했습니다.");
    }

    private static Stream<String> provideOutOfRangeValues() {
        return Stream.of(
                String.valueOf((long) Integer.MAX_VALUE + 1),
                String.valueOf((long) Integer.MIN_VALUE - 1)
        );
    }

    @Test
    @DisplayName("입력 값이 양의 정수가 아니면 예외를 발생시킨다.")
    void 입력_값이_양의_정수가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate("-3000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액은 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외를 발생시킨다.")
    void 구입_금액이_1000원_단위가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate("2100"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("입력 값이 유효하면 성공한다.")
    void 입력_값이_유효하면_성공한다() {
        assertThatCode(() -> PurchaseAmountValidator.validate("10000"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력 값이 유효하면 정수로 변환해서 반환한다.")
    void 입력_값이_유효하면_정수로_변환해서_반환한다() {
        String input = "13000";

        int result = PurchaseAmountValidator.validate(input);

        assertThat(result).isEqualTo(13000);
    }
}
