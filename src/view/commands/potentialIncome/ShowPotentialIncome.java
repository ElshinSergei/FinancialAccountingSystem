package view.commands.potentialIncome;

import view.Command;
import view.ConsoleUI;

public class ShowPotentialIncome extends Command {
    public ShowPotentialIncome(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Показать потенциальные доходы";
    }

    @Override
    public void execute() {
        consoleUI.showPotentialIncomes();
    }
}
