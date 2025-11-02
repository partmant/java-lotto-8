package lotto.util;

public final class PurchaseAmountValidator {

    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "입력은 비어있을 수 없습니다.";
    private static final String NON_NUMERIC_ERROR_MESSAGE = "입력 값은 숫자여야 합니다.";
    private static final String OUT_OF_INTEGER_RANGE_ERROR_MESSAGE = "입력값이 처리 가능한 정수 범위를 초과했습니다.";
    private static final String NEGATIVE_OR_ZERO_ERROR_MESSAGE = "구입 금액은 0보다 커야 합니다.";
    private static final String INVALID_UNIT_ERROR_MESSAGE = "구입 금액은 1000원 단위여야 합니다.";

    private PurchaseAmountValidator() {
    }

    public static int validate(String input) {
        validateNotBlank(input);
        validateNumeric(input);

        int amount = parseToInt(input.trim());
        validatePositive(amount);
        validateUnit(amount);

        return amount;
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR_MESSAGE);
        }
    }

    private static void validateNumeric(String input) {
        if (!input.trim().matches("-?\\d+")) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR_MESSAGE);
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE_ERROR_MESSAGE);
        }
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(NEGATIVE_OR_ZERO_ERROR_MESSAGE);
        }
    }

    private static void validateUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(INVALID_UNIT_ERROR_MESSAGE);
        }
    }
}
