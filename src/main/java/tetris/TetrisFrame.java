package tetris;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TetrisFrame extends JFrame {
    private final TetrisGame tetris;
    private final TetrisView view;
    private final TetrisKeyListener keyListener;
    private ScheduledFuture<?> scheduledFuture;
    private final ImageIcon icon;

    public TetrisFrame(TetrisGame tetrisGame, TetrisView tetrisView, TetrisKeyListener tetrisKeyListener){
        super();
        tetris = tetrisGame;
        view = tetrisView;
        keyListener = tetrisKeyListener;
        icon = new ImageIcon(getClass().getResource("/TetrisIcon.png"));

        setUpFrame();
        tetris.startGame();
        scheduleDropShape();
    }

    private void setUpFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setIconImage(icon.getImage());
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
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable dropShapeTask = () -> {
            if(tetris.isGameOver()){
                gameOver();
            } else {
                tetris.drop();
                view.repaint();
            }
        };
        scheduledFuture = scheduler.scheduleAtFixedRate(dropShapeTask, delay, delay, TimeUnit.MILLISECONDS);
    }

    public void gameOver(){
        int answer = JOptionPane.showConfirmDialog(this, "Game Over.\n Would you like to play again?",
                "Game Over.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        scheduledFuture.cancel(true);
        if(answer == JOptionPane.YES_OPTION){
            startNewGame();
        }
        else{
            System.exit(0);
        }
    }

    private void startNewGame() {
        tetris.startGame();
        view.repaint();
        scheduleDropShape();
    }
}
