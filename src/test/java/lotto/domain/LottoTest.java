package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("유효한 6개의 번호로 Lotto 객체를 생성하면 정상적으로 생성된다.")
    @Test
    void 유효한_번호로_생성하면_정상적으로_생성된다() {
        assertThat(new Lotto(List.of(1, 2, 3, 4, 5, 6))).isNotNull();
    }
}
