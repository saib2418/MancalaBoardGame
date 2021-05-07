/**
 * Model view of an individual pit (either ordinary or mancala). Contains a number
 * of stones and has a previous pit and a next, representing the counterclockwise cycle.
 *
 * @version 2.0
 * @authors Rhea, Sai, Rammy
 */
public class Pit {
    private int stones;
    private final int position;
    public Pit next;
    public Pit prev;

    /**
     * Constructor that takes a position
     *
     * @param pos position of pit
     */
    public Pit(int pos) {
        this.position = pos;
    }

    /**
     * Getter for position
     *
     * @return pit position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Getter for previous pit
     *
     * @return previous pit
     */
    public Pit getPrev() {
        return prev;
    }

    /**
     * Getter for next pit
     *
     * @return next pit
     */
    public Pit getNext() {
        return next;
    }

    /**
     * Setter for previous pit
     *
     * @param p previous pit
     */
    public void setPrev(Pit p) {
        this.prev = p;
    }

    /**
     * Setter for next pit
     *
     * @param p next pit
     */
    public void setNext(Pit p) {
        this.next = p;
    }

    /**
     * Sets the number of stones in pit
     *
     * @param stones number of stones
     */
    public void setStones(int stones) {
        this.stones = stones;
    }

    /**
     * Getter for number of stones in pit
     *
     * @return number of stones
     */
    public int getStones() {
        return this.stones;
    }

    /**
     * Returns whether pit is empty
     *
     * @return true if number of stones is 0
     */
    public boolean isEmpty() {
        return this.stones == 0;
    }

    /**
     * Sets number of stones to 0 and returns how many stones
     * used to be in pit. Models emptying the pit.
     *
     * @return number of stones in pit
     */
    public int emptyAll() {
        int numStones = stones;
        stones = 0;
        return numStones;

    }

    /**
     * Increments the number of stones by 1
     */
    public void addStone() {
        stones++;

    }

    /**
     * Adds stones to the pit
     *
     * @param newStones number of stones to be added
     */
    public void addMany(int newStones) {
        stones += newStones;
    }

    /**
     * Decremeents number of stones by 1
     */
    public void loseStone() {
        stones--;
    }

    /**
     * Returns string representation of pit
     *
     * @return string representation of pit
     */
    public String toString() {
        String str = "";
        str += "\nPit #" + position;
        str += "\nStones: " + stones;
        str += "\nNext: " + next.position;
        str += "\nPrev: " + prev.position;
        return str;
    }
    
}
