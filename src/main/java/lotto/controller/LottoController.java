package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.LottosDto;
import lotto.dto.WinningResultDto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class LottoController {

    public void run() {
        // 1. 구입 금액 입력
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        OutputView.printNewLine();

        // 2. 로또 발행 및 구매 내역 출력
        Lottos lottos = LottoService.issueLottos(purchaseAmount);
        OutputView.printPurchasedCount(purchaseAmount.calculateLottoCount());
        OutputView.printPurchasedLottos(LottosDto.from(lottos));
        OutputView.printNewLine();

        // 3. 당첨 번호 입력
        WinningNumbers winningNumbers = readWinningNumbers();
        OutputView.printNewLine();

        // 4. 보너스 번호 입력
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);
        OutputView.printNewLine();

        // 5. 결과 계산
        LottoResult result = LottoService.calculateResult(lottos, winningLotto);
        double profitRate = result.profitRate(purchaseAmount);

        // 6. 결과 출력
        OutputView.printWinningResult(WinningResultDto.from(result, profitRate));

        // 7. 자원 해제
        InputView.close();
    }

    private PurchaseAmount readPurchaseAmount() {
        while (true) {
            try {
                return PurchaseAmount.from(InputView.readPurchaseAmount());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
                OutputView.printNewLine();
            }
        }
    }

    private WinningNumbers readWinningNumbers() {
        while (true) {
            try {
                return WinningNumbers.from(InputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
                OutputView.printNewLine();
            }
        }
    }

    private BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                return BonusNumber.from(InputView.readBonusNumber());
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
                OutputView.printNewLine();
            }
        }
    }
}
