package entity;

import entity.enums.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Потенциальные доходы
 */
public class PotentialIncome extends FinancialOperation {
    private IncomeCategory category;

    public PotentialIncome(BigDecimal amount, IncomeCategory category, LocalDate date, String description) {
        super(amount, date, description);
        this.category = category;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public void setCategory(IncomeCategory category) {
        this.category = category;
    }
}
