package view;

public abstract class Command {
    protected String menuText;
    protected ConsoleUI consoleUI;

    public Command(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    public String getMenuText() {
        return menuText;
    }

    public abstract void execute();

}
