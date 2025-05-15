package view.commands.currentExpense;

import view.Command;
import view.ConsoleUI;

public class ShowCurrentExpense extends Command {

    public ShowCurrentExpense(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Показать все текущие расходы";
    }

    @Override
    public void execute() {
        consoleUI.showExpenses();
    }
}
