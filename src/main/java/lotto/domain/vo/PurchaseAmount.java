package lotto.domain.vo;

import static lotto.exception.ErrorMessage.*;


public final class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;

    private static final String NUMERIC_REGEX = "-?\\d+";

    private final int amount;

    private PurchaseAmount(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount from(String input) {
        int validatedAmount = PurchaseAmount.validate(input);
        return new PurchaseAmount(validatedAmount);
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public double calculateProfitRate(long totalReward) {
        double rawRate = ((double) totalReward / amount) * 100;
        return Math.round(rawRate * 10.0) / 10.0;
    }

    private static int validate(String input) {
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
        if (!input.trim().matches(NUMERIC_REGEX)) {
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
