package service;

import repository.*;

import java.io.File;

public class DataManager {
    private final WalletRepository walletRepository;
    private final DebitCardRepository debitCardRepository;
    private final CurrentExpenseRepository currentExpenseRepository;
    private final PotentialExpenseRepository potentialExpenseRepository;
    private final PotentialIncomeRepository potentialIncomeRepository;

    public DataManager(WalletRepository walletRepository,
                       DebitCardRepository debitCardRepository,
                       CurrentExpenseRepository currentExpenseRepository,
                       PotentialExpenseRepository potentialExpenseRepository,
                       PotentialIncomeRepository potentialIncomeRepository) {
        new File("data").mkdirs();
        this.walletRepository = walletRepository;
        this.debitCardRepository = debitCardRepository;
        this.currentExpenseRepository = currentExpenseRepository;
        this.potentialExpenseRepository = potentialExpenseRepository;
        this.potentialIncomeRepository = potentialIncomeRepository;
    }

    /**
     * Сохранение в файл
     */
    public void saveAll() {
        walletRepository.saveToFile("data/wallets.txt");
        debitCardRepository.saveToFile("data/cards.txt");
        currentExpenseRepository.saveToFile("data/currentExpenses.txt");
        potentialExpenseRepository.saveToFile("data/potentialExpenses.txt");
        potentialIncomeRepository.saveToFile("data/potentialIncomes.txt");
    }

    /**
     * Загрузка из файла
     */
    public void loadAll() {
        walletRepository.loadFromFile("data/wallets.txt");
        debitCardRepository.loadFromFile("data/cards.txt");
        currentExpenseRepository.loadFromFile("data/currentExpenses.txt");
        potentialExpenseRepository.loadFromFile("data/potentialExpenses.txt");
        potentialIncomeRepository.loadFromFile("data/potentialIncomes.txt");
    }
}
