package view.commands.debitCard;

import view.ConsoleUI;
import view.Command;

public class CreateCard extends Command {

    public CreateCard(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Добавить дебетовую карту";
    }

    @Override
    public void execute() {
        consoleUI.createCard();
    }
}
