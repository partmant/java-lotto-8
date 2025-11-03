package lotto.util;

import static lotto.exception.ErrorMessage.*;

public final class PurchaseAmountValidator {

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
            throw new IllegalArgumentException(NULL_OR_EMPTY.getMessage());
        }
    }

    private static void validateNumeric(String input) {
        if (!input.trim().matches("-?\\d+")) {
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

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(NEGATIVE_OR_ZERO.getMessage());
        }
    }

    private static void validateUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_UNIT.getMessage());
        }
    }
}
