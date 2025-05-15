package view.commands.debitCard;

import view.ConsoleUI;
import view.Command;

public class UpdateCard extends Command {

    public UpdateCard(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Изменить данные карты";
    }

    @Override
    public void execute() {
        consoleUI.updateCard();
    }
}
