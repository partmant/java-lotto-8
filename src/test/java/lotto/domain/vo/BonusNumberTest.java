package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class BonusNumberTest {

    @DisplayName("유효한 입력 문자열이면 객체가 정상적으로 생성된다.")
    @Test
    void 유효한_입력이면_보너스번호를_정상적으로_생성한다() {
        assertThatCode(() -> BonusNumber.from("7"))
                .doesNotThrowAnyException();
    }

    @DisplayName("생성된 BonusNumber는 주어진 숫자와 동일한 값을 가진다.")
    @Test
    void 생성된_보너스번호는_입력값과_동일하다() {
        BonusNumber bonusNumber = BonusNumber.from("7");

        assertThat(bonusNumber.equalsTo(7)).isTrue();
    }
}
