package Game;

import javax.swing.JPanel;

/*
 * Clase abstrata de panel de juego
 */

public abstract class GamePanel<T extends GameFrame> extends JPanel {
    protected T gameFrame;

    public GamePanel(T gameFrame) {
        this.gameFrame = gameFrame;        
    }
    
    public abstract void checkIfFinished();
    public abstract void restart(int option);
}
