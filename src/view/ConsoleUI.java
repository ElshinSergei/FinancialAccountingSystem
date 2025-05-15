package view;

import controller.*;
import entity.enums.ExpenseCategory;
import entity.enums.IncomeCategory;
import repository.*;
import view.subMenu.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI implements View {
    private final WalletController walletController;
    private final DebitCardController debitCardController;
    private final CurrentExpenseController currentExpenseController;
    private final PotentialExpenseController potentialExpenseController;
    private final PotentialIncomeController potentialIncomeController;
    private final Scanner scanner;
    private final Menu menu;
    private boolean work;

    public ConsoleUI(WalletRepository walletRepository,
                     DebitCardRepository debitCardRepository,
                     CurrentExpenseRepository currentExpenseRepository,
                     PotentialExpenseRepository potentialExpenseRepository,
                     PotentialIncomeRepository potentialIncomeRepository) {
        potentialIncomeController = new PotentialIncomeController(this, potentialIncomeRepository);
        potentialExpenseController = new PotentialExpenseController(this, potentialExpenseRepository);
        walletController = new WalletController(this, walletRepository);
        debitCardController = new DebitCardController(this, debitCardRepository);
        currentExpenseController = new CurrentExpenseController(this, walletRepository, debitCardRepository,
                currentExpenseRepository);
        scanner = new Scanner(System.in);
        menu = new Menu(this);
        work = true;
    }


    public void run() {
        System.out.println("Система учетов финансов\n");
        while (work) {
            System.out.println(menu.printMenu());
            execute();
        }
        System.out.println("Программа завершила свою работу");
    }

    private void execute() {
        try {
            System.out.print("Введите соответстующий пункт меню: ");
            int numMenu = scanner.nextInt();
            scanner.nextLine();
            menu.execute(numMenu);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка. Введите число");
            scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка. Неверный пункт меню");
        }
    }

    public void showSubMenu(Command menu) {
        while (true) {
            // Выводим подменю
            if (menu instanceof WalletMenu) {
                System.out.println(((WalletMenu) menu).printMenu());
            } else if (menu instanceof CardMenu) {
                System.out.println(((CardMenu) menu).printMenu());
            } else if (menu instanceof CurrentExpenseMenu) {
                System.out.println(((CurrentExpenseMenu) menu).printMenu());
            } else if (menu instanceof PotentialExpenseMenu) {
                System.out.println(((PotentialExpenseMenu) menu).printMenu());
            } else if (menu instanceof PotentialIncomeMenu) {
                System.out.println(((PotentialIncomeMenu) menu).printMenu());
            }
            // Получаем выбор пользователя
            System.out.print("Выберите пункт: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число!");
                scanner.nextLine();
                continue;
            }
            // Обработка выхода из подменю
            int exitChoice;
            if (menu instanceof WalletMenu) {
                exitChoice = ((WalletMenu) menu).getSize() + 1;
            } else if (menu instanceof CardMenu) {
                exitChoice = ((CardMenu) menu).getSize() + 1;
            } else if (menu instanceof CurrentExpenseMenu) {
                exitChoice = ((CurrentExpenseMenu) menu).getSize() + 1;
            } else if (menu instanceof PotentialIncomeMenu) {
                exitChoice = ((PotentialIncomeMenu) menu).getSize() + 1;
            } else {
                exitChoice = ((PotentialExpenseMenu) menu).getSize() + 1;
            }
            if (choice == exitChoice) {
                break;
            }

            // Выполняем выбранную команду
            try {
                if (menu instanceof WalletMenu) {
                    ((WalletMenu) menu).execute(choice);
                } else if (menu instanceof CardMenu) {
                    ((CardMenu) menu).execute(choice);
                } else if (menu instanceof CurrentExpenseMenu) {
                    ((CurrentExpenseMenu) menu).execute(choice);
                } else if (menu instanceof PotentialIncomeMenu) {
                    ((PotentialIncomeMenu) menu).execute(choice);
                } else {
                    ((PotentialExpenseMenu) menu).execute(choice);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неверный пункт меню!");
            }
        }
    }

    @Override
    public void printAnswer(String info) {
        System.out.println(info);
    }

    /**
     * Создание нового кошелька
     */
    public void createWallet() {
        System.out.println("Введите имя кошелька: ");
        String nameWallet = scanner.nextLine();
        System.out.println("Введите баланс кошелька: ");
        String balanceWallet = scanner.nextLine();
        walletController.createWallet(nameWallet, balanceWallet);
    }

    /**
     * Удаление кошелька
     */
    public void deleteWallet() {
        System.out.println("Введите id кошелька для удаления: ");
        String id = scanner.nextLine();
        walletController.deleteWallet(id);
    }

    /**
     * Изменить данные кошелька
     */
    public void updateWallet() {
        System.out.println("Введите ID кошелька для изменения: ");
        String id = scanner.nextLine();
        System.out.println("Введите новое имя кошелька: ");
        String newName = scanner.nextLine();
        System.out.println("Введите новый баланс: ");
        String newBalance = scanner.nextLine();
        walletController.updateWallet(id, newName, newBalance);
    }

    /**
     * Показать все кошельки
     */
    public void showAllWallets() {
        walletController.showAllWallets();
    }

    /**
     * Добавление карты
     */
    public void createCard() {
        System.out.println("Введите номер дебетовой карты: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Введите название Банка: ");
        String bankName = scanner.nextLine();
        System.out.println("Введите баланс карты: ");
        String balanceCard = scanner.nextLine();
        debitCardController.createCard(cardNumber, bankName, balanceCard);
    }

    /**
     * Удалить карту
     */
    public void deleteCard() {
        System.out.println("Введите id карты для удаления: ");
        String id = scanner.nextLine();
        debitCardController.deleteDebitCard(id);
    }

    /**
     * Изменить данные карты
     */
    public void updateCard() {
        System.out.println("Введите ID карты для изменения: ");
        String id = scanner.nextLine();
        System.out.println("Введите новый номер карты: ");
        String newCardNumber = scanner.nextLine();
        System.out.println("Введите новое название Банка: ");
        String newNameBank = scanner.nextLine();
        System.out.println("Введите новый баланс: ");
        String newBalanceCard = scanner.nextLine();
        debitCardController.updateCard(id, newCardNumber, newNameBank, newBalanceCard);
    }

    /**
     * Показать все карты
     */
    public void showAllCards() {
        debitCardController.showAllCards();
    }

    /**
     * Добавление текущего расхода
     */
    public void addCurrentExpense() {
        System.out.println("\n=== Добавление текущего расхода ===");

        System.out.println("Введите сумму расхода:");
        String amountStr = scanner.nextLine();

        System.out.println("Введите категорию: (" +
        String.join(", ", Arrays.stream(ExpenseCategory.values())
                .map(Enum::name)
                .toArray(String[]::new)) + "):");
        String categoryStr = scanner.nextLine();

        System.out.println("Введите описание: ");
        String description = scanner.nextLine();

        System.out.println("\nВыберите способ оплаты:");
        walletController.showAllWallets();
        debitCardController.showAllCards();
        System.out.print("Введите ID (например, W1 или C2): ");
        String sourceId = scanner.nextLine();

        currentExpenseController.addExpense(amountStr,categoryStr, description, sourceId);
    }

    /**
     * Показать все текущие расходы
     */
    public void showExpenses() {
        currentExpenseController.showAllExpenses();
    }

    /**
     * Добавление потенциального расхода
     */
    public void addPotentialExpense() {
        System.out.println("\n=== Добавление потенциального расхода ===");

        System.out.println("Введите сумму расхода:");
        String amountStr = scanner.nextLine();

        System.out.println("Введите категорию (" +
        String.join(", ", Arrays.stream(ExpenseCategory.values())
                .map(Enum::name)
                .toArray(String[]::new)) + "):");
        String categoryStr = scanner.nextLine();

        System.out.println("Введите планируемую дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        System.out.println("Введите описание: ");
        String description = scanner.nextLine();

        potentialExpenseController.addPotentialExpense(amountStr, categoryStr, dateStr, description);
    }

    /**
     * Показать все потенциальные расходы
     */
    public void showAllPotentialExpenses() {
        potentialExpenseController.showAllPotentialExpenses();
    }

    /**
     * Добавление потенциального дохода
     */
    public void addPotentialIncome() {
        System.out.println("\n=== Добавление потенциального дохода ===");

        System.out.println("Введите сумму дохода");
        String amountStr = scanner.nextLine();

        System.out.println("Выберите категорию (" +
        String.join(", ", Arrays.stream(IncomeCategory.values())
                .map(Enum::name)
                .toArray(String[]::new)) + "):");
        String categoryStr = scanner.nextLine();

        System.out.println("Введите планируемую дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        System.out.println("Введите описание: ");
        String description = scanner.nextLine();
        potentialIncomeController.addPotentialIncome(amountStr, categoryStr, dateStr, description);

    }

    /**
     * Показать все потенциальные доходы
     */
    public void showPotentialIncomes() {
        potentialIncomeController.showPotentialIncomes();
    }

    public void exit () {
            work = false;
        }
    }
