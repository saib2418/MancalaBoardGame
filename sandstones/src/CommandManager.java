/**
 * A view for managing the sequence of moves carried out by
 * the current player.
 *
 * @version 1.0
 * @authors Sai, Rammy
 */
public class CommandManager {
    private Command lastCommand;

    /**
     * Empty constructor
     */
    public CommandManager() {
    }

    /**
     * Sets the most recent command to the given parameter and executes it
     *
     * @param c most recent command
     */
    public void executeCommand(Command c) {
        c.execute();
        lastCommand = c;
    }

    /**
     * Getter for last command
     *
     * @return last command
     */
    public Command getLastCommand() {
        return lastCommand;
    }

    /**
     * Returns true if a move has been made, and false if no.
     *
     * @return whether last command is null
     */
    public boolean isUndoAvailable() {
        return lastCommand != null;
    }

    /**
     * Undos the most recent command and sets it to null
     */
    public void undo() {
        assert (lastCommand != null);
        lastCommand.undo();
        lastCommand = null;
    }
}

