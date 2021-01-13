package tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisView extends JComponent {

    TetrisGame game;
    Grid grid;

    public TetrisView(TetrisGame game, Grid grid) {
        this.game = game;
        this.grid = grid;
    }

    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] point = tetrisShapes.getShapes();
    public static final int BORDER_CELL_SIZE = 26;
    public static final int CELL_SIZE = 25;

    private void drawPiece(Graphics g) {
        g.setColor(grid.getColorArray()[game.getCurrentPiece()]);
        for (Point p : point[game.getCurrentPiece()][game.getRotation()]) {
            g.fillRect((p.x + game.getPoint().x) * BORDER_CELL_SIZE, (game.getPoint().y + p.y) * BORDER_CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, BORDER_CELL_SIZE * grid.getWidth(), BORDER_CELL_SIZE * (grid.getHeight()-1));
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight()-1; j++) {
                g.setColor(grid.getBoard()[i][j]);        //null
                g.fillRect(BORDER_CELL_SIZE * i, BORDER_CELL_SIZE * j, CELL_SIZE, CELL_SIZE);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Score is: " + game.getScore(), 19 * 12, CELL_SIZE);
        drawPiece(g);
    }

}
