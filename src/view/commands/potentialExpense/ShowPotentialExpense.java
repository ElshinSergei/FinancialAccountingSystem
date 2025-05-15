package view.commands.potentialExpense;

import view.Command;
import view.ConsoleUI;

public class ShowPotentialExpense extends Command {

    public ShowPotentialExpense(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Показать потенциальные расходы";
    }

    @Override
    public void execute() {
        consoleUI.showAllPotentialExpenses();
    }
}
