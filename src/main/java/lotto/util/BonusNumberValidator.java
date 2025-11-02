package lotto.util;

public final class BonusNumberValidator {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "입력은 비어 있을 수 없습니다.";
    private static final String NON_NUMERIC_ERROR_MESSAGE = "보너스 번호는 숫자여야 합니다.";
    private static final String OUT_OF_RANGE_ERROR_MESSAGE = "보너스 번호는 1부터 45 사이여야 합니다.";
    private static final String OUT_OF_INTEGER_RANGE_ERROR_MESSAGE = "입력값이 정수 범위를 초과했습니다.";

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
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR_MESSAGE);
        }
    }

    private static void validateNumeric(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR_MESSAGE);
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE_ERROR_MESSAGE);
        }
    }

    private static void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }
}
