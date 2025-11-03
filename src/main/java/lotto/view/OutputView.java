package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Rank;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;
import lotto.dto.RankResultDto;
import lotto.dto.WinningResultDto;

public final class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PURCHASED_COUNT_MESSAGE = "%d개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "당첨 통계";
    private static final String SEPARATOR_LINE = "---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %,.1f%%입니다.";

    private OutputView() {
    }

    public static void printPurchasedCount(int count) {
        println(String.format(PURCHASED_COUNT_MESSAGE, count));
    }

    public static void printPurchasedLottos(LottosDto lottosDto) {
        for (LottoDto lotto : lottosDto.lottos()) {
            String formatted = formatLotto(lotto.numbers());
            println(formatted);
        }
    }

    public static void printLottoResult(WinningResultDto dto) {
        println(STATISTICS_HEADER);
        println(SEPARATOR_LINE);

        for (RankResultDto result : dto.rankResults()) {
            println(formatRankResult(result));
        }

        System.out.printf(PROFIT_RATE_MESSAGE, dto.profitRate());
    }

    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static String formatLotto(List<Integer> numbers) {
        return "[" + numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }

    private static String formatRankResult(RankResultDto dto) {
        Rank rank = dto.rank();
        String matchMessage;

        if (rank.hasBonus()) {
            matchMessage = String.format("%d개 일치, 보너스 볼 일치", rank.matchCount());
        } else {
            matchMessage = String.format("%d개 일치", rank.matchCount());
        }

        return String.format("%s (%,d원) - %d개", matchMessage, rank.reward(), dto.count());
    }
}
