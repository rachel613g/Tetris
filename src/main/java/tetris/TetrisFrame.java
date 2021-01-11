package tetris;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TetrisFrame extends JFrame {
    private final TetrisGame tetris;
    private final TetrisView view;
    private final TetrisKeyListener keyListener;

    public TetrisFrame(TetrisGame tetrisGame, TetrisView tetrisView, TetrisKeyListener tetrisKeyListener){
        super();
        tetris = tetrisGame;
        view = tetrisView;
        keyListener = tetrisKeyListener;

        setUpFrame();
        tetris.init();
        scheduleDropShape();
    }

    private void setUpFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setSize(350, 630);
        addKeyListener(keyListener);
        add(view);
    }

    /**
     * @scheduler ScheduledExecutorService
     * Java doc: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ScheduledExecutorService.html
     * @dropShapeTask Runnable - executed by scheduler repeatedly at given delay interval.
     */
    private void scheduleDropShape() {
        int delay = 500;
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable dropShapeTask = () -> {
            tetris.drop();
            view.repaint();
        };

        scheduler.scheduleAtFixedRate(dropShapeTask, delay, delay, TimeUnit.MILLISECONDS);
    }
}
