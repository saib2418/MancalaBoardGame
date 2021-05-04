public class CommandManager {
    private Command lastCommand;

    public CommandManager() {
    }

    public void executeCommand(Command c) {
        c.execute();
        lastCommand = c;

    }

    public Command getLastCommand() {
        return lastCommand;
    }

    public boolean isUndoAvailable() {
        return lastCommand != null;
    }

    public void undo() {
        assert (lastCommand != null);
        lastCommand.undo();
        lastCommand = null;
    }
}

