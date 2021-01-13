package tetris;

import java.awt.*;

public class Grid {

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

    public static final int WIDTH = 13;
    public static final int HEIGHT = 24;
    private final Color[][] board = new Color[WIDTH][HEIGHT];

    public Color[][] getBoard() {
        return board;
    }

    public Color[] getColorArray() {
        return colorArray;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Color getBackground() {
        return background;
    }

    public Color getBorder() {
        return border;
    }
}
