package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class WinningNumbersTest {

    @DisplayName("유효한 문자열을 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void 유효한_입력으로_WinningNumbers_객체를_생성한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6");

        assertThat(winningNumbers).isNotNull();
    }

    @DisplayName("WinningNumbers가 특정 숫자의 포함 여부를 판단할 수 있다.")
    @Test
    void 특정_숫자의_포함_여부를_판단한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6");

        assertThat(winningNumbers.contains(1)).isTrue();
        assertThat(winningNumbers.contains(6)).isTrue();
        assertThat(winningNumbers.contains(7)).isFalse();
    }
}
