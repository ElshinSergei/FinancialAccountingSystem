package controller;

import entity.PotentialIncome;
import repository.PotentialIncomeRepository;
import service.PotentialIncomeService;
import view.View;

import java.util.List;

public class PotentialIncomeController {
    private final PotentialIncomeService potentialIncomeService;
    private final View view;

    public PotentialIncomeController(View view, PotentialIncomeRepository potentialIncomeRepository) {
        potentialIncomeService = new PotentialIncomeService(potentialIncomeRepository);
        this.view = view;
    }

    /**
     * Добавить потенциальный доход
     * @param amountStr
     * @param categoryStr
     * @param dateStr
     * @param description
     */
    public void addPotentialIncome(String amountStr, String categoryStr, String dateStr, String description) {
        try {
            potentialIncomeService.addPotentialIncome(amountStr, categoryStr, dateStr, description);
            view.printAnswer("Потенциальный доход успешно добавлен");
        } catch (IllegalArgumentException e) {
            view.printAnswer("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Показать все потенциальные доходы
     */
    public void showPotentialIncomes() {
        List<PotentialIncome> potentialIncomes = potentialIncomeService.getPotentialIncomes();

        if (potentialIncomes.isEmpty()) {
            view.printAnswer("Нет добавленных операций");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nСписок потенциальных доходов:\n");
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("| %-12s | %-15s | %-10s | %-20s |%n",
                "Дата", "Сумма", "Категория", "Описание"));
        sb.append("------------------------------------------------------------\n");

        for (PotentialIncome potentialIncome : potentialIncomes) {
            sb.append(String.format("| %-12s | %-15s | %-10s | %-20s |%n",
                    potentialIncome.getDate(),
                    potentialIncome.getAmount() + " ₽",
                    potentialIncome.getCategory(),
                    potentialIncome.getDescription()));
        }
        sb.append("------------------------------------------------------------\n");

        view.printAnswer(sb.toString());
    }
}
