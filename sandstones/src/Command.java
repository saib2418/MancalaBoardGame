/**
 * An interface for representing a stone move.
 *
 * @version 1.0
 * @authors Sai and Rammy
 */
public interface Command {
    /**
     * Carry out a move.
     */
    public void execute();

    /**
     * Undo the move.
     */
    public void undo();

}
