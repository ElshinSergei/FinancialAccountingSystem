package service;

import controller.PotentialIncomeController;
import entity.PotentialIncome;
import entity.enums.IncomeCategory;
import repository.PotentialIncomeRepository;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class PotentialIncomeService {
    private final PotentialIncomeRepository potentialIncomeRepository;

    public PotentialIncomeService(PotentialIncomeRepository potentialIncomeRepository) {
        this.potentialIncomeRepository = potentialIncomeRepository;
    }

    public void addPotentialIncome(String amountStr, String categoryStr, String dateStr, String description) {

        BigDecimal amount;
        try {
            amount = new BigDecimal(amountStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат суммы");
        }

        IncomeCategory category;
        try {
            category= IncomeCategory.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неизвестная категория: " + categoryStr);
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Неверный формат даты. Используйте гггг-мм-дд");
        }


        PotentialIncome potentialIncome = new PotentialIncome(amount, category, date, description);
        potentialIncomeRepository.add(potentialIncome);
    }

    public List<PotentialIncome> getPotentialIncomes() {
        return potentialIncomeRepository.getAll();
    }
}
