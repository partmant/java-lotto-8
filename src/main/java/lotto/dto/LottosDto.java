package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lottos;

public final class LottosDto {

    private final List<LottoDto> lottos;

    private LottosDto(List<LottoDto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public static LottosDto from(Lottos domain) {
        List<LottoDto> list = domain.values().stream()
                .map(LottoDto::from)
                .collect(Collectors.toList());
        return new LottosDto(list);
    }

    public String formatted() {
        return lottos.stream()
                .map(LottoDto::formatted)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public List<LottoDto> lottos() {
        return List.copyOf(lottos);
    }
}
