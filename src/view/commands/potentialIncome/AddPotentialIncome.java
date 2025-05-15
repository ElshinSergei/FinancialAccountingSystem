package view.commands.potentialIncome;

import view.Command;
import view.ConsoleUI;

public class AddPotentialIncome extends Command {
    public AddPotentialIncome(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Добавить потенциальный доход";
    }

    @Override
    public void execute() {
        consoleUI.addPotentialIncome();
    }
}
