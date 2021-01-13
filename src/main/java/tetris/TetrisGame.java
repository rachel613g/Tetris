package tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TetrisGame {

    private final Grid grid;

    public TetrisGame(Grid grid){
        this.grid = grid;
    }
    private final TetrisShapes tetrisShapes = new TetrisShapes();
    private final Point[][][] tetrisPoints = tetrisShapes.getShapes();
    private Point point;
    private int currPiece;
    private int rotation;
    private final ArrayList<Integer> nextPiece = new ArrayList<>();
    private int score;
    private boolean isGameOver;
    private final int RIGHT_BORDER = 12;
    private final int BOTTOM_BORDER = 22;
    public static final int INIT_X_POSITION = 6;
    public static final int INIT_Y_POSITION = 0;

    public int getCurrentPiece(){
        return currPiece;
    }

    public int getRotation(){
        return rotation;
    }

    public Point getPoint(){
        return point;
    }

    public int getScore(){
        return score;
    }

    public void startGame() {
        if(nextPiece.size() != 0){
            nextPiece.clear();
        }
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
        point = new Point(INIT_X_POSITION, INIT_Y_POSITION);
        rotation = 0;

        //in lieu of random piece generator
        if (nextPiece.isEmpty()) {
            Collections.addAll(nextPiece, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            Collections.shuffle(nextPiece);
        }

        currPiece = nextPiece.remove(0);
    }

    /**
     *
     * @param x current x of the piece
     * @param y current y of the piece
     * @param rotation current rotation of the piece
     * @return whether there is no collision or not. If a collision happens, returns false.
     * If a collision does not happen, returns true.
     */
    public boolean noCollisionAt(int x, int y, int rotation) {
        for (Point p : tetrisPoints[currPiece][rotation]) {
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
        int newRotation = (rotation + rotateBy) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (noCollisionAt(point.x, point.y, newRotation)) {
            rotation = newRotation;
        }
    }

    public void move(int moveBy) {
        if (noCollisionAt(point.x + moveBy, point.y, rotation)) {
            point.x += moveBy;
        }
    }

    public void drop() {
        if (noCollisionAt(point.x, point.y + 1, rotation)) {
            point.y += 1;
        } else {
            attachToBoard();
        }
    }

    private void attachToBoard() {
        for (Point p : tetrisPoints[currPiece][rotation]) {
            int newX = point.x + p.x;
            int newY = point.y + p.y;
            if (newY == INIT_Y_POSITION + 1) {
                isGameOver = true;
                break;
            }
            grid.getBoard()[newX][newY] = grid.getColorArray()[currPiece];
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
