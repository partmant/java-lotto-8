package lotto.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WinningNumbersParser {

    private static final String NULL_OR_EMPTY_ERROR_MESSAGE = "입력은 비어 있을 수 없습니다.";
    private static final String NON_NUMERIC_ERROR_MESSAGE = "당첨 번호는 숫자여야 합니다.";
    private static final String OUT_OF_INTEGER_RANGE_ERROR_MESSAGE = "입력값이 처리 가능한 정수 범위를 초과했습니다.";

    private WinningNumbersParser() {
    }

    public static List<Integer> parse(String input) {
        validateNotEmpty(input);

        return Stream.of(input.split(","))
                .map(String::trim)
                .peek(WinningNumbersParser::validateNumeric)
                .map(WinningNumbersParser::parseToInt)
                .collect(Collectors.toList());
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR_MESSAGE);
        }
    }

    private static void validateNumeric(String input) {
        if (!input.trim().matches("-?\\d+")) {
            throw new IllegalArgumentException(NON_NUMERIC_ERROR_MESSAGE);
        }
    }

    private static int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE_ERROR_MESSAGE);
        }
    }
}
