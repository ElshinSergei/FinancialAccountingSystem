package view.subMenu;

import view.ConsoleUI;
import view.Command;
import view.commands.debitCard.CreateCard;
import view.commands.debitCard.DeleteCard;
import view.commands.debitCard.ShowAllCards;
import view.commands.debitCard.UpdateCard;

import java.util.ArrayList;
import java.util.List;

public class CardMenu extends Command {
    private final List<Command> commands;

    public CardMenu(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Управление картами";
        commands = new ArrayList<>();
        commands.add(new CreateCard(consoleUI));
        commands.add(new ShowAllCards(consoleUI));
        commands.add(new UpdateCard(consoleUI));
        commands.add(new DeleteCard(consoleUI));
    }

    @Override
    public void execute() {
        consoleUI.showSubMenu(this);
    }

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nУправление картами\n");
        for (int i = 0; i < commands.size(); i++) {
            sb.append(i + 1).append(". ").append(commands.get(i).getMenuText()).append("\n");
        }
        sb.append(commands.size() + 1).append(". Вернуться в главное меню\n");
        return sb.toString();
    }

    public void execute(int choice) {
        Command command = commands.get(choice - 1);
        command.execute();
    }

    public int getSize() {
        return commands.size();
    }
}
