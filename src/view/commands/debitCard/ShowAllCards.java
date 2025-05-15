package view.commands.debitCard;

import view.ConsoleUI;
import view.Command;

public class ShowAllCards extends Command {

    public ShowAllCards(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Посмотреть все карты";
    }

    @Override
    public void execute() {
        consoleUI.showAllCards();
    }
}
