package lotto.service;

import lotto.domain.Lottos;
import lotto.domain.LottoMachine;
import lotto.domain.WinningLotto;
import lotto.domain.LottoResult;
import lotto.domain.vo.PurchaseAmount;

public final class LottoService {

    private LottoService() {
    }

    public static Lottos issueLottos(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.calculateLottoCount();
        return LottoMachine.generateMultiple(count);
    }

    public static double calculateProfitRate(Lottos lottos, WinningLotto winningLotto, PurchaseAmount purchaseAmount) {
        LottoResult result = calculateResult(lottos, winningLotto);
        return result.profitRate(purchaseAmount);
    }

    public static LottoResult calculateResult(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateResult(winningLotto);
    }
}
