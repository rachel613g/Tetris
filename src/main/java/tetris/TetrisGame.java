package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TetrisGame {

    public TetrisGame(){

    }

    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] tetrisPoints = tetrisShapes.getShapes();

    Color sunset = new Color(250, 163, 152);
    Color peach = new Color(250, 221, 152);
    Color babyYellow = new Color(239, 250, 152);
    Color mint = new Color(150, 255, 175);
    Color teal = new Color(152, 250, 224);
    Color skyBlue = new Color(152, 209, 250);
    Color babyBlue = new Color(152, 174, 250);
    Color lightBlue = new Color(74, 180, 255);
    Color lavender = new Color(183, 152, 250);
    Color babyPink = new Color(241, 207, 255);
    Color background = new Color(7, 11, 59);
    Color border = new Color(1, 12, 138);

    private final Color[] colorArray = {babyPink, mint, lightBlue, sunset, peach,
            babyYellow, teal, lavender, skyBlue, babyBlue};

    private Point point;
    private int currPiece;
    private int rotation;
    private final ArrayList<Integer> nextPiece = new ArrayList<>();
    private int score;
    private boolean isGameOver;
    public static final int WIDTH = 13;
    public static final int HEIGHT = 24;
    private final int RIGHT_BORDER = 12;
    private final int BOTTOM_BORDER = 22;
    public static final int INIT_X_POSITION = 6;
    public static final int INIT_Y_POSITION = 1;
    private final Color[][] board = new Color[WIDTH][HEIGHT];

    public Color[][] getBoard(){
        return board;
    }

    public Color[] getColorArray(){
        return colorArray;
    }

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

    public void init() {
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
        point = new Point(INIT_X_POSITION, INIT_Y_POSITION);
        rotation = 0;

        //in lieu of random piece generator
        if (nextPiece.isEmpty()) {
            Collections.addAll(nextPiece, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            Collections.shuffle(nextPiece);
        }

        currPiece = nextPiece.get(0);
        nextPiece.remove(0);
    }

    private boolean noCollisionAt(int x, int y, int rotation) {
        for (Point p : tetrisPoints[currPiece][rotation]) {
            if (board[p.x + x][p.y + y] != background) {
                return false;
            }
        }
        return true;
    }

    public void rotate(int rotateBy) {
        int newRotation = (rotation + rotateBy) % 4;
        //if go counter clockwise at position 0, then it would be -1 == position 3
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
            if (newY == INIT_Y_POSITION){
                isGameOver = true;
                break;
            }
            board[newX][newY] = colorArray[currPiece];
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