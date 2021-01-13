package tetris;

public class TetrisMain {
    public static void main(String[] args) {
        CurrentPieceFactory factory = new CurrentPieceFactory();
        TetrisGame tetris = new TetrisGame(factory);
        TetrisView view = new TetrisView(tetris);
        TetrisKeyListener keyListener = new TetrisKeyListener(tetris);
        TetrisFrame frame = new TetrisFrame(tetris,view, keyListener);
        frame.setVisible(true);
    }
}
