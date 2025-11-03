package lotto.util;

import static lotto.exception.ErrorMessage.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LottoValidator {

    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private LottoValidator() {
    }

    public static List<Integer> validate(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        return numbers;
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
