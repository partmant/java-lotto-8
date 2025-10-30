package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LottoValidator {

    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String INVALID_COUNT_ERROR_MESSAGE = "[ERROR] 당첨 번호는 6개의 숫자여야 합니다.";
    private static final String OUT_OF_RANGE_ERROR_MESSAGE = "[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "[ERROR] 당첨 번호는 중복된 숫자가 있을 수 없습니다.";

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
            throw new IllegalArgumentException(INVALID_COUNT_ERROR_MESSAGE);
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
            }
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }
}
