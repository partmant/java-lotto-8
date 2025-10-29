package lotto.domain.vo;

import lotto.util.PurchaseAmountValidator;

public final class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private PurchaseAmount(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount from(String input) {
        int validatedAmount = PurchaseAmountValidator.validate(input);
        return new PurchaseAmount(validatedAmount);
    }

    public int calculatePurchasableCount() {
        return amount / LOTTO_PRICE;
    }
}
