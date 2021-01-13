package tetris;

public class TetrisMain {
    public static void main(String[] args) {
        Grid grid = new Grid();
        TetrisGame tetris = new TetrisGame(grid);
        TetrisView view = new TetrisView(tetris, grid);
        TetrisKeyListener keyListener = new TetrisKeyListener(tetris);
        TetrisFrame frame = new TetrisFrame(tetris,view, keyListener);
        frame.setVisible(true);
    }
}
