import java.util.ArrayList;

/**
 * Represents the logic part of the game, according to the rules of Mancala.
 *
 * @version 5.0
 * @authors Rhea, Sai, Rammy
 */
public class MancalaBoard extends BoardPainter {
    private boolean turn; // true represents Player A and false = Player B
    private ArrayList<Pit> pits;
    CommandManager cm = new CommandManager();
    public int undoCounter = 0;

    /**
     * Constructor that takes a style and the number of stones per pit. Calls the super constructor
     * to build the GUI, sets the first player to A, and initializes this class's pits.
     *
     * @param style        theme in which board is drawn
     * @param stonesPerPit either 3 or 4 stones per pit
     */
    public MancalaBoard(Style style, int stonesPerPit) {
        super(style, stonesPerPit);
        turn = true;
        setPits();
    }

    /**
     * Private method for initializing pits based on GUI class's pit panels
     */
    private void setPits() {
        ArrayList<PitPanel> panels = getPitPanels();
        pits = new ArrayList<>();
        for (PitPanel pitPanel : panels) {
            pits.add(pitPanel.getPit());
        }
    }

    /**
     * Method called whenever a pit is pressed, in order to conduct a move. It saves this move
     * as the most recently called command. It throws an exception if an empty pit is clicked,
     * a mancala is clicked, or a pit from the other player's row is clicked.
     *
     * @param position the position of the pit pressed
     * @throws IllegalStateException error thrown if an incorrect move is attempted
     */
    public void pitPressed(int position) throws IllegalStateException {
        cm.executeCommand(new stoneMove(this, position));
    }

    /**
     * Private method for setting label displaying whose turn it is.
     */
    private void setPlayerLabel() {
        if (turn)
            player.setText("Player A's turn");
        else
            player.setText("Player B's turn");
    }

    /**
     * Private helper method that determines if row A (bottom row) is empty.
     *
     * @return whether pits A1-A6 are empty or not
     */
    private boolean rowAEmpty() {
        return pits.get(6).isEmpty() && pits.get(7).isEmpty() && pits.get(8).isEmpty()
                && pits.get(9).isEmpty() && pits.get(10).isEmpty() && pits.get(11).isEmpty();
    }

    /**
     * Helper method that determines if row B (top row) is empty.
     *
     * @return whether pits B1-B6 are empty or not
     */
    private boolean rowBEmpty() {
        return pits.get(0).isEmpty() && pits.get(1).isEmpty() && pits.get(2).isEmpty()
                && pits.get(3).isEmpty() && pits.get(4).isEmpty() && pits.get(5).isEmpty();
    }

    /**
     * Returns whether a pit belongs to the current player
     *
     * @param pos position of pit
     * @return whether pit is in current player's row
     */
    private boolean inMyRow(int pos) {
        if (turn) {
            return pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11;
        } else {
            return pos == 0 || pos == 1 || pos == 2 || pos == 3 || pos == 4 || pos == 5;
        }

    }

    /**
     * Returns the pit opposite the current pit, in the other player's row
     *
     * @param thisPit current player's pit
     * @return opponent's pit directly across
     */
    private Pit getOppositePit(Pit thisPit) {
        int thisPitPosition = thisPit.getPosition();
        if (thisPitPosition >= 0 && thisPitPosition <= 5)
            return pits.get(thisPitPosition + 6);
        else
            return pits.get(thisPitPosition - 6);

    }

    /**
     * Gets the mancala of the current player
     *
     * @return mancala of current player
     */
    private Pit getCurrentMancala() {
        if (turn)
            return pits.get(12);
        else
            return pits.get(13);
    }

    /**
     * Inner class that represents a move by a player
     *
     * @version 2.0
     * @authors Sai, Rammy
     */
    private class stoneMove implements Command {
        private MancalaBoard model;
        private int previousStones;
        private boolean previousTurn;
        private int pe;
        private int counter;
        private Pit previousLastPit;
        private int originalStones;
        private int oppositeStones;
        private Pit playerMancala;

        /**
         * Constructor that takes a mancala board model, and the currently pressed pit
         *
         * @param model mancala board model
         * @param pe    current pit pressed
         */
        public stoneMove(MancalaBoard model, int pe) {
            this.model = model;
            this.pe = pe;
            previousStones = model.pits.get(pe).getStones();
            previousTurn = model.turn;
            originalStones = 1;

        }

        /**
         * Moves all the stones in the current pit around the board in counterclockwise motion. Yields
         * an error if a mancala is pressed, an empty pit is pressed, or an opponent's pitis pressed.
         * Grants a bonus turn if a player finishes in their mancala. If player finshes in an empty pit on
         * their row, and there are stones in the pit directly opposite, the player wins all those stones
         * as well as the empty stone.
         */
        public void execute() {
            setPlayerLabel();
            if (pe == 12 || pe == 13) {
                throw new IllegalStateException("Error: End mancalas cannot be clicked.");
            }
            if (pe >= 0 && pe <= 5) {
                if (turn) {
                    throw new IllegalStateException("Error: It's Player A's turn right now.\nClick a pit from A1-A6.");
                }
            }
            if (pe >= 6 && pe <= 11) {
                if (!turn) {
                    throw new IllegalStateException("Error: It's Player B's turn right now.\nClick a pit from B1-B6.");
                }
            }
            Pit current = pits.get(pe);
            int stones = current.getStones();
            if (stones == 0) {
                throw new IllegalStateException("Error: Clicking an empty pit is\nnot a move. Try another pit.");

            }

            Pit next = current.getNext();
            while (stones > 0) {
                current.loseStone();
                if (stones == 1 && inMyRow(next.getPosition()) && !getOppositePit(next).isEmpty()) {
                    originalStones = next.getStones();
                    Pit o = getOppositePit(next);
                    oppositeStones = o.getStones();
                }
                next.addStone();
                next = next.getNext();
                stones--;

            }

            Pit lastPit = next.getPrev();
            Pit oppositePit = getOppositePit(lastPit);
            Pit mancala = getCurrentMancala();
            previousLastPit = lastPit;
            playerMancala = mancala;

            if (inMyRow(lastPit.getPosition()) && lastPit.getStones() == 1
                    && !oppositePit.isEmpty()) {
                mancala.addMany(lastPit.emptyAll());
                mancala.addMany(oppositePit.emptyAll());
            }


            if (!((turn && next.prev.getPosition() == 12) || (!turn && next.prev.getPosition() == 13))) {
                turn = !turn;
                setPlayerLabel();
                if (undoCounter > 3) {
                    undoCounter = 0;
                }

            } else {
                if (undoCounter > 3) {
                    undoCounter = 0;
                }
            }

            if (turn) {
                pits.get(11).setNext(pits.get(12));
                pits.get(6).setPrev(pits.get(0));

            } else if (!turn) {
                pits.get(11).setNext(pits.get(5));
                pits.get(6).setPrev(pits.get(13));

            }
            if (!turn) {
                pits.get(5).setPrev(pits.get(11));
                pits.get(0).setNext(pits.get(13));
            } else if (turn) {
                pits.get(5).setPrev(pits.get(12));
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

        }

        /**
         * Undoes the move if the player has executed a move, and if the player has not yet committed 3 undos.
         * Grants the player a new turn.
         */
        public void undo() {
            if (canUndo()) {
                if (!previousLastPit.equals(playerMancala)) {
                    turn = !turn;
                    setPlayerLabel();
                }

                if (turn) {
                    pits.get(11).setNext(pits.get(12));
                    pits.get(6).setPrev(pits.get(0));
                } else if (!turn) {
                    pits.get(11).setNext(pits.get(5));
                    pits.get(6).setPrev(pits.get(13));
                }
                if (!turn) {
                    pits.get(5).setPrev(pits.get(11));
                    pits.get(0).setNext(pits.get(13));

                } else if (turn) {
                    pits.get(5).setPrev(pits.get(12));
                    pits.get(0).setNext(pits.get(6));

                }
                Pit current = previousLastPit;
                if (!current.equals(playerMancala)) {
                    if (originalStones == 0) {
                        int removeStones = oppositeStones + 1;
                        while (removeStones > 0) {
                            playerMancala.loseStone();
                            removeStones--;
                        }
                        getOppositePit(current).addMany(oppositeStones);
                        current = current.getPrev();
                        int undoStones = previousStones - 1;
                        while (undoStones > 0) {
                            current.loseStone();
                            current = current.getPrev();
                            undoStones--;
                        }
                        current.addMany(previousStones);
                    } else {
                        int undoStones = previousStones;
                        while (undoStones > 0) {
                            current.loseStone();
                            current = current.getPrev();
                            undoStones--;
                        }
                        current.addMany(previousStones);
                    }
                } else if (current.equals(playerMancala)) {
                    int undoStones = previousStones;
                    while (undoStones > 0) {
                        current.loseStone();
                        current = current.getPrev();
                        undoStones--;
                    }
                    current.addMany(previousStones);
                }

            }
            undoCounter++;
        }
    }

    /**
     * Checks whether the player has undone 3 times, and whether they have made a move yet
     *
     * @return true if player has undone less than 3 times and if player has made a move
     */
    public boolean canUndo() {
        if (undoCounter >= 3) {
            return false;
        }

        return cm.isUndoAvailable();
    }

}
