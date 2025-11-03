package lotto.util;

import static lotto.exception.ErrorMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WinningNumbersParser {

    private static final String separator = ",";

    private WinningNumbersParser() {
    }

    public static List<Integer> parse(String input) {
        validateNotEmpty(input);

        return Stream.of(input.split(separator))
                .map(String::trim)
                .peek(WinningNumbersParser::validateNumeric)
                .map(WinningNumbersParser::parseToInt)
                .collect(Collectors.toList());
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY.getMessage());
        }
    }

    private static void validateNumeric(String input) {
        if (!input.trim().matches("-?\\d+")) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }

    private static int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(OUT_OF_INTEGER_RANGE.getMessage());
        }
    }
}
