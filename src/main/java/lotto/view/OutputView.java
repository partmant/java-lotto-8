package lotto.view;

import lotto.dto.LottosDto;
import lotto.dto.WinningResultDto;

public final class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PURCHASED_COUNT_MESSAGE = "%d개를 구매했습니다.";

    private OutputView() {
    }

    public static void printPurchasedCount(int count) {
        printMessage(String.format(PURCHASED_COUNT_MESSAGE, count));
        printNewLine();
    }

    public static void printPurchasedLottos(LottosDto lottosDto) {
        printMessage(lottosDto.formatted());
        printNewLine();
    }

    public static void printWinningResult(WinningResultDto dto) {
        printMessage(dto.formatted());
    }

    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    private static void printMessage(String message) {
        System.out.print(message);
    }
}
