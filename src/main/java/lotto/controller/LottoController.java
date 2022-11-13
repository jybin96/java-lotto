package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.Buyer;
import lotto.Winning;
import lotto.service.LottoService;
import lotto.system.SystemValid;
import lotto.type.ErrorType;
import lotto.type.Rank;

public class LottoController {
    private final LottoService lottoService = new LottoService();

    public void run() {
        Buyer buyer = buy();
        Winning winning = Pick();
        List<Rank> lottosRank = buyer.getLottosRank(winning.getNumbers(), winning.getBonusNumber());
        for (Rank rank : lottosRank) {
            System.out.println(rank.getReward());
        }
    }

    private Buyer buy() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        SystemValid.validateForNumber(input, ErrorType.MONEY);
        int money = Integer.parseInt(input);
        System.out.println();
        return lottoService.buy(money);
    }

    private Winning Pick() {
        return lottoService.pick(winningPick(), bonusPick());
    }

    private List<Integer> winningPick() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        SystemValid.validateForWinningNumber(input);
        System.out.println();
        return lottoService.winningInputParseToList(input);
    }

    private int bonusPick() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        SystemValid.validateForNumber(input, ErrorType.BONUS);
        return Integer.parseInt(input);
    }
}
