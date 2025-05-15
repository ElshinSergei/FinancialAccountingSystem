package view.commands.debitCard;

import view.ConsoleUI;
import view.Command;

public class DeleteCard extends Command {

    public DeleteCard(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Удалить дебетовую карту";
    }

    @Override
    public void execute() {
        consoleUI.deleteCard();
    }
}
