package lotto.domain;

import static lotto.exception.ErrorMessage.BONUS_NUMBER_DUPLICATE;

import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumbers;

public final class WinningLotto {

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
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    public Rank determineRankFor(Lotto lotto) {
        int matchCount = winningNumbers.matchCountWith(lotto);
        boolean hasBonus = lotto.hasBonus(bonusNumber);
        return Rank.from(matchCount, hasBonus);
    }
}
