package tetris;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TetrisFrame extends JFrame {
    private TetrisGame tetris;
    private TetrisView view;
    private final TetrisKeyListener keyListener;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;

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
        scheduler = Executors.newScheduledThreadPool(1);
        final Runnable dropShapeTask = () -> {
            if(tetris.isGameOver()){
                gameOver();
                scheduledFuture.cancel(true);
            } else {
                tetris.drop();
                view.repaint();
            }
        };
        scheduledFuture = scheduler.scheduleAtFixedRate(dropShapeTask, delay, delay, TimeUnit.MILLISECONDS);
    }

    public void gameOver(){
        int answer = JOptionPane.showConfirmDialog(this, "Game Over.\n Would you like to play again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION){
            startNewGame();
        }
        else{
            System.exit(0);
        }
    }

    private void startNewGame() {
        tetris = new TetrisGame();
        view.setTetrisGameInstance(tetris);
        keyListener.setTetrisGameInstance(tetris);
        tetris.init();
        view.repaint();
        scheduleDropShape();
    }
}
