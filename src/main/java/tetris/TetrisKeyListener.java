package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisKeyListener extends KeyAdapter {

    private final TetrisGame game;

    public TetrisKeyListener(TetrisGame game){
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.rotate(-1);
                break;

            case KeyEvent.VK_DOWN:
                game.rotate(1);
                break;

            case KeyEvent.VK_LEFT:
                game.move(-1);
                break;
            case KeyEvent.VK_RIGHT:
                game.move(1);
                break;

            case KeyEvent.VK_SPACE:
                game.drop();
                break;
        }
        e.getComponent().repaint();
    }
}