package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("당첨 번호와 일치하는 개수를 정확히 계산한다.")
    @Test
    void 당첨번호와_일치개수를_계산한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = List.of(1, 2, 7, 8, 9, 10);

        int matchCount = lotto.countMatchingNumbers(winningNumbers);

        assertThat(matchCount).isEqualTo(2);
    }

    @DisplayName("로또에 보너스 번호가 포함되어 있으면 true를 반환한다.")
    @Test
    void 보너스번호가_포함되어있으면_true를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from("6");

        boolean hasBonus = lotto.hasBonus(bonusNumber);

        assertThat(hasBonus).isTrue();
    }

    @DisplayName("로또에 보너스 번호가 포함되어 있지 않으면 false를 반환한다.")
    @Test
    void 보너스번호가_없으면_false를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from("7");

        boolean hasBonus = lotto.hasBonus(bonusNumber);

        assertThat(hasBonus).isFalse();
    }
}
