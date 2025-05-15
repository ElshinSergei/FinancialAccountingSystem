package view.commands;

import view.Command;
import view.ConsoleUI;

public class Exit extends Command {

    public Exit(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Выход";
    }

    @Override
    public void execute() {
        consoleUI.exit();
    }
}
