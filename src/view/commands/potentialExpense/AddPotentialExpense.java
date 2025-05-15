package view.commands.potentialExpense;

import view.Command;
import view.ConsoleUI;

public class AddPotentialExpense extends Command {

    public AddPotentialExpense(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Добавить потенциальный расход";
    }

    @Override
    public void execute() {
        consoleUI.addPotentialExpense();
    }
}
