package entity;

import entity.enums.ExpenseCategory;
import entity.enums.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Потенциальные расходы
 */
public class PotentialExpense extends FinancialOperation {
    private ExpenseCategory category;

    public PotentialExpense(BigDecimal amount, ExpenseCategory category, LocalDate date,
                            String description) {
        super(amount, date, description);
        this.category = category;
    }

    public ExpenseCategory getCategory() {
        return category;
    }
}
