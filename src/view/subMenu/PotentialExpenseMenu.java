package view.subMenu;

import view.Command;
import view.ConsoleUI;
import view.commands.potentialExpense.AddPotentialExpense;
import view.commands.potentialExpense.ShowPotentialExpense;

import java.util.ArrayList;
import java.util.List;

public class PotentialExpenseMenu extends Command{
    private final List<Command> commands;

    public PotentialExpenseMenu(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Потенциальные расходы";
        commands = new ArrayList<>();
        commands.add(new AddPotentialExpense(consoleUI));
        commands.add(new ShowPotentialExpense(consoleUI));
    }

    @Override
    public void execute() {
        consoleUI.showSubMenu(this);
    }

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nПотенциальные расходы\n");
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
