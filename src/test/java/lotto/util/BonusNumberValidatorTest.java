package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public final class BonusNumberValidatorTest {

    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "입력은 비어 있을 수 없습니다.";
    private static final String NON_NUMERIC_ERROR_MESSAGE = "보너스 번호는 숫자여야 합니다.";
    private static final String OUT_OF_RANGE_ERROR_MESSAGE = "보너스 번호는 1부터 45 사이여야 합니다.";
    private static final String OUT_OF_INTEGER_RANGE_ERROR_MESSAGE = "입력값이 정수 범위를 초과했습니다.";

    @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY_ERROR_MESSAGE);
    }

    @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @ValueSource(strings = {"육", "이십1"})
    void 입력_값이_숫자가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC_ERROR_MESSAGE);
    }

    @DisplayName("1보다 작거나 45보다 큰 숫자를 입력하면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("lotto.util.BonusNumberValidatorTest#provideOutOfLottoRangeValues")
    void 숫자_범위를_벗어나면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_RANGE_ERROR_MESSAGE);
    }

    @DisplayName("입력 값이 int의 범위를 초과하면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("lotto.util.BonusNumberValidatorTest#provideOutOfRangeValues")
    void 입력_값이_int_범위를_초과하면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_INTEGER_RANGE_ERROR_MESSAGE);
    }

    @DisplayName("입력 값이 유효하면 예외가 발생하지 않는다.")
    @Test
    void 입력_값이_유효하면_성공한다() {
        assertThatCode(() -> BonusNumberValidator.validate("7"))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력 값이 유효하면 정수로 변환해서 반환한다.")
    @Test
    void 입력_값이_유효하면_정수로_변환해서_반환한다() {
        int result = BonusNumberValidator.validate("7");

        assertThat(result).isEqualTo(7);
    }

    static Stream<String> provideOutOfRangeValues() {
        return Stream.of(
                String.valueOf((long) Integer.MAX_VALUE + 1),
                String.valueOf((long) Integer.MIN_VALUE - 1)
        );
    }

    public static Stream<String> provideOutOfLottoRangeValues() {
        return Stream.of("0", "46", "-1", "100");
    }
}
