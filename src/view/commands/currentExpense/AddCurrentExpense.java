package view.commands.currentExpense;

import view.Command;
import view.ConsoleUI;

public class AddCurrentExpense extends Command {

    public AddCurrentExpense(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Добавить текущий расход";
    }

    @Override
    public void execute() {
        consoleUI.addCurrentExpense();
    }
}
