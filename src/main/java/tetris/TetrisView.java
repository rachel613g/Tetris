package tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisView extends JComponent {

    TetrisGame game;

    public TetrisView(TetrisGame game) {
        this.game = game;
    }


    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] point = tetrisShapes.getShapes();
    private final int BOARDER_CELL_SIZE = 26;
    private final int CELL_SIZE = 25;

    private void drawPiece(Graphics g) {
        g.setColor(game.getColorArray()[game.getCurrentPiece()]);
        for (Point p : point[game.getCurrentPiece()][game.getRotation()]) {
            g.fillRect((p.x + game.getPoint().x) * BOARDER_CELL_SIZE, (game.getPoint().y + p.y) * BOARDER_CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, BOARDER_CELL_SIZE * game.getWidth(), BOARDER_CELL_SIZE * game.getHeight()-1);
        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight()-1; j++) {
                g.setColor(game.getBoard()[i][j]);        //null
                g.fillRect(BOARDER_CELL_SIZE * i, BOARDER_CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Score is: " + game.getScore(), 19 * 12, CELL_SIZE);
        drawPiece(g);
    }
}
