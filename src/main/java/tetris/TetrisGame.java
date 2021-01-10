package tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TetrisGame {

    public TetrisGame(){

    }

    TetrisShapes tetrisShapes = new TetrisShapes();
    Point[][][] tetrisPoints = tetrisShapes.getShapes();

    Color bumblebee = new Color(207, 207, 0);
    Color darkGreen = new Color(23, 110, 22);
    Color purple = new Color(76, 5, 158);

    private final Color[] colorArray = {Color.CYAN, Color.magenta, Color.ORANGE, Color.yellow, Color.black, Color.PINK, Color.red,
            Color.BLUE, Color.GREEN, purple,bumblebee, darkGreen};

    private Point point;
    private int currPiece;
    private int rotation;
    private final ArrayList<Integer> nextPiece = new ArrayList<>();
    private int score;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 24;
    private final int RIGHT_BORDER = 19;
    private final int BOTTOM_BORDER = 22;
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
                    board[i][j] = Color.PINK;
                } else {
                    board[i][j] = Color.black;
                }
            }
        }
        newPiece();
    }

    private void newPiece() {
        //make x and y constants
        point = new Point(1, 2);
        rotation = 0;
        if (nextPiece.isEmpty()) {
            Collections.addAll(nextPiece, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            Collections.shuffle(nextPiece);
        }
        currPiece = nextPiece.get(0);
        nextPiece.remove(0);
    }

    private boolean noCollisionAt(int x, int y, int rotation) {
        for (Point p : tetrisPoints[currPiece][rotation]) {
            if (board[p.x + x][p.y + y] != Color.black) {
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
            board[point.x + p.x][point.y + p.y] = colorArray[currPiece];
        }
        clearRows();
        newPiece();
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
        for (int j = HEIGHT; j > 0; j--) {
            gap = false;
            for (int i = 1; i < WIDTH; i++) {
                if (board[i][j] == Color.BLACK) {
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
                score += 200;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 400;
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
}