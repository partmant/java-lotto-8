package lotto.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoResult;
import lotto.domain.Rank;

public final class WinningResultDto {

    private final List<RankResultDto> rankResults;
    private final double profitRate;

    private WinningResultDto(List<RankResultDto> rankResults, double profitRate) {
        this.rankResults = List.copyOf(rankResults);
        this.profitRate = profitRate;
    }

    public static WinningResultDto from(LottoResult lottoResult, double profitRate) {
        List<RankResultDto> rankDtos = createRankResultDtos(lottoResult);
        return new WinningResultDto(rankDtos, profitRate);
    }

    private static List<RankResultDto> createRankResultDtos(LottoResult lottoResult) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .sorted(Comparator.comparingInt(Rank::matchCount)
                        .thenComparing(Rank::hasBonus))
                .map(rank -> RankResultDto.from(rank, lottoResult.countOf(rank)))
                .collect(Collectors.toList());
    }

    public List<RankResultDto> rankResults() {
        return List.copyOf(rankResults);
    }

    public double profitRate() {
        return profitRate;
    }
}
