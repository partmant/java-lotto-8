package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public final class RankTest {

    @DisplayName("일치 개수와 보너스 번호 여부에 따라 올바른 Rank를 반환한다.")
    @ParameterizedTest(name = "[{index}] 일치 개수: {0}, 보너스 여부: {1} → 예상 등수: {2}")
    @MethodSource("provideMatchCountAndBonusForRank")
    void 일치_개수와_보너스_여부에_따라_Rank를_반환한다(int matchCount, boolean hasBonus, Rank expectedRank) {
        Rank result = Rank.from(matchCount, hasBonus);

        assertThat(result).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideMatchCountAndBonusForRank() {
        return Stream.of(
                of(6, false, Rank.FIRST),
                of(5, true, Rank.SECOND),
                of(5, false, Rank.THIRD),
                of(4, false, Rank.FOURTH),
                of(3, false, Rank.FIFTH),
                of(2, false, Rank.MISS),
                of(0, false, Rank.MISS)
        );
    }
}
