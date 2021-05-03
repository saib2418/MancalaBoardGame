/**
 * paints a pot
 */
public class Pit {
    private int stones;
    private final int position;
    public Pit next;
    public Pit prev;

    public Pit(int pos) {
        this.position = pos;
    }

    public int getPosition() {
        return position;
    }

    public Pit getPrev() {
        return prev;
    }

    public Pit getNext() {
        return next;
    }

    public void setPrev(Pit p) {
        this.prev = p;
    }

    public void setNext(Pit p) {
        this.next = p;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getStones() {
        return this.stones;
    }

    public boolean isEmpty() {
        return this.stones == 0;
    }

    public int emptyAll() {
        int numStones = stones;
        stones = 0;
        return numStones;

    }

    public void addStone() {
        stones++;

    }

    public void addMany(int newStones) {
        stones += newStones;
    }

    public void loseStone() {
        stones--;
    }


    public String toString() {
        String str = "";
        str += "\nPit #" + position;
        str += "\nStones: " + stones;
        str += "\nNext: " + next.position;
        str += "\nPrev: " + prev.position;
        return str;
    }


}
