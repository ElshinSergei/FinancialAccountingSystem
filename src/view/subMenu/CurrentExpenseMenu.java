package view.subMenu;

import view.Command;
import view.ConsoleUI;
import view.commands.currentExpense.AddCurrentExpense;
import view.commands.currentExpense.ShowCurrentExpense;

import java.util.ArrayList;
import java.util.List;

public class CurrentExpenseMenu extends Command {
    private final List<Command> commands;

    public CurrentExpenseMenu(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Текущие расходы";
        commands = new ArrayList<>();
        commands.add(new AddCurrentExpense(consoleUI));
        commands.add(new ShowCurrentExpense(consoleUI));
    }

    @Override
    public void execute() {
        consoleUI.showSubMenu(this);
    }

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nТекущие расходы\n");
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
