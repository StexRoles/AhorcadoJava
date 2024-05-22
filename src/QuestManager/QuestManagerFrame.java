package QuestManager;

import Constants.Constants;
import javax.swing.*;

/*
 * Clase de Frame Generador de Preguntas
 */

public class QuestManagerFrame extends JFrame {

    public QuestManagerFrame() {
        this.add(new QuestManagerPanel(this));
        this.setTitle("Ahorcado");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.SCREEN_X, Constants.SCREEN_Y);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
