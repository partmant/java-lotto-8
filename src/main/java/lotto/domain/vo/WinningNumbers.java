package lotto.domain.vo;

import java.util.List;
import lotto.util.LottoValidator;
import lotto.util.WinningNumbersParser;

public final class WinningNumbers {

    private final List<Integer> numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static WinningNumbers from(String input) {
        List<Integer> parsedNumbers = WinningNumbersParser.parse(input);
        LottoValidator.validate(parsedNumbers);
        return new WinningNumbers(parsedNumbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int matchCountWith(Lotto lotto) {
        return lotto.countMatchingNumbers(numbers);
    }
}
