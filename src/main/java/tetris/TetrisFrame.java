package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//resource: https://www.ssaurel.com/blog/learn-to-create-a-tetris-game-in-java-with-swing/
//resource: https://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html

public class TetrisFrame extends JFrame {
    private TetrisGame tetris;
    private TetrisView view;
    private TetrisKeyListener keyListener;
    private Timer timer;

    public TetrisFrame(TetrisGame tetrisGame, TetrisView tetrisView, TetrisKeyListener tetrisKeyListener){
        super();
        tetris = tetrisGame;
        view = tetrisView;
        keyListener = tetrisKeyListener;

        setUpFrame();
        addView();
        addTimer();
        tetris.init();
    }

    private void addView() {
        add(view);
    }

    private void setUpFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");

        //int frameHeight = (TetrisView.CELL_SIZE * (TetrisGame.HEIGHT - 1)) + TetrisView.BORDER_CELL_SIZE;
        //int frameWidth = (TetrisView.CELL_SIZE * (TetrisGame.WIDTH - 1)) + TetrisView.BORDER_CELL_SIZE;

        setSize(550, 690);
        addKeyListener(keyListener);
    }

    private void addTimer(){
        //creds to https://www.ssaurel.com/blog/learn-to-create-a-tetris-game-in-java-with-swing/
        //for giving idea to use Timer
        //creds to https://docs.oracle.com/javase/7/docs/api/javax/swing/Timer.html
        //for the ready-made Timer anonymous instance

//        int delay = 500; //milliseconds
//        ActionListener taskPerformer = new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                tetris.drop();
//                repaint();
//            }
//        };
//        timer = new Timer(delay, null);
//        timer.setActionCommand(null);
//        timer.start();

    }

}
