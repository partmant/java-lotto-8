package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public final class LottoTest {

    @DisplayName("입력이 null이면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void 입력이_null이면_예외를_발생시킨다(List<Integer> numbers) {
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NULL_OR_EMPTY.getMessage());
    }

    @DisplayName("로또 번호가 6개가 아니면 예외를 발생시킨다.")
    @Test
    void 로또_번호가_6개가_아니면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_COUNT.getMessage());
    }

    @DisplayName("1~45 범위를 벗어나는 숫자가 포함되어 있으면 예외를 발생시킨다.")
    @Test
    void 범위를_벗어난_숫자가_있으면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 46);

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_WINNING_NUMBER_RANGE.getMessage());
    }

    @DisplayName("중복된 숫자가 있으면 예외를 발생시킨다.")
    @Test
    void 중복된_숫자가_있으면_예외를_발생시킨다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WINNING_NUMBER_DUPLICATE.getMessage());
    }

    @DisplayName("유효한 숫자 6개로 로또를 생성하면 예외가 발생하지 않는다.")
    @Test
    void 유효한_숫자면_예외가_발생하지_않는다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> Lotto.from(numbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또는 항상 정렬된 상태로 보관된다.")
    @Test
    void 로또는_항상_정렬된_상태로_보관된다() {
        List<Integer> shuffled = List.of(6, 1, 4, 2, 5, 3);
        Lotto lotto = Lotto.from(shuffled);

        assertThat(lotto.numbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호와 일치하는 개수를 계산한다.")
    @Test
    void 당첨_번호와_일치하는_개수를_계산한다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = List.of(1, 3, 5, 7, 9, 11);

        int matchCount = lotto.countMatchingNumbers(winningNumbers);

        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("보너스 번호 포함 여부를 반환한다.")
    @Test
    void 보너스_번호_포함_여부를_반환한다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = BonusNumber.from("6");
        BonusNumber notBonus = BonusNumber.from("7");

        assertThat(lotto.hasBonus(bonus)).isTrue();
        assertThat(lotto.hasBonus(notBonus)).isFalse();
    }
}
