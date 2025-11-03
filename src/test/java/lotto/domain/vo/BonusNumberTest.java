package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public final class BonusNumberTest {

    @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY.getMessage());
    }

    @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @ValueSource(strings = {"육", "이십1"})
    void 입력_값이_숫자가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC.getMessage());
    }

    @DisplayName("1보다 작거나 45보다 큰 숫자를 입력하면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("provideOutOfLottoRangeValues")
    void 숫자_범위를_벗어나면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_RANGE.getMessage());
    }

    @DisplayName("입력 값이 int의 범위를 초과하면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @MethodSource("provideOutOfRangeValues")
    void 입력_값이_int_범위를_초과하면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> BonusNumber.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_INTEGER_RANGE.getMessage());
    }

    @DisplayName("유효한 입력 문자열이면 객체가 정상적으로 생성된다.")
    @Test
    void 유효한_입력이면_보너스번호를_정상적으로_생성한다() {
        assertThatCode(() -> BonusNumber.from("7"))
                .doesNotThrowAnyException();
    }

    static Stream<String> provideOutOfLottoRangeValues() {
        return Stream.of("0", "46", "-1", "100");
    }

    static Stream<String> provideOutOfRangeValues() {
        return Stream.of(
                String.valueOf((long) Integer.MAX_VALUE + 1),
                String.valueOf((long) Integer.MIN_VALUE - 1)
        );
    }
}
