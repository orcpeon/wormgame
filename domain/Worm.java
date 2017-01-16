package wormgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import wormgame.Direction;
import static wormgame.Direction.DOWN;
import static wormgame.Direction.LEFT;
import static wormgame.Direction.RIGHT;
import static wormgame.Direction.UP;

public class Worm {

    private int x;
    private int y;
    private Direction direction;
    private ArrayList<Piece> pieces;
    private boolean growing = false;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        x = originalX;
        y = originalY;
        direction = originalDirection;
        pieces = new ArrayList<Piece>();
        pieces.add(new Piece(x, y));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public int getLength() {
        return pieces.size();
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void move() {

        if (pieces.size() < 3) {  //grow if length is <3
            growing = true;
        }

        if (direction.equals(UP)) {
            pieces.add(new Piece(x, y - 1));
            y--;
        } else if (direction.equals(RIGHT)) {
            pieces.add(new Piece(x + 1, y));
            x++;
        } else if (direction.equals(DOWN)) {
            pieces.add(new Piece(x, y + 1));
            y++;
        } else if (direction.equals(LEFT)) {
            pieces.add(new Piece(x - 1, y));
            x--;
        }
        if (!growing) {
            pieces.remove(0);
        }
        growing = false;
    }

    public void grow() {

        growing = true;

    }

    public boolean runsInto(Piece piece) {
        for (Piece item : pieces) {
            if (item.getX() == piece.getX() && item.getY() == piece.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean runsIntoItself() {
        for (Piece piece : pieces) {
            if (piece!= pieces.get(pieces.size()-1) && pieces.size()>1 && piece.getX() == x && piece.getY() == y) {
                return true;
            }
        }
        return false;
    }

}
