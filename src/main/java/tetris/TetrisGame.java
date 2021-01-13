package tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TetrisGame {
    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] tetrisPoints = tetrisShapes.getShapes();

    private final Color sunset = new Color(250, 163, 152);
    private final Color peach = new Color(250, 221, 152);
    private final Color babyYellow = new Color(239, 250, 152);
    private final Color mint = new Color(150, 255, 175);
    private final Color teal = new Color(152, 250, 224);
    private final Color skyBlue = new Color(152, 209, 250);
    private final Color babyBlue = new Color(152, 174, 250);
    private final Color lightBlue = new Color(74, 180, 255);
    private final Color lavender = new Color(183, 152, 250);
    private final Color babyPink = new Color(241, 207, 255);
    private final Color background = new Color(7, 11, 59);
    private final Color border = new Color(1, 12, 138);

    private final Color[] colorArray = {babyPink, mint, lightBlue, sunset, peach,
            babyYellow, teal, lavender, skyBlue, babyBlue};

    private CurrentPieceFactory factory;
    private CurrentPiece currentPiece;
    private int score;
    private boolean isGameOver;
    public static final int WIDTH = 13;
    public static final int HEIGHT = 24;
    private final int RIGHT_BORDER = 12;
    private final int BOTTOM_BORDER = 22;
    public static final int INIT_X_POSITION = 6;
    public static final int INIT_Y_POSITION = 0;
    private final Color[][] board = new Color[WIDTH][HEIGHT];

    public TetrisGame(CurrentPieceFactory currentPieceFactory){
        factory = currentPieceFactory;
        startGame();
    }

    public Color[][] getBoard(){
        return board;
    }

    public Color[] getColorArray(){
        return colorArray;
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
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT -1; j++) {
                if (i == 0 || i == RIGHT_BORDER || j == BOTTOM_BORDER) {
                    board[i][j] = border;
                } else {
                    board[i][j] = background;
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
            if (board[p.x + x][p.y + y] != background) {
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
            board[newX][newY] = colorArray[currentPiece.getCurrPieceIndex()];
        }
        if (!isGameOver) {
            clearRows();
            newPiece();
        }
    }


    public void deleteRow(int row) {
        //j first so col is first
        for (int j = row - 1; j > 0; j--) {
            for (int i = 1; i < WIDTH; i++) {
                board[i][j + 1] = board[i][j];
            }
        }
    }

    public void clearRows() {
        boolean gap;
        int numClear = 0;
        for (int j = HEIGHT - 3; j > 0; j--) {
            gap = false;
            for (int i = 1; i < WIDTH; i++) {
                if (board[i][j] == background) {
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

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public boolean isGameOver(){
        return isGameOver;
    }
}