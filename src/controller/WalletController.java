package controller;

import entity.Wallet;
import repository.WalletRepository;
import service.WalletService;
import view.View;

import java.math.BigDecimal;
import java.util.Map;

public class WalletController {
    private final WalletService walletService;
    private final View view;

    public WalletController(View view, WalletRepository walletRepository) {
        walletService = new WalletService(walletRepository);
        this.view = view;
    }

    /**
     * Создать кошелек
     * @param nameWallet
     * @param balanceWallet
     */
    public void createWallet(String nameWallet, String balanceWallet) {
        try {
            BigDecimal balance = new BigDecimal(balanceWallet);
            walletService.createWallet(nameWallet, balance);
            view.printAnswer("Кошелек успешно создан");
        } catch (NumberFormatException e) {
            view.printAnswer("Ошибка: неверный формат баланса");
        }
    }

    /**
     * Удалить кошелек
     * @param idStr
     */
    public void deleteWallet(String idStr) {
        try {
            long id = Long.parseLong(idStr);
            if (walletService.deleteWallet(id)) {
                view.printAnswer("Кошелек успешно удален");
            } else {
                view.printAnswer("Кошелек с указанным ID не найден");
            }
        } catch (NumberFormatException e) {
            view.printAnswer("Ошибка: неверный формат ID");
        }
    }

    /**
     * Показать все кошельки
     */
    public void showAllWallets() {
        Map<Long, Wallet> wallets = walletService.getAllWallets();
        if (wallets.isEmpty()) {
            view.printAnswer("Нет созданных кошельков");
        } else {
            StringBuilder sb = new StringBuilder("Список кошельков:\n");
            wallets.forEach((id, wallet) ->
                    sb.append("ID: ").append(id)
                            .append(", Имя: ").append(wallet.getNameWallet())
                            .append(", Баланс: ").append(wallet.getBalance())
                            .append("\n"));
            view.printAnswer(sb.toString());
        }
    }

    /**
     * Изменить данные кошелька
     * @param idStr
     * @param newName
     * @param newBalanceStr
     */
    public void updateWallet(String idStr, String newName, String newBalanceStr) {
       try {
           long id = Long.parseLong(idStr);
           BigDecimal newBalance = new BigDecimal(newBalanceStr);
           if (walletService.updateWallet(id, newName, newBalance)) {
               view.printAnswer("Данные кошелька успешно обновлены");
           } else {
               view.printAnswer("Кошелек с указанным ID не найден");
           }
       } catch (NumberFormatException e) {
           view.printAnswer("Ошибка: неверный формат данных");
       }
    }
}
