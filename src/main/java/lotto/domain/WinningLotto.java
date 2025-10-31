package lotto.domain;

import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumbers;

public final class WinningLotto {

    private static final String BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    private WinningLotto(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        validateNoDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private void validateNoDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        if (bonusNumber.isDuplicatedWith(winningNumbers)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public Rank determineRankFor(Lotto lotto) {
        int matchCount = winningNumbers.matchCountWith(lotto);
        boolean hasBonus = lotto.hasBonus(bonusNumber);
        return Rank.valueOf(matchCount, hasBonus);
    }
}
