package controller;

import entity.CurrentExpense;
import repository.CurrentExpenseRepository;
import repository.DebitCardRepository;
import repository.WalletRepository;
import service.CurrentExpenseService;
import view.View;

import java.util.List;

public class CurrentExpenseController {
    private final CurrentExpenseService currentExpenseService;
    private final View view;

    public CurrentExpenseController(View view, WalletRepository walletRepository,
                                    DebitCardRepository debitCardRepository,
                                    CurrentExpenseRepository currentExpenseRepository) {
        currentExpenseService = new CurrentExpenseService(currentExpenseRepository, walletRepository, debitCardRepository);
        this.view = view;
    }

    /**
     * Показать все текущие расходы
     */
    public void showAllExpenses() {

        List<CurrentExpense> currentExpenses = currentExpenseService.getAllExpenses();

        if (currentExpenses.isEmpty()) {
            view.printAnswer("Нет добавленных расходов");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nСписок текущих расходов:\n");
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("| %-4s | %-12s | %-15s | %-10s | %-20s | %-15s |%n",
                "ID", "Дата", "Сумма", "Категория", "Описание", "Способ оплаты"));
        sb.append("------------------------------------------------------------\n");

        for (CurrentExpense expense : currentExpenses) {
            sb.append(String.format("| %-4d | %-12s | %-15s | %-10s | %-20s | %-15s |%n",
                    expense.getId(),
                    expense.getDate(),
                    expense.getAmount() + " ₽",
                    expense.getCategory(),
                    expense.getDescription(),
                    expense.getPaymentMethod()));
        }
        sb.append("------------------------------------------------------------\n");

        view.printAnswer(sb.toString());
    }

    /**
     * Добавить текущий расход
     * @param amountStr
     * @param categoryStr
     * @param description
     * @param sourceId
     */
    public void addExpense(String amountStr, String categoryStr, String description, String sourceId) {
        try {
            currentExpenseService.addCurrentExpense(amountStr, categoryStr, description, sourceId);
            view.printAnswer("Текущий расход успешно добавлен");
        } catch (IllegalArgumentException e) {
            view.printAnswer("Ошибка: " + e.getMessage());
        }
    }

}
