package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import wormgame.domain.Piece;
import wormgame.game.WormGame;

public class DrawingBoard extends JPanel implements Updatable {

    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame newGame, int length) {
        super.setBackground(Color.GRAY);
        wormGame = newGame;
        pieceLength = length;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); 
        graphics.setColor(Color.BLACK);
        for (Piece piece : wormGame.getWorm().getPieces()) {
            graphics.fill3DRect(piece.getX()*pieceLength, piece.getY()*pieceLength, pieceLength, pieceLength, false);
        }
        graphics.setColor(Color.red);
        graphics.fillOval(wormGame.getApple().getX()*pieceLength, wormGame.getApple().getY()*pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
    }

}
