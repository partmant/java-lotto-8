package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Lotto {

    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    public static Lotto from(List<Integer> numbers) {
        validate(numbers);
        return new Lotto(numbers);
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

    public List<Integer> numbers() {
        return numbers;
    }

    private static void validate(List<Integer> numbers) {
        validateNotNull(numbers);
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private static void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(NULL_OR_EMPTY.getMessage());
        }
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_COUNT) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_COUNT.getMessage());
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(INVALID_WINNING_NUMBER_RANGE.getMessage());
            }
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE.getMessage());
        }
    }
}
