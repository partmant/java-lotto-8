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
        WinningLotto winningLotto = createWinningLotto(winningNumbers);
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
                OutputView.printNewLine();
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers readWinningNumbers() {
        while (true) {
            try {
                return WinningNumbers.from(InputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                OutputView.printNewLine();
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto createWinningLotto(WinningNumbers winningNumbers) {
        while (true) {
            try {
                BonusNumber bonusNumber = BonusNumber.from(InputView.readBonusNumber());
                return WinningLotto.of(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printNewLine();
                OutputView.printError(e.getMessage());
            }
        }
    }
}
