package view.commands.wallet;

import view.ConsoleUI;
import view.Command;

public class UpdateWallet extends Command {

    public UpdateWallet(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Изменить данные кошелька";
    }

    @Override
    public void execute() {
        consoleUI.updateWallet();
    }
}
