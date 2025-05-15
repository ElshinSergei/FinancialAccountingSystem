package view.commands;

import view.Command;
import view.ConsoleUI;

public class FinancialReport extends Command {

    public FinancialReport(ConsoleUI consoleUI) {
        super(consoleUI);
        menuText = "Финансовый отчет";
    }

    @Override
    public void execute() {

    }
}
