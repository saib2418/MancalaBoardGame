public class MancalaBoard extends BoardPainter {
    private boolean turn; // true represents Player A and false = Player B

    public MancalaBoard(Style style, java.util.List<Integer> mancalaBoard) {
        super(style, mancalaBoard);
        turn = true;
    }

    // This method will handle moving stones when a pit is clicked.
    public void pitPressed(int position) {
        System.out.println("Pit " + position + " has been clicked.");
        if (position == 12 || position == 13)
            return; // TODO add error message saying goal pits can't be clicked
        // TODO this should NOT count as a move or cause game to switch to other player
        if (position >= 0 && position <= 5) {
            if (turn) {
                return;// TODO add error message saying goal pits can't be clicked
                // TODO this should NOT count as a move or cause game to switch to other player
            }
        }
        if (position >= 6 && position <= 11) {
            if (!turn) {
                return;// TODO add error message saying goal pits can't be clicked
                // TODO this should NOT count as a move or cause game to switch to other player
            }
        }
        Pit current = pits.get(position);
        int stones = current.getStones();
        if (stones == 0)
            return; // TODO add error message saying empty pits don't count as a move
        // TODO this should NOT count as a move or cause game to switch to other player

        // TODO check whether this pit belongs to the current player, add error message if not

        Pit next = current.getNext();
        while (stones > 0) {
            current.loseStone();
            next.addStone();
            this.repaint();
            next = next.getNext();
            stones--;
        }

        System.out.println("After stones loop");

        if (!((turn && next.prev.getPosition() == 12) || (!turn && next.prev.getPosition() == 13))) {

            if (turn) {
                turn = false;
            } else if (!turn) {
                turn = true;
            }

        }
        if (turn) {
            pits.get(11).setNext(pits.get(12));
        } else if (!turn) {
            pits.get(11).setNext(pits.get(5));
        }
        if (!turn) {
            pits.get(0).setNext(pits.get(13));
        } else if (turn) {
            pits.get(0).setNext(pits.get(6));
        }
        for (Pit p : pits) {
            System.out.println("Pit " + p.getPosition() + " stones: " + p.getStones());
        }
        if (turn && rowAEmpty()) {
            // move all stones from row B to Mancala B
        } else if (!turn && rowBEmpty()) {
            // move all stones from row A to Mancala A
        }
    }


    private boolean rowAEmpty() {
        return pits.get(0).empty() && pits.get(1).empty() && pits.get(2).empty()
                && pits.get(3).empty() && pits.get(4).empty() && pits.get(5).empty();
    }

    private boolean rowBEmpty() {
        return pits.get(6).empty() && pits.get(7).empty() && pits.get(8).empty()
                && pits.get(9).empty() && pits.get(10).empty() && pits.get(11).empty();
    }


}
