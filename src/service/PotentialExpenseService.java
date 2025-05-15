package service;

import controller.PotentialExpenseController;
import entity.PotentialExpense;
import entity.enums.ExpenseCategory;
import repository.PotentialExpenseRepository;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class PotentialExpenseService {
    private final PotentialExpenseRepository potentialExpenseRepository;

    public PotentialExpenseService(PotentialExpenseRepository potentialExpenseRepository) {
        this.potentialExpenseRepository = potentialExpenseRepository;
    }

    public void addPotentialExpense(String amountStr, String categoryStr, String dateStr, String description) {

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат суммы");
        }

        ExpenseCategory category;
        try {
            category = ExpenseCategory.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неизвестная категория: " + categoryStr);
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Неверный формат даты. Используйте гггг-мм-дд");
        }

        PotentialExpense potentialExpense = new PotentialExpense(amount, category, date, description);

        potentialExpenseRepository.add(potentialExpense);
    }

    public List<PotentialExpense> getAllPotentialExpenses() {
       return potentialExpenseRepository.getAll();
    }
}
