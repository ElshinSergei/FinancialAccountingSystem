package view.commands.wallet;

import view.ConsoleUI;
import view.Command;

public class DeleteWallet extends Command {

    public DeleteWallet(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Удалить кошелек";
    }

    @Override
    public void execute() {
        consoleUI.deleteWallet();
    }
}
