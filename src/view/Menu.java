package view;

import view.commands.Exit;
import view.commands.FinancialReport;
import view.subMenu.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Command> commands;

    public Menu(ConsoleUI consoleUI) {
        commands = new ArrayList<>();
        commands.add(new WalletMenu(consoleUI));
        commands.add(new CardMenu(consoleUI));
        commands.add(new CurrentExpenseMenu(consoleUI));
        commands.add(new PotentialIncomeMenu(consoleUI));
        commands.add(new PotentialExpenseMenu(consoleUI));
        commands.add(new FinancialReport(consoleUI));
        commands.add(new Exit(consoleUI));
    }

    public String printMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Меню\n");
        for (int i = 0; i < commands.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(commands.get(i).getMenuText()).append("\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int choice) {
        Command command = commands.get(choice - 1);
        command.execute();
    }

    public int getSize() {
        return commands.size();
    }
}
