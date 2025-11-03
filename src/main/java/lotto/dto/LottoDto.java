package lotto.dto;

import java.util.List;

import lotto.domain.vo.Lotto;

public final class LottoDto {
    private final List<Integer> numbers;

    private LottoDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.numbers());
    }

    public List<Integer> numbers() {
        return numbers;
    }
}
