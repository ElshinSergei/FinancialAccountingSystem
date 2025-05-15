package controller;

import entity.PotentialExpense;
import repository.PotentialExpenseRepository;
import service.PotentialExpenseService;
import view.View;

import java.util.List;

public class PotentialExpenseController {
    private final PotentialExpenseService potentialExpenseService;
    private final View view;

    public PotentialExpenseController(View view, PotentialExpenseRepository potentialExpenseRepository) {
        potentialExpenseService = new PotentialExpenseService(potentialExpenseRepository);
        this.view = view;
    }

    /**
     * Добавить потенциальный расход
     * @param amountStr
     * @param categoryStr
     * @param dateStr
     * @param description
     */
    public void addPotentialExpense(String amountStr, String categoryStr, String dateStr, String description) {
       try {
           potentialExpenseService.addPotentialExpense(amountStr, categoryStr, dateStr, description);
           view.printAnswer("Потенциальный расход успешно добавлен");
       } catch (IllegalArgumentException e) {
           view.printAnswer("Ошибка: " + e.getMessage());
       }
    }

    /**
     * Показать все потенциальные расходы
     */
    public void showAllPotentialExpenses() {

        List<PotentialExpense> potentialExpenses = potentialExpenseService.getAllPotentialExpenses();

        if (potentialExpenses.isEmpty()) {
            view.printAnswer("Нет добавленных расходов");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nСписок потенциальных расходов:\n");
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("| %-12s | %-15s | %-10s | %-20s |%n",
                "Дата", "Сумма", "Категория", "Описание"));
        sb.append("------------------------------------------------------------\n");

        for (PotentialExpense expense : potentialExpenses) {
            sb.append(String.format("| %-12s | %-15s | %-10s | %-20s |%n",
                    expense.getDate(),
                    expense.getAmount() + " ₽",
                    expense.getCategory(),
                    expense.getDescription()));
        }
        sb.append("------------------------------------------------------------\n");

        view.printAnswer(sb.toString());
    }
}
