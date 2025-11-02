package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoValidatorTest {

    private static final String INVALID_COUNT_ERROR_MESSAGE = "당첨 번호는 6개의 숫자여야 합니다.";
    private static final String OUT_OF_RANGE_ERROR_MESSAGE = "당첨 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "당첨 번호는 중복된 숫자가 있을 수 없습니다.";

    @DisplayName("숫자가 6개가 아니면 예외를 발생시킨다.")
    @Test
    void 숫자가_6개가_아니면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> LottoValidator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_COUNT_ERROR_MESSAGE);
    }

    @DisplayName("1부터 45 범위를 벗어나는 숫자가 포함되어 있으면 예외를 발생시킨다.")
    @Test
    void 범위를_벗어난_숫자가_있으면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> LottoValidator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_RANGE_ERROR_MESSAGE);
    }

    @DisplayName("중복된 숫자가 있으면 예외를 발생시킨다.")
    @Test
    void 중복된_숫자가_있으면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> LottoValidator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_ERROR_MESSAGE);
    }

    @DisplayName("유효한 입력은 검증을 성공한다.")
    @Test
    void 유효한_입력은_리스트를_반환한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> result = LottoValidator.validate(numbers);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
