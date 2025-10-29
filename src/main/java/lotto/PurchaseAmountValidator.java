package lotto;

public class PurchaseAmountValidator {

    private PurchaseAmountValidator() {
    }

    public static int validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력은 비어있을 수 없습니다.");
        }

        input = input.trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력은 비어있을 수 없습니다.");
        }

        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException("입력 값은 숫자여야 합니다.");
        }

        try {
            int amount =  Integer.parseInt(input);

            if (amount <= 0) {
                throw new IllegalArgumentException("구입 금액은 0보다 커야 합니다.");
            }

            if (amount % 1000 != 0) {
                throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
            }

            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 처리 가능한 정수 범위를 초과했습니다.");
        }
    }
}
