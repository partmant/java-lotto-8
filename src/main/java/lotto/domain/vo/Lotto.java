package lotto.domain.vo;

import lotto.util.LottoValidator;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validate(numbers);
        this.numbers = numbers;
    }

    public int countMatchingNumbers(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonus(BonusNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(bonusNumber::equalsTo);
    }
}
