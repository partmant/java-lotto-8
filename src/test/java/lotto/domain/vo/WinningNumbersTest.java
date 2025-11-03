package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public final class WinningNumbersTest {

    private final String VALID_INPUT = "1, 2, 3, 4, 5, 6";

    @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> WinningNumbers.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY.getMessage());
    }

    @DisplayName("입력 값이 숫자가 아니면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @ValueSource(strings = {"육", "이십1"})
    void 입력_값이_숫자가_아니면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> WinningNumbers.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC.getMessage());
    }

    @DisplayName("숫자가 6개가 아니면 예외를 발생시킨다.")
    @Test
    void 숫자가_6개가_아니면_예외를_발생시킨다() {
        String numbers = "1, 2, 3, 4, 5";

        assertThatThrownBy(() -> WinningNumbers.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_COUNT.getMessage());
    }

    @DisplayName("1부터 45 범위를 벗어나는 숫자가 포함되어 있으면 예외를 발생시킨다.")
    @Test
    void 범위를_벗어난_숫자가_있으면_예외를_발생시킨다() {
        String numbers = "0, 2, 3, 4, 5, 46";

        assertThatThrownBy(() -> WinningNumbers.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_RANGE.getMessage());
    }

    @DisplayName("중복된 숫자가 있으면 예외를 발생시킨다.")
    @Test
    void 중복된_숫자가_있으면_예외를_발생시킨다() {
        String numbers = "1, 1, 1, 4, 5, 5";

        assertThatThrownBy(() -> WinningNumbers.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WINNING_NUMBER_DUPLICATE.getMessage());
    }

    @DisplayName("유효한 문자열을 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void 유효한_입력이면_객체를_생성한다() {
        WinningNumbers winningNumbers = WinningNumbers.from(VALID_INPUT);

        assertThat(winningNumbers).isNotNull();
    }

    @DisplayName("특정 숫자의 포함 여부를 판단한다.")
    @Test
    void 특정_숫자의_포함_여부를_판단한다() {
        WinningNumbers winningNumbers = WinningNumbers.from(VALID_INPUT);

        assertThat(winningNumbers.contains(1)).isTrue();
        assertThat(winningNumbers.contains(6)).isTrue();
        assertThat(winningNumbers.contains(7)).isFalse();
    }

    @DisplayName("로또 번호와 일치 개수를 계산하여 반환한다.")
    @Test
    void 로또와_일치하는_번호_개수를_계산한다() {
        WinningNumbers winningNumbers = WinningNumbers.from(VALID_INPUT);
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 11, 12));

        int matchCount = winningNumbers.matchCountWith(lotto);

        assertThat(matchCount).isEqualTo(3);
    }
}
