package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("1~45 이내의 중복 없는 숫자 6개로 구성된 로또번호를 생성한다.")
    @RepeatedTest(10)
    void 로또_번호는_1부터_45_사이의_중복_없는_6개_숫자로_구성된다() {
        assertThatCode(LottoMachine::generate)
                .doesNotThrowAnyException();
    }

    @DisplayName("구매 개수만큼 로또를 생성한다")
    @Test
    void 입력된_개수만큼_로또를_생성한다() {
        int count = 5;

        Lottos lottos = LottoMachine.generateMultiple(count);

        assertThat(lottos.hasCountOf(count)).isTrue();
    }
}
