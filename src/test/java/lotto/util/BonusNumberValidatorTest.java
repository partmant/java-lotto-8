package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class BonusNumberValidatorTest {

    @DisplayName("유효한 숫자 문자열을 입력하면 예외 없이 검증을 통과한다.")
    @Test
    void 유효한_입력이면_예외없이_검증을_통과한다() {
        assertThatCode(() -> BonusNumberValidator.validate("7"))
                .doesNotThrowAnyException();
    }

    @DisplayName("유효한 숫자 문자열을 입력하면 정수로 변환된다.")
    @Test
    void 유효한_입력이면_정상적으로_정수로_변환된다() {
        int result = BonusNumberValidator.validate("7");

        assertThat(result).isEqualTo(7);
    }

    @DisplayName("입력이 null이면 예외를 발생시킨다.")
    @Test
    void null_입력이면_예외를_발생시킨다() {
        assertThatThrownBy(() -> BonusNumberValidator.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어 있을 수 없습니다");
    }

    @DisplayName("입력이 공백이면 예외를 발생시킨다.")
    @Test
    void 공백_입력이면_예외를_발생시킨다() {
        assertThatThrownBy(() -> BonusNumberValidator.validate("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어 있을 수 없습니다");
    }

    @DisplayName("숫자가 아닌 문자를 입력하면 예외를 발생시킨다.")
    @Test
    void 숫자가_아닌_문자를_입력하면_예외를_발생시킨다() {
        assertThatThrownBy(() -> BonusNumberValidator.validate("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자여야 합니다");
    }

    @DisplayName("1보다 작거나 45보다 큰 숫자를 입력하면 예외를 발생시킨다.")
    @Test
    void 숫자_범위를_벗어나면_예외를_발생시킨다() {
        assertThatThrownBy(() -> BonusNumberValidator.validate("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45 사이");

        assertThatThrownBy(() -> BonusNumberValidator.validate("46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45 사이");
    }

    @DisplayName("정수 범위를 초과하는 큰 값을 입력하면 예외를 발생시킨다.")
    @Test
    void 정수_범위를_초과하면_예외를_발생시킨다() {
        assertThatThrownBy(() -> BonusNumberValidator.validate(String.valueOf(Integer.MAX_VALUE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수 범위를 초과");
    }
}
