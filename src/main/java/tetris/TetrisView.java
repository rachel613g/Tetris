package tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisView extends JComponent {

    TetrisGame game;
    CurrentPiece currentPiece;

    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] point = tetrisShapes.getShapes();
    public static final int BORDER_CELL_SIZE = 26;
    public static final int CELL_SIZE = 25;

    public TetrisView(TetrisGame game) {
        this.game = game;
    }

    private void drawPiece(Graphics g) {
        currentPiece = game.getCurrentPiece();
        g.setColor(game.getColorArray()[currentPiece.getCurrPieceIndex()]);
        for (Point p : point[currentPiece.getCurrPieceIndex()][currentPiece.getRotation()]) {
            g.fillRect(( currentPiece.getX() + p.x ) * BORDER_CELL_SIZE, (currentPiece.getY() + p.y) * BORDER_CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, BORDER_CELL_SIZE * game.getWidth(), BORDER_CELL_SIZE * (game.getHeight()-1));
        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight()-1; j++) {
                g.setColor(game.getBoard()[i][j]);        //null
                g.fillRect(BORDER_CELL_SIZE * i, BORDER_CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Score is: " + game.getScore(), 19 * 12, CELL_SIZE);
        drawPiece(g);
    }
}
