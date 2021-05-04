public class MancalaBoard extends BoardPainter {
    private boolean turn; // true represents Player A and false = Player B
    //private Stack<> moves;
    CommandManager cm = new CommandManager();
    public MancalaBoard(Style style, int stonesPerPit) {
        super(style, stonesPerPit);
        turn = true;
    }

    // This method will handle moving stones when a pit is clicked.
    public void pitPressed(int position) throws IllegalStateException {
        /*System.out.println("Pit " + position + " has been clicked.");
        if (position == 12 || position == 13) {
            throw new IllegalStateException("Error: End mancalas cannot be clicked.");
        }
        if (position >= 0 && position <= 5) {
            if (turn) {
                throw new IllegalStateException("Error: It's Player A's turn right now.\nClick a pit from A1-A6.");
            }
        }
        if (position >= 6 && position <= 11) {
            if (!turn) {
                throw new IllegalStateException("Error: It's Player B's turn right now.\nClick a pit from B1-B6.");
            }
        }
        PitPanel current = pits.get(position);
        int stones = current.getPit().getStones();
        if (stones == 0) {
            throw new IllegalStateException("Error: Clicking an empty pit is\nnot a move. Try another pit.");

        }


        Pit next = current.getPit().getNext();
        while (stones > 0) {
            current.getPit().loseStone();
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

        if (inMyRow(lastPit.getPosition()) && lastPit.getStones() == 1
                && !oppositePit.isEmpty()) {
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
            pits.get(11).getPit().setNext(pits.get(12).getPit());
        } else if (!turn) {
            pits.get(11).getPit().setNext(pits.get(5).getPit());
        }
        if (!turn) {
            pits.get(0).getPit().setNext(pits.get(13).getPit());
        } else if (turn) {
            pits.get(0).getPit().setNext(pits.get(6).getPit());
        }

        if (rowAEmpty()) {
            mancala = pits.get(13).getPit();
            for (int i = 0; i <= 5; i++) {
                mancala.addMany(pits.get(i).getPit().emptyAll());
            }
            // move all stones from row B to Mancala B
        } else if (rowBEmpty()) {
            mancala = pits.get(12).getPit();
            for (int i = 6; i <= 11; i++) {
                mancala.addMany(pits.get(i).getPit().emptyAll());
            }
            // move all stones from row A to Mancala A
        }

        for (PitPanel p : pits) {
            System.out.println("Pit " + p.getPit().getPosition() + " stones: " + p.getPit().getStones());
        }*/
        cm.executeCommand(new stoneMove(this, position));
    }

    private boolean rowAEmpty() {
        return pits.get(6).getPit().isEmpty() && pits.get(7).getPit().isEmpty() && pits.get(8).getPit().isEmpty()
                && pits.get(9).getPit().isEmpty() && pits.get(10).getPit().isEmpty() && pits.get(11).getPit().isEmpty();
    }

    private boolean rowBEmpty() {
        return pits.get(0).getPit().isEmpty() && pits.get(1).getPit().isEmpty() && pits.get(2).getPit().isEmpty()
                && pits.get(3).getPit().isEmpty() && pits.get(4).getPit().isEmpty() && pits.get(5).getPit().isEmpty();
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
            return pits.get(thisPitPosition + 6).getPit();
        else
            return pits.get(thisPitPosition - 6).getPit();

    }

    private Pit getCurrentMancala() {
        if (turn)
            return pits.get(12).getPit();
        else
            return pits.get(13).getPit();
    }

    private class stoneMove implements Command {
        private MancalaBoard model;
        private int previousStones;
        private boolean previousTurn;
        private int pe;
        
        public stoneMove(MancalaBoard model, int pe)
        {
        	this.model = model;
        	this.pe = pe;
        	previousStones = model.pits.get(pe).getPit().getStones();
        	previousTurn = model.turn;
        }

        public void execute() 
        {
        	System.out.println("Pit " + pe + " has been clicked.");
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
            PitPanel current = pits.get(pe);
            int stones = current.getPit().getStones();
            if (stones == 0) {
                throw new IllegalStateException("Error: Clicking an empty pit is\nnot a move. Try another pit.");

            }


            Pit next = current.getPit().getNext();
            while (stones > 0) {
                current.getPit().loseStone();
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

            if (inMyRow(lastPit.getPosition()) && lastPit.getStones() == 1
                    && !oppositePit.isEmpty()) {
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
                pits.get(11).getPit().setNext(pits.get(12).getPit());
            } else if (!turn) {
                pits.get(11).getPit().setNext(pits.get(5).getPit());
            }
            if (!turn) {
                pits.get(0).getPit().setNext(pits.get(13).getPit());
            } else if (turn) {
                pits.get(0).getPit().setNext(pits.get(6).getPit());
            }

            if (rowAEmpty()) {
                mancala = pits.get(13).getPit();
                for (int i = 0; i <= 5; i++) {
                    mancala.addMany(pits.get(i).getPit().emptyAll());
                }
                // move all stones from row B to Mancala B
            } else if (rowBEmpty()) {
                mancala = pits.get(12).getPit();
                for (int i = 6; i <= 11; i++) {
                    mancala.addMany(pits.get(i).getPit().emptyAll());
                }
                // move all stones from row A to Mancala A
            }

            for (PitPanel p : pits) {
                System.out.println("Pit " + p.getPit().getPosition() + " stones: " + p.getPit().getStones());
            }
        }

        public void undo() 
        {
        	if(canUndo())
        	{
        		System.out.println("Undo button clicked.");
        	}
        }
    }
    public boolean canUndo()
    {
    	return cm.isUndoAvailable();
    }
    class Move {
        int pitPressed;
        int stonesMoved;

        public Move(int pit, int stones) {
            pitPressed = pit;
            stonesMoved = stones;
        }
    }

}
