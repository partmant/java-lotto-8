package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import lotto.Lotto;

public class LottoMachineTest {

    @DisplayName("로또 번호는 중복 없이 6개가 생성된다.")
    @RepeatedTest(10)
    void 로또_번호는_중복_없이_6개가_생성된다() {
        Lotto lotto = LottoMachine.generate();

        assertThat(lotto.isValid()).isTrue();
    }
}
