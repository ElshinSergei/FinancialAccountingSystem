package controller;

import entity.DebitCard;
import repository.DebitCardRepository;
import service.DebitCardService;
import view.View;

import java.math.BigDecimal;
import java.util.Map;

public class DebitCardController {
    private final DebitCardService debitCardService;
    private final View view;

    public DebitCardController(View view, DebitCardRepository debitCardRepository) {
        debitCardService = new DebitCardService(debitCardRepository);
        this.view = view;
    }

    /**
     * Добавить карту
     * @param cardNumber
     * @param bankName
     * @param balanceCard
     */
    public void createCard(String cardNumber, String bankName, String balanceCard) {
        try {
            BigDecimal balance = new BigDecimal(balanceCard);
            debitCardService.createDebitCard(cardNumber, bankName, balance);
            view.printAnswer("Карта успешно добавлена");
        } catch (NumberFormatException e) {
            view.printAnswer("Ошибка: неверный формат баланса");
        }
    }

    /**
     * Удалить карту
     * @param idStr
     */
    public void deleteDebitCard(String idStr) {
        try {
            Long id = Long.parseLong(idStr);
            if (debitCardService.deleteDebitCard(id)) {
                view.printAnswer("Карта успешно удалена");
            } else {
                view.printAnswer("Карта с указанным ID не найдена");
            }
        } catch (NumberFormatException e) {
            view.printAnswer("Ошибка: неверный формат ID");
        }
    }

    /**
     * Показать все карты
     */
    public void showAllCards() {
        Map<Long, DebitCard> cards = debitCardService.getAllCards();
        if (cards.isEmpty()) {
            view.printAnswer("Нет добавленных карт");
        } else {
            StringBuilder sb = new StringBuilder("Список дебетовых карт:\n");
            cards.forEach((id, debitCard) ->
                    sb.append("ID: ").append(id)
                            .append(", Номер карты: ").append(debitCard.getCardNumber())
                            .append(", Банк: ").append(debitCard.getBankName())
                            .append(", Баланс: ").append(debitCard.getBalance())
                            .append("\n"));
            view.printAnswer(sb.toString());
        }
    }

    /**
     * Изменить данные карты
     * @param idStr
     * @param newCardNumber
     * @param newNameBank
     * @param newBalanceCard
     */
    public void updateCard(String idStr, String newCardNumber, String newNameBank, String newBalanceCard) {
        try {
            Long id = Long.parseLong(idStr);
            BigDecimal newBalance = new BigDecimal(newBalanceCard);
            if (debitCardService.updateCard(id, newCardNumber, newNameBank, newBalance)) {
                view.printAnswer("Данные карты успешно обновлены");
            } else {
                view.printAnswer("Карта с указанным ID не найдена");
            }
        } catch (NumberFormatException e) {
            view.printAnswer("Ошибка: неверный формат данных");
        }
    }
}