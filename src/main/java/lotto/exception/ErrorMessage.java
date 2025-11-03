package lotto.exception;

public enum ErrorMessage {

    NULL_OR_EMPTY("입력은 비어 있을 수 없습니다."),
    NON_NUMERIC("입력 값은 숫자여야 합니다."),
    OUT_OF_INTEGER_RANGE("입력값이 처리 가능한 정수 범위를 초과했습니다."),

    // 구입 금액
    NEGATIVE_OR_ZERO("구입 금액은 0보다 커야 합니다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액은 1000원 단위여야 합니다."),

    // 당첨 번호
    INVALID_WINNING_NUMBER_COUNT("당첨 번호는 6개의 숫자로 구성되어야 합니다."),
    WINNING_NUMBER_DUPLICATE("당첨 번호는 중복된 숫자가 있을 수 없습니다."),
    INVALID_WINNING_NUMBER_RANGE("당첨 번호는 1부터 45사이의 숫자여야 합니다."),

    // 보너스 번호
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45사이의 숫자여야 합니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
