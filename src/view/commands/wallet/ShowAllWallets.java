package view.commands.wallet;

import view.ConsoleUI;
import view.Command;

public class ShowAllWallets extends Command {

    public ShowAllWallets(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Посмотреть все кошельки";
    }

    @Override
    public void execute() {
        consoleUI.showAllWallets();
    }
}
