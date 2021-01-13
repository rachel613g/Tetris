package tetris;

import java.awt.*;

public class TetrisShapes {

    private Point[][][] pointArray;

    public TetrisShapes() {
        createShapes();
    }

    public Point[][][] getShapes() {
        return pointArray;
    }

    /**
     * creates a 3D array of points. First D of array is an array of shapes.
     * Second D is an array of the rotations for that specific shape.
     * Third D is an array of points for a specific rotation.
     * [shape][rotation][point array of points in shape for that rotation]
     */
    private void createShapes() {
        pointArray = new Point[][][]{
                {
                        //J-SHAPE
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)},
                },
                {
                        // 4 box LINE
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
                },
                {
                        // 3 box LINE
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2)},
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2)},
                },
                {
                        //DOT
                        {new Point(0, 0)},
                        {new Point(0, 0)},
                        {new Point(0, 0)},
                        {new Point(0, 0)},
                },
                {
                        //SQUARE
                        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                },
                {
                        //BOOMERANG
                        {new Point(0, 1), new Point(1, 1), new Point(1, 0)},
                        {new Point(1, 0), new Point(1, 1), new Point(0, 0)},
                        {new Point(0, 1), new Point(0, 0), new Point(1, 0)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 1)},
                },
                {
                        //T-SHAPE
                        {new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2, 1)},
                        {new Point(1, 0), new Point(1, 1), new Point(0, 1), new Point(1, 2)},
                        {new Point(0, 0), new Point(1, 1), new Point(1, 0), new Point(2, 0)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
                },
                {
                        //Z-SHAPE
                        {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                        {new Point(2, 0), new Point(2, 1), new Point(1, 1), new Point(1, 2)},
                        {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                        {new Point(2, 0), new Point(2, 1), new Point(1, 1), new Point(1, 2)},
                },
                {
                        //S-SHAPE
                        {new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2, 0)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
                        {new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2, 0)},
                        {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
                },
                {
                        //L-SHAPE
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
                        {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
                        {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)},
                }
        };

    }

}
