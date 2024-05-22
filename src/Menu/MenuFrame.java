package Menu;

import Constants.Constants;

import javax.swing.*;

public class MenuFrame extends JFrame  {

    private static MenuFrame instance = null;

    private MenuFrame() {
        this.add(new MenuPanel(this));
        this.setTitle("Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.SCREEN_X, Constants.SCREEN_Y);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // PATRON DE DISEÑO SINGLENTON
    public static MenuFrame getInstance() {
        if (instance == null) {
            instance = new MenuFrame();
        }
        return instance;
    }
}
