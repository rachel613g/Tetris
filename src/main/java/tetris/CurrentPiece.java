package tetris;

import java.awt.*;

import static tetris.TetrisGame.INIT_X_POSITION;
import static tetris.TetrisGame.INIT_Y_POSITION;

public class CurrentPiece {
    private Point point;
    private int currPieceIndex;
    private int rotation;

    public CurrentPiece(){
        point = new Point(0,0);
    }

    public int getX() {
        return point.x;
    }
    public int getY(){
        return point.y;
    }

    public int getCurrPieceIndex() {
        return currPieceIndex;
    }

    public int getRotation() {
        return rotation;
    }

    public void setX(int delta) {
        this.point.setLocation(this.point.x + delta, this.point.y);
    }

    public void setY(int delta) {
        this.point.setLocation(this.point.x, this.point.y + delta);
    }

    public void setCurrPieceIndex(int currPieceIndex) {
        this.currPieceIndex = currPieceIndex;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
