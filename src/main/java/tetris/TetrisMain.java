package tetris;

public class TetrisMain {
    public static void main(String[] args) {
        TetrisGame tetris = new TetrisGame();
        TetrisView view = new TetrisView(tetris);
        TetrisKeyListener keyListener = new TetrisKeyListener(tetris);
        TetrisFrame frame = new TetrisFrame(tetris,view, keyListener);
        frame.setVisible(true);
    }
}
