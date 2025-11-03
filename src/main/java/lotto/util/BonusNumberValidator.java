package lotto.util;

import static lotto.exception.ErrorMessage.*;

public final class BonusNumberValidator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String NUMERIC_REGEX = "-?\\d+";

    private BonusNumberValidator() {
    }

    public static int validate(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
        int number = parseToInt(input);
        validateRange(number);
        return number;
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY.getMessage());
        }
    }

    private static void validateNumeric(String input) {
        String trimmed = input.trim();
        if (!trimmed.matches(NUMERIC_REGEX)) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE.getMessage());
        }
    }

    private static void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBER_RANGE.getMessage());
        }
    }
}
