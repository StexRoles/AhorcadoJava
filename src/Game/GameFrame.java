package Game;

import Constants.Constants;
import javax.swing.*;


public abstract class GameFrame extends JFrame {
    public void title(String title) {
        /*
         * Método para configurar el título de la pantalla
         */
        this.setTitle(title);
    }
    public GameFrame() {
        /*
         * Define detalles visuales
         */
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.SCREEN_X, Constants.SCREEN_Y);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
