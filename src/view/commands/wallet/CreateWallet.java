package view.commands.wallet;

import view.ConsoleUI;
import view.Command;

public class CreateWallet extends Command {

    public CreateWallet(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Создать новый кошелек";
    }

    @Override
    public void execute() {
        consoleUI.createWallet();
    }
}
