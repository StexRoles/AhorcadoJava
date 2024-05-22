package Game.HangmanGane;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * Clase para posicionamiento correcto de letras
 */

public class WordPanel extends JPanel {
    private final String WORD;
    private ArrayList<JLabel> labels;

    // Constructor
    public WordPanel(String word) {
        this.WORD = word;
        labels = new ArrayList<JLabel>();

        this.setLayout(new FlowLayout());
        this.setBackground(Constants.COLOR_BACKGROUND);

        // espacios por palabra
        for(int i = 0; i < this.WORD.length(); i++) {
            JLabel lblLetter;
            if(this.WORD.charAt(i) == ' '){
                lblLetter = new JLabel(" ", SwingConstants.CENTER);
            }
            else{
                lblLetter = new JLabel("_", SwingConstants.CENTER);
            }
            lblLetter.setFont(Constants.FONT(30));

            labels.add(lblLetter);
            this.add(lblLetter);
        }
    }

    // metodo de verificacion de las letras
    public boolean matchLetter(String letter) {
        // Primero, vemos cuáles son las posiciones de la letra en la palabra.
        ArrayList<Integer> letterPosition = getPositionsOfLetter(this.WORD, letter);
        // si hay apariciones de esa letra en la palabra
        if(letterPosition.size() > 0) {
            // intercambiamos los espacios por la letra deseada
            for(int position : letterPosition) {
                JLabel lblLetter = labels.get(position);
                lblLetter.setText(letter);
            }

            return true;
        }

        return false;
    }

    // Método utilizado para saber si todas las letras eran correctas.
    public boolean hasGuessedAllLetters() {
        for(JLabel label : labels) {
            if(label.getText().equals("_")) {
                return false;
            }
        }

        return true;
    }

    //Método utilizado para saber cuáles son todas las posiciones de una letra determinada.
    private ArrayList<Integer> getPositionsOfLetter(String word, String letter) {
        ArrayList<Integer> letterPosition = new ArrayList<Integer>();
        String lowerCaseWord = word.toLowerCase();

        // repasamos la palabra completa y comparamos sus caracteres con la letra deseada
        for(int i = 0; i < word.length(); i++) {
            if(lowerCaseWord.charAt(i) == letter.toLowerCase().charAt(0)) {
                letterPosition.add(i);
            }
        }

        return letterPosition;
    }
}
