package tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static tetris.TetrisGame.INIT_X_POSITION;
import static tetris.TetrisGame.INIT_Y_POSITION;

public class CurrentPieceFactory {
    private final ArrayList<Integer> nextPiece = new ArrayList<>();
    CurrentPiece currentPiece;

    public CurrentPieceFactory() {
        createRandomCollection();
    }

    public CurrentPiece newInstance() {
        currentPiece = new CurrentPiece();
        if (nextPiece.isEmpty()) {
            createRandomCollection();
        }
        currentPiece.setCurrPieceIndex(nextPiece.remove(0));
        currentPiece.setX(INIT_X_POSITION);
        currentPiece.setY(INIT_Y_POSITION);
        currentPiece.setRotation(0);
        return currentPiece;
    }

    public void createRandomCollection() {
        //in lieu of random piece generator
        Collections.addAll(nextPiece, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(nextPiece);
    }

}
