package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public final class WinningNumbersTest {

    @DisplayName("유효한 문자열을 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void 유효한_입력으로_WinningNumbers_객체를_생성한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6");

        assertThat(winningNumbers).isNotNull();
    }

    @DisplayName("특정 숫자의 포함 여부를 판단한다.")
    @Test
    void 특정_숫자의_포함_여부를_판단한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6");

        assertThat(winningNumbers.contains(1)).isTrue();
        assertThat(winningNumbers.contains(6)).isTrue();
        assertThat(winningNumbers.contains(7)).isFalse();
    }

    @DisplayName("로또 번호와 일치 개수를 계산하여 반환한다.")
    @Test
    void 로또와_일치하는_번호_개수를_계산한다() {
        WinningNumbers winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6");
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 11, 12));

        int matchCount = winningNumbers.matchCountWith(lotto);

        assertThat(matchCount).isEqualTo(3);
    }
}
