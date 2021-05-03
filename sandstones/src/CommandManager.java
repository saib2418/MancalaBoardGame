public class CommandManager {
    private Command lastCommand;

    public CommandManager() {
    }

    public void executeCommand(Command c) {
        c.execute();
        lastCommand = c;

    }

    public boolean isUndoAvailable() {
        return lastCommand != null;
    }

    public void undo() {
        assert (lastCommand != null);
        lastCommand.undo();
        lastCommand = null;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> df4f39562f6a332b423da3e1753ada03381b1da1
