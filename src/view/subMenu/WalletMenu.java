package view.subMenu;

import view.ConsoleUI;
import view.Command;
import view.commands.wallet.CreateWallet;
import view.commands.wallet.DeleteWallet;
import view.commands.wallet.ShowAllWallets;
import view.commands.wallet.UpdateWallet;

import java.util.ArrayList;
import java.util.List;

public class WalletMenu extends Command {
    private final List<Command> commands;

    public WalletMenu(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Управление кошельками";
        commands = new ArrayList<>();
        commands.add(new CreateWallet(consoleUI));
        commands.add(new ShowAllWallets(consoleUI));
        commands.add(new UpdateWallet(consoleUI));
        commands.add(new DeleteWallet(consoleUI));
    }

    @Override
    public void execute() {
        consoleUI.showSubMenu(this);
    }

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nУправление кошельками\n");
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
