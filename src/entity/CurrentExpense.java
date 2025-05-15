package entity;

import entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Текущие расходы
 */
public class CurrentExpense extends FinancialOperation {
    private ExpenseCategory category;
    private final String paymentMethod;   // способ оплаты

    public CurrentExpense(BigDecimal amount, ExpenseCategory category, LocalDate date, String description, String paymentMethod) {
        super(amount, date, description);
        this.paymentMethod = paymentMethod;
        this.category = category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ExpenseCategory getCategory() {
        return category;
    }
}
