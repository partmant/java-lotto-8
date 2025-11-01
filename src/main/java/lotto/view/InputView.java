package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {

    private static final String PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_GUIDE_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_GUIDE_MESSAGE = "보너스 번호를 입력해 주세요.";

    private InputView() {
    }

    public static String readPurchaseAmount() {
        return readLineWithGuide(PURCHASE_AMOUNT_GUIDE_MESSAGE);
    }

    public static String readWinningNumbers() {
        return readLineWithGuide(WINNING_NUMBERS_GUIDE_MESSAGE);
    }

    public static String readBonusNumber() {
        return readLineWithGuide(BONUS_NUMBER_GUIDE_MESSAGE);
    }

    public static void close() {
        Console.close();
    }

    private static String readLineWithGuide(String guideMessage) {
        System.out.println(guideMessage);
        return Console.readLine();
    }
}
