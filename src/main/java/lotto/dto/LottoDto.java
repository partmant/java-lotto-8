package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;

public final class LottoDto {

    private final List<Integer> numbers;

    private LottoDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.numbers());
    }

    public String formatted() {
        String joined = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + joined + "]";
    }
}
