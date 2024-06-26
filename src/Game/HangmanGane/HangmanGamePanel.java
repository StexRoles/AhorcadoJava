package Game.HangmanGane;

import javax.swing.*;

import Constants.Constants;
import Game.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import GameMode.*;
import QuestManager.QuestRandomizer;
import model.Quest;

public class HangmanGamePanel extends GamePanel <HangmanGameFrame>{
    private WordPanel pnlWord;
    private HangmanState hangmanState;
    private Timer timer;
    private int currentTime;
    private final HangmanGameMode DIFFICULTY;
    private Set<Image> drawnBodyParts;
    private QuestRandomizer questRandomizer = new QuestRandomizer();
    private String word;

    // Constructor
    public HangmanGamePanel(HangmanGameMode difficulty, HangmanGameFrame gameFrame) {
        super(gameFrame);
        this.hangmanState = HangmanState.HEAD;
        this.DIFFICULTY = difficulty;
        this.drawnBodyParts = new HashSet<>();

        this.setLayout(null);
        this.setBackground(Constants.COLOR_BACKGROUND);

        // escoge una palabra aleatoria
        Quest quest = questRandomizer.selectRandomQuest();

        // instanciamos o panel que contenga una palabra
        word = quest.getWord();
        pnlWord = new WordPanel(word);

        // adiciona visuales
        JLabel lblTimer = new JLabel();
        JLabel lblUsedLetters = new JLabel("Used letters: ");
        JLabel lblHintContent = new JLabel(quest.getHint().toUpperCase());

        JButton btnBackToGameMode = new JButton("Back to Game Mode Selection");

        // implementa un timer que depende de la dificultad escolgida
        currentTime = difficulty.getTime();
        this.timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {    
                lblTimer.setText(Integer.toString(currentTime--));
                checkIfFinishedTime();
            }
        });
        timer.start();

        lblHintContent.setBounds(0, 30, 900, 50);
        lblHintContent.setHorizontalAlignment(SwingConstants.CENTER);
        lblHintContent.setFont(Constants.FONT(40));

        lblUsedLetters.setBounds(0, 90, 900, 50);
        lblUsedLetters.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsedLetters.setFont(Constants.FONT(40));

        lblTimer.setBounds(0, 150, 900, 50);
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setFont(Constants.FONT(40));

        pnlWord.setBounds(0, Constants.MID_SCREEN_Y + 50, 900, 50);

        btnBackToGameMode.setBounds(Constants.MID_SCREEN_X - Constants.WIDTH_BUTTONS, Constants.MID_SCREEN_Y + 400 - Constants.HEIGHT_BUTTONS, 2 * Constants.WIDTH_BUTTONS, Constants.HEIGHT_BUTTONS);
        btnBackToGameMode.setBackground(Constants.COLOR_BACK_BUTTON);

        btnBackToGameMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HangmanGameModeFrame();
                gameFrame.dispose();
            }
        });

        loadLetterButtons(lblUsedLetters, pnlWord);

        this.add(lblTimer);
        this.add(lblHintContent);
        this.add(lblUsedLetters);
        this.add(pnlWord);
        this.add(btnBackToGameMode);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        drawHangmanBase(g);

        for (Image bodyPart : drawnBodyParts) {
            ImageIcon imageIcon = new ImageIcon(bodyPart.getImagePath());
            java.awt.Image rawImage = imageIcon.getImage();
            g.drawImage(rawImage, bodyPart.getXCoordinate(), bodyPart.getYCoordinate(), null);
        }
    }

    private boolean hasLost() {
        return hangmanState == HangmanState.HANGED;
    }

    private boolean hasLostTime() {
        return currentTime == -1;
    }

    private boolean hasWon() {
        return pnlWord.hasGuessedAllLetters() && hangmanState != HangmanState.HANGED && currentTime >= 0;
    }

    private void drawHangmanBase(Graphics g) {
        ImageIcon imageIcon;
        java.awt.Image image;

        imageIcon = new ImageIcon("utils/assets/images/Enforcado_resized.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X, Constants.MID_SCREEN_Y, null);

        imageIcon = new ImageIcon("utils/assets/images/forca_comeco.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-50, Constants.MID_SCREEN_Y - 100, null);

        imageIcon = new ImageIcon("utils/assets/images/forca_comeco.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-50, Constants.MID_SCREEN_Y - 60, null);

        imageIcon = new ImageIcon("utils/assets/images/forca_fim.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-50, Constants.MID_SCREEN_Y - 170, null);

        imageIcon = new ImageIcon("utils/assets/images/forquilha.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-50, Constants.MID_SCREEN_Y - 197, null);

        imageIcon = new ImageIcon("utils/assets/images/haste.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X - 1, Constants.MID_SCREEN_Y - 197, null);

        imageIcon = new ImageIcon("utils/assets/images/corda_comeco.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-6, Constants.MID_SCREEN_Y - 170, null);

        imageIcon = new ImageIcon("utils/assets/images/corda_meio.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-2, Constants.MID_SCREEN_Y-125, null);

        imageIcon = new ImageIcon("utils/assets/images/corda_fim.gif");
        image = imageIcon.getImage();
        g.drawImage(image, Constants.MID_SCREEN_X-2, Constants.MID_SCREEN_Y-117, null);
    }

    public void checkIfFinished() {
        
        int option = 3;
        if (hasWon()) {
            timer.stop();
            option = JOptionPane.showConfirmDialog(null, "Congrats! You have won! Would you like to play again?", "Nice!", JOptionPane.YES_NO_OPTION);
        }else if (hasLost()) {
            timer.stop();
            option = JOptionPane.showConfirmDialog(null, "Looks like you have lost! The word was " + this.word + ". Would you like to play again?", "Too bad :/", JOptionPane.YES_NO_OPTION);
        }
        restart(option);
    }

    private void checkIfFinishedTime() {
        
        int option = 3;
        if (hasLost() || hasWon()){
            timer.stop();
        }
        else if (hasLostTime()) {
            option = JOptionPane.showConfirmDialog(null, "Looks like your time is up! The word was " + this.word + ". Would you like to play again?", "Too bad :/", JOptionPane.YES_NO_OPTION);
        }
        restart(option);
    }

    public void restart(int option) {
        /*
         * Reinicia el juego
         */
        if (option == 3) return;
        if (option == 0) {
            new HangmanGameFrame(DIFFICULTY);
        } else {
            new HangmanGameModeFrame();
        }
        this.gameFrame.dispose();
    }

    private void loadLetterButtons(JLabel lblUsedLetters, WordPanel pnlWord) {
        String[] letters = new String[] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };

        int line = 0;
        int i = 0;

        for (String letter : letters) {
            JButton newLetterButton = new JButton(letter);

            if (i % 10 == 0) {
                line++;
                i = 0;
            }

            newLetterButton.setBounds(155 + 2 * i * Constants.HORIZONTAL_GAP_BUTTONS, 500 + 2 * line * Constants.VERTICAL_GAP_BUTTONS, 50,50);
            newLetterButton.setBackground(Constants.COLOR_BUTTONS);

            newLetterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String letter = newLetterButton.getText();
                    boolean matches = pnlWord.matchLetter(letter);

                    if (!matches) {
            
                        Set<Image> images = hangmanState.getImages();

                        for(Image image : images) {
                            drawnBodyParts.add(image);
                            repaint();
                        }

                        lblUsedLetters.setText(lblUsedLetters.getText() + " " + letter);
                        hangmanState = HangmanState.nextBodyPart(hangmanState);
                    }

                    checkIfFinished();

                    newLetterButton.setVisible(false);
                }
            });

            this.add(newLetterButton);
            i++;
        }
    }
}
