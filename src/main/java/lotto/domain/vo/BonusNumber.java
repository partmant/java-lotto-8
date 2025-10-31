package lotto.domain.vo;

import lotto.util.BonusNumberValidator;

public final class BonusNumber {

    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber from(String input) {
        int validatedNumber = BonusNumberValidator.validate(input);
        return new BonusNumber(validatedNumber);
    }

    public boolean equalsTo(int number) {
        return this.value == number;
    }

    public boolean isDuplicatedWith(WinningNumbers winningNumbers) {
        return winningNumbers.contains(value);
    }
}
