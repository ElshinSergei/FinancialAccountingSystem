import repository.*;
import service.DataManager;
import view.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        WalletRepository walletRepository = new WalletRepository();
        DebitCardRepository debitCardRepository = new DebitCardRepository();
        CurrentExpenseRepository currentExpenseRepository = new CurrentExpenseRepository();
        PotentialExpenseRepository potentialExpenseRepository = new PotentialExpenseRepository();
        PotentialIncomeRepository potentialIncomeRepository = new PotentialIncomeRepository();

        DataManager dataManager = new DataManager(
                walletRepository,
                debitCardRepository,
                currentExpenseRepository,
                potentialExpenseRepository,
                potentialIncomeRepository);

        // Загрузка данных при старте
        dataManager.loadAll();

        ConsoleUI consoleUI = new ConsoleUI(walletRepository, debitCardRepository, currentExpenseRepository,
                potentialExpenseRepository, potentialIncomeRepository);

        // Сохранение данных при завершении
        Runtime.getRuntime().addShutdownHook(new Thread(dataManager::saveAll));

        consoleUI.run();
    }
}
