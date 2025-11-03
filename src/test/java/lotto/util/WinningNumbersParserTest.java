package lotto.util;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersParserTest {

    @DisplayName("입력이 null이거나 공백이면 예외를 발생시킨다.")
    @ParameterizedTest(name = "[{index}] 입력값: \"{0}\"")
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void 입력이_null_또는_공백이면_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> WinningNumbersParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY.getMessage());
    }

    @DisplayName("숫자가 아닌 값이 포함되어 있으면 예외를 발생시킨다.")
    @Test
    void 입력_값이_숫자가_아니면_예외를_발생시킨다() {
        assertThatThrownBy(() -> WinningNumbersParser.parse("1,2,3,4,a,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC.getMessage());
    }

    @DisplayName("입력 값이 int 범위를 초과하면 예외를 발생시킨다.")
    @Test
    void 입력_값이_int_범위를_초과하면_예외를_발생시킨다() {
        assertThatThrownBy(() -> WinningNumbersParser.parse("1,2,3,4,5,2200000000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_INTEGER_RANGE.getMessage());
    }

    @DisplayName("유효한 입력은 쉼표 기준으로 분리되어 정수 리스트로 반환한다.")
    @Test
    void 유효한_입력은_정수_리스트로_반환한다() {
        List<Integer> result = WinningNumbersParser.parse("1,2,3,4,5,6");

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("입력 값에 공백이 포함되어 있으면 제거 후 정수 리스트로 반환한다.")
    @Test
    void 입력에_공백이_포함되어_있어도_정상_처리된다() {
        List<Integer> result = WinningNumbersParser.parse(" 1, 2,3 ,4, 5 , 6 ");

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
