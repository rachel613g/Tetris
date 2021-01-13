package tetris;

import java.awt.*;

public class TetrisGame {

    private final Grid grid;
    private final TetrisShapes tetrisShapes = new TetrisShapes();
    private final Point[][][] tetrisPoints = tetrisShapes.getShapes();

    private final CurrentPieceFactory factory;
    private CurrentPiece currentPiece;
    private int score;
    private boolean isGameOver;
    private final int RIGHT_BORDER = 12;
    private final int BOTTOM_BORDER = 22;
    public static final int INIT_X_POSITION = 6;
    public static final int INIT_Y_POSITION = 0;

    public TetrisGame(Grid grid, CurrentPieceFactory currentPieceFactory){
        this.grid = grid;
        factory = currentPieceFactory;
        startGame();
    }

    public CurrentPiece getCurrentPiece(){
        return currentPiece;
    }

    public int getScore(){
        return score;
    }

    public void startGame() {
        factory.createRandomCollection();
        if(score != 0){
            score = 0;
        }
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight() -1; j++) {
                if (i == 0 || i == RIGHT_BORDER || j == BOTTOM_BORDER) {
                    grid.getBoard()[i][j] = grid.getBorder();
                } else {
                    grid.getBoard()[i][j] = grid.getBackground();
                }
            }
        }
        newPiece();
        isGameOver = false;
    }

    private void newPiece() {
        currentPiece = factory.newInstance();
    }

    /**
     *
     * @param x current x of the piece
     * @param y current y of the piece
     * @param rotation current rotation of the piece
     * @return whether there is no collision or not. If a collision happens, returns false.
     * If a collision does not happen, returns true.
     */
    private boolean noCollisionAt(int x, int y, int rotation) {
        for (Point p : tetrisPoints[currentPiece.getCurrPieceIndex()][rotation]) {
            if (grid.getBoard()[p.x + x][p.y + y] != grid.getBackground()) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param rotateBy is either -1 or 1. If rotateBy is 1, it
     * goes clockwise. If -1, then counter clockwise. If go clockwise at
     * rotation 0, then it would be -1 == rotation 3
     */
    public void rotate(int rotateBy) {
        int newRotation = (currentPiece.getRotation() + rotateBy) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (noCollisionAt(currentPiece.getX(), currentPiece.getY(), newRotation)) {
            currentPiece.setRotation(newRotation);
        }
    }

    public void move(int moveBy) {
        if (noCollisionAt(currentPiece.getX()+ moveBy, currentPiece.getY(), currentPiece.getRotation())) {
            currentPiece.setX(moveBy);
        }
    }

    public void drop() {
        if (noCollisionAt(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getRotation())) {
             currentPiece.setY(1);
        } else {
            attachToBoard();
        }
    }

    private void attachToBoard() {
        for (Point p : tetrisPoints[currentPiece.getCurrPieceIndex()][currentPiece.getRotation()]) {
            int newX = currentPiece.getX() + p.x;
            int newY = currentPiece.getY() + p.y;
            if (newY == INIT_Y_POSITION + 1) {
                isGameOver = true;
                break;
            }
            grid.getBoard()[newX][newY] = grid.getColorArray()[currentPiece.getCurrPieceIndex()];
        }
        if (!isGameOver) {
            clearRows();
            newPiece();
        }
    }


    public void deleteRow(int row) {
        //j first so col is first
        for (int j = row - 1; j > 0; j--) {
            for (int i = 1; i < grid.getWidth(); i++) {
                grid.getBoard()[i][j + 1] = grid.getBoard()[i][j];
            }
        }
    }

    public void clearRows() {
        boolean gap;
        int numClear = 0;
        for (int j = grid.getHeight() - 3; j > 0; j--) {
            gap = false;
            for (int i = 1; i < grid.getWidth(); i++) {
                if (grid.getBoard()[i][j] == grid.getBackground()) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);
                j++;
                numClear++;
            }
        }
        switch (numClear) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 350;
                break;
            case 3:
                score += 600;
                break;
            case 4:
                score += 800;
                break;
            default:
                score += 10;
                break;
        }
    }

    public boolean isGameOver(){
        return isGameOver;
    }
}
