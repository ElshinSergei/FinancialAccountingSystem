package service;

import entity.CurrentExpense;
import entity.DebitCard;
import entity.Wallet;
import entity.enums.ExpenseCategory;
import repository.CurrentExpenseRepository;
import repository.DebitCardRepository;
import repository.WalletRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CurrentExpenseService {
    private final CurrentExpenseRepository currentExpenseRepository;
    private final WalletRepository walletRepository;
    private final DebitCardRepository debitCardRepository;

    public CurrentExpenseService(CurrentExpenseRepository currentExpenseRepository,
                                 WalletRepository walletRepository,
                                 DebitCardRepository debitCardRepository) {
        this.currentExpenseRepository = currentExpenseRepository;
        this.walletRepository = walletRepository;
        this.debitCardRepository = debitCardRepository;
    }

    /**
     * Добавление текущего расхода
     * @param amountStr
     * @param categoryStr
     * @param description
     * @param sourceId
     */
    public void addCurrentExpense(String amountStr, String categoryStr, String description, String sourceId) {

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

        if (sourceId.length() < 2) {
            throw new IllegalArgumentException("Неверный формат источника платежа");
        }

        String paymentMethod = resolvePaymentMethod(sourceId); // определение осточника оплаты

        if (sourceId.startsWith("W")) {
            walletRepository.withdraw(amount, sourceId);
        } else if (sourceId.startsWith("C")) {
            debitCardRepository.withdraw(amount, sourceId);
        } else {
            throw new IllegalArgumentException("Неверный формат источника платежа");
        }

        LocalDate currentDate = LocalDate.now();

        CurrentExpense currentExpense = new CurrentExpense(amount, category, currentDate, description, paymentMethod);
        currentExpenseRepository.add(currentExpense);
    }

    /**
     * Определение способа оплаты
     * @param sourceId
     * @return
     */
    private String resolvePaymentMethod(String sourceId) {
        if(sourceId.startsWith("W")) {
            long id = Long.parseLong(sourceId.substring(1));
            Wallet wallet = walletRepository.getWalletById(id);
            return "Кошелек: " + wallet.getNameWallet();
        } else if (sourceId.startsWith("C")) {
            long id = Long.parseLong(sourceId.substring(1));
            DebitCard debitCard = debitCardRepository.getDebitCardById(id);
            return "Карта: " + debitCard.getBankName() + " " + debitCard.getCardNumber();
        } else {
            throw new IllegalArgumentException("Неизвестный источник оплаты");
        }
    }

    /**
     * Получить все текущие расходы
     * @return
     */
    public List<CurrentExpense> getAllExpenses() {
        return currentExpenseRepository.getAll();
    }
}
