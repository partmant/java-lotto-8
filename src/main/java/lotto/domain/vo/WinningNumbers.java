package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WinningNumbers {

    private static final String SEPARATOR = ",";
    private static final String NUMERIC_REGEX = "-?\\d+";

    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static WinningNumbers from(String input) {
        List<Integer> parsedNumbers = WinningNumbers.parse(input);
        WinningNumbers.validate(parsedNumbers);
        return new WinningNumbers(parsedNumbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int matchCountWith(Lotto lotto) {
        return lotto.countMatchingNumbers(numbers);
    }

    private static List<Integer> parse(String input) {
        validateNotEmpty(input);

        return Stream.of(input.split(SEPARATOR))
                .map(String::trim)
                .peek(WinningNumbers::validateNumeric)
                .map(WinningNumbers::parseToInt)
                .collect(Collectors.toList());
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY.getMessage());
        }
    }

    private static void validateNumeric(String input) {
        if (!input.trim().matches(NUMERIC_REGEX)) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }

    private static int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE.getMessage());
        }
    }

    private static void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
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
