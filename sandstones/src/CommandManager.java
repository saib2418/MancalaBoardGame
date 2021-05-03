public class CommandManager {
    private Command lastCommand;

    public CommandManager() {
    }

    public void executeCommand(Command c) {
        c.execute();
        lastCommand = c;
        
    }
}
