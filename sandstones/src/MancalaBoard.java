public class MancalaBoard extends BoardPainter {
    private boolean turn; // true represents Player A and false = Player B

    public MancalaBoard(Style style, int stonesPerPit) {
        super(style, stonesPerPit);
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
            next = next.getNext();
            stones--;
        }


        Pit lastPit = next.getPrev();
        Pit oppositePit = getOppositePit(lastPit);
        Pit mancala = getCurrentMancala();

        System.out.println("Current player: " + (turn ? "A" : "B"));
        System.out.println("Last pit: " + lastPit);
        System.out.println("Opposite pit: " + oppositePit);
        System.out.println("Mancala: " + mancala);

        System.out.println(inMyRow(lastPit.getPosition()));
        System.out.println(lastPit.getStones() == 1);
        System.out.println(!oppositePit.isEmpty());

        if (inMyRow(lastPit.getPosition()) && lastPit.getStones() == 1
                && !oppositePit.isEmpty()) {
            System.out.println("empty pit grab");
            mancala.addMany(lastPit.emptyAll());
            mancala.addMany(oppositePit.emptyAll());
        }

        System.out.println("After stones loop");

        if (!((turn && next.prev.getPosition() == 12) ||
                (!turn && next.prev.getPosition() == 13))) {
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

        if (rowAEmpty()) {
            mancala = pits.get(13);
            for (int i = 0; i <= 5; i++) {
                mancala.addMany(pits.get(i).emptyAll());
            }
            // move all stones from row B to Mancala B
        } else if (rowBEmpty()) {
            mancala = pits.get(12);
            for (int i = 6; i <= 11; i++) {
                mancala.addMany(pits.get(i).emptyAll());
            }
            // move all stones from row A to Mancala A
        }

        for (Pit p : pits) {
            System.out.println("Pit " + p.getPosition() + " stones: " + p.getStones());
        }

    }


    private boolean rowAEmpty() {
        return pits.get(6).isEmpty() && pits.get(7).isEmpty() && pits.get(8).isEmpty()
                && pits.get(9).isEmpty() && pits.get(10).isEmpty() && pits.get(11).isEmpty();
    }

    private boolean rowBEmpty() {
        return pits.get(0).isEmpty() && pits.get(1).isEmpty() && pits.get(2).isEmpty()
                && pits.get(3).isEmpty() && pits.get(4).isEmpty() && pits.get(5).isEmpty();
    }

    private boolean inMyRow(int pos) {
        if (turn) {
            return pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11;
        } else {
            return pos == 0 || pos == 1 || pos == 2 || pos == 3 || pos == 4 || pos == 5;
        }

    }

    private Pit getOppositePit(Pit thisPit) {
        int thisPitPosition = thisPit.getPosition();
        if (thisPitPosition >= 0 && thisPitPosition <= 5)
            return pits.get(thisPitPosition + 6);
        else
            return pits.get(thisPitPosition - 6);

    }

    private Pit getCurrentMancala() {
        if (turn)
            return pits.get(12);
        else
            return pits.get(13);
    }


}
